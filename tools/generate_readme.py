#!/usr/bin/env python3
"""Regenerate the marked sections of README.md from the solution files on disk.

Conventions this reads (see README.md):
  <Category>/<ProblemName>/Optimal*.java       the final / best solution
  <Category>/<ProblemName>/<Approach>.java     an alternative approach

A problem counts as *solved* only when an optimal solution file exists in its folder.
A problem that only has alternative approaches (e.g. Recursive.java) is reported
as in progress, never as solved.

Only the text between the BEGIN/END markers is touched; everything you write by
hand around them is left alone.
"""

from __future__ import annotations

import json
import re
import sys
from pathlib import Path
from urllib.parse import quote

REPO = Path(__file__).resolve().parent.parent
README = REPO / "README.md"
META = Path(__file__).resolve().parent / "problems.json"

BAR_WIDTH = 12

# Split PascalCase into words, keeping runs of capitals (II, III) and digits together.
WORD_RE = re.compile(r"[A-Z]+(?![a-z])|[A-Z][a-z']*|\d+")


def marker_block(text: str, name: str, body: str) -> str:
    begin, end = f"<!-- BEGIN:{name} -->", f"<!-- END:{name} -->"
    pattern = re.compile(
        re.escape(begin) + r".*?" + re.escape(end), re.DOTALL
    )
    if not pattern.search(text):
        raise SystemExit(
            f"README.md is missing the {begin} / {end} markers — "
            "restore them or the section cannot be regenerated."
        )
    return pattern.sub(f"{begin}\n{body}\n{end}", text, count=1)


def humanize(name: str) -> str:
    words = WORD_RE.findall(name)
    return " ".join(words) if words else name


def link(text: str, path: Path) -> str:
    rel = path.relative_to(REPO).as_posix()
    return f"[{text}]({quote(rel)})"


def collect(category_dir: Path) -> dict[str, dict]:
    """base name -> {'final': Path|None, 'variants': [(label, Path), ...]}"""
    problems: dict[str, dict] = {}
    if not category_dir.is_dir():
        return problems

    # 1. Problem subdirectories: <Category>/<ProblemName>/...
    for sub in sorted(category_dir.iterdir()):
        if not sub.is_dir() or sub.name.startswith("."):
            continue
        base = sub.name
        java_files = sorted(sub.glob("*.java"))
        if not java_files:
            continue

        entry = problems.setdefault(base, {"final": None, "variants": []})

        final_file = None
        exact_matches = [
            f for f in java_files
            if f.stem.lower() == "optimal" or f.stem == base or f.stem.lower() == "solution"
        ]
        if exact_matches:
            final_file = exact_matches[0]
        else:
            prefix_matches = [
                f for f in java_files
                if f.stem.lower().startswith("optimal")
            ]
            if prefix_matches:
                final_file = prefix_matches[0]

        if final_file:
            entry["final"] = final_file

        for f in java_files:
            if f == final_file:
                continue
            entry["variants"].append((humanize(f.stem), f))

    # 2. Backward compatibility: loose files directly in category_dir
    for f in sorted(category_dir.glob("*.java")):
        stem = f.stem
        base, _, suffix = stem.partition("_")
        entry = problems.setdefault(base, {"final": None, "variants": []})
        if suffix:
            entry["variants"].append((humanize(suffix.replace("_", " ")), f))
        else:
            entry["final"] = f

    return problems


def build(meta: dict) -> tuple[str, str]:
    ids = meta.get("ids", {})
    overrides = {k: v for k, v in meta.get("titles", {}).items() if not k.startswith("_")}

    def title_of(base: str) -> str:
        return overrides.get(base, humanize(base))

    def titled_link(base: str) -> str:
        title = title_of(base)
        task = ids.get(base)
        return f"[{title}](https://cses.fi/problemset/task/{task})" if task else title

    progress_rows = []
    solution_sections = []
    total_solved = total_wip = total_all = 0

    for cat in meta["categories"]:
        name, total = cat["name"], cat["total"]
        problems = collect(REPO / name)
        solved = {b: e for b, e in problems.items() if e["final"]}
        wip = {b: e for b, e in problems.items() if not e["final"]}

        total_solved += len(solved)
        total_wip += len(wip)
        total_all += total

        pct = round(100 * len(solved) / total) if total else 0
        filled = round(BAR_WIDTH * len(solved) / total) if total else 0
        bar = "█" * filled + "░" * (BAR_WIDTH - filled)
        label = f"[{name}]({quote(name)}/)" if problems else name
        note = f" _(+{len(wip)} in progress)_" if wip else ""
        progress_rows.append(
            f"| {label} | {len(solved)} / {total}{note} | `{bar}` {pct}% |"
        )

        if not problems:
            continue

        lines = [f"### {name}", ""]
        if solved:
            lines += [
                "| Problem | Solution | Other approaches |",
                "| ------- | -------- | ---------------- |",
            ]
            for base in sorted(solved):
                entry = solved[base]
                others = ", ".join(
                    link(lbl, p) for lbl, p in sorted(entry["variants"])
                ) or "—"
                lines.append(
                    f"| {titled_link(base)} "
                    f"| {link(entry['final'].name, entry['final'])} "
                    f"| {others} |"
                )
            lines.append("")
        if wip:
            lines += [
                "> **In progress** — attempted, but no final solution committed yet.",
                "",
                "| Problem | Attempts so far |",
                "| ------- | --------------- |",
            ]
            for base in sorted(wip):
                attempts = ", ".join(
                    link(lbl, p) for lbl, p in sorted(wip[base]["variants"])
                )
                lines.append(f"| {titled_link(base)} | {attempts} |")
            lines.append("")
        solution_sections.append("\n".join(lines))

    pct = round(100 * total_solved / total_all) if total_all else 0
    progress = "\n".join(
        [
            "| Category | Solved | Progress |",
            "| -------- | ------ | -------- |",
            *progress_rows,
            f"| **Total** | **{total_solved} / {total_all}** | **{pct}%** |",
        ]
    )
    if total_wip:
        progress += (
            f"\n\n_{total_wip} more problem(s) attempted but not finished — "
            "listed under their category below. A problem counts as solved only "
            "once it has an optimal solution file._"
        )

    solutions = (
        "\n".join(solution_sections).rstrip()
        if solution_sections
        else "_No solutions committed yet._"
    )
    return progress, solutions


def main() -> int:
    check = "--check" in sys.argv
    meta = json.loads(META.read_text(encoding="utf-8"))
    progress, solutions = build(meta)

    original = README.read_text(encoding="utf-8")
    updated = marker_block(original, "PROGRESS", progress)
    updated = marker_block(updated, "SOLUTIONS", solutions)

    if updated == original:
        print("README.md already up to date.")
        return 0
    if check:
        print("README.md is out of date — run tools/generate_readme.py", file=sys.stderr)
        return 1
    README.write_text(updated, encoding="utf-8")
    print("README.md updated.")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
