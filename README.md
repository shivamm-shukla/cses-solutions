# CSES Problem Set — Solutions

My Java solutions to the [CSES Problem Set](https://cses.fi/problemset/), organized by category as they appear on the site.

## Progress

<!-- BEGIN:PROGRESS -->
| Category | Solved | Progress |
| -------- | ------ | -------- |
| Introductory Problems | 0 / 19 | `░░░░░░░░░░░░` 0% |
| Sorting and Searching | 0 / 35 | `░░░░░░░░░░░░` 0% |
| [Dynamic Programming](Dynamic%20Programming/) | 2 / 19 _(+2 in progress)_ | `█░░░░░░░░░░░` 11% |
| Graph Algorithms | 0 / 36 | `░░░░░░░░░░░░` 0% |
| Range Queries | 0 / 20 | `░░░░░░░░░░░░` 0% |
| Tree Algorithms | 0 / 16 | `░░░░░░░░░░░░` 0% |
| Mathematics | 0 / 31 | `░░░░░░░░░░░░` 0% |
| String Algorithms | 0 / 20 | `░░░░░░░░░░░░` 0% |
| Geometry | 0 / 15 | `░░░░░░░░░░░░` 0% |
| Advanced Techniques | 0 / 21 | `░░░░░░░░░░░░` 0% |
| **Total** | **2 / 232** | **1%** |

_2 more problem(s) attempted but not finished — listed under their category below. A problem counts as solved only once it has a final (un-suffixed) solution file._
<!-- END:PROGRESS -->

## Solutions

<!-- BEGIN:SOLUTIONS -->
### Dynamic Programming

| Problem | Solution | Other approaches |
| ------- | -------- | ---------------- |
| [Grid Paths](https://cses.fi/problemset/task/1638) | [GridPaths.java](Dynamic%20Programming/GridPaths.java) | [Memo](Dynamic%20Programming/GridPaths_Memo.java) |
| [Removing Digits](https://cses.fi/problemset/task/1637) | [RemovingDigits.java](Dynamic%20Programming/RemovingDigits.java) | [Iterative](Dynamic%20Programming/RemovingDigits_Iterative.java), [Memo](Dynamic%20Programming/RemovingDigits_Memo.java) |

> **In progress** — attempted, but no final solution committed yet.

| Problem | Attempts so far |
| ------- | --------------- |
| [Minimizing Coins](https://cses.fi/problemset/task/1634) | [Recursive](Dynamic%20Programming/MinimizingCoins_Recursive.java) |
| [Removal Game](https://cses.fi/problemset/task/1097) | [Recursive](Dynamic%20Programming/RemovalGame_Recursive.java) |
<!-- END:SOLUTIONS -->

## Structure

```
cses-solutions/
├── Introductory Problems/
├── Sorting and Searching/
├── Dynamic Programming/
├── Graph Algorithms/
├── Range Queries/
├── Tree Algorithms/
├── Mathematics/
├── String Algorithms/
├── Geometry/
└── Advanced Techniques/
```

Each problem is filed under the same category CSES puts it in.

## Naming convention

Every problem gets its own file, named after the problem title in PascalCase (e.g. `Weird Algorithm` → `WeirdAlgorithm.java`). Java requires the public class name to match, so the class is renamed to match the file.

If I solved a problem with more than one approach, the plain filename (no suffix) is always the best/final version — the one that's cleanest or most optimized. Other approaches sit next to it with a suffix:

```
Dynamic Programming/
├── RemovingDigits.java              # final, most optimized solution
├── RemovingDigits_Memo.java         # top-down with memoization
├── RemovingDigits_Iterative.java    # first bottom-up version
```

So if you just want the best solution to a problem, grab the file without a suffix. If you want to see the thought process (brute force → optimized), the suffixed files are there too.

A problem with **only** suffixed files is one I haven't finished — the approaches there don't pass yet (too slow, or incomplete). Those show up under **In progress** above and are never counted as solved.

## Running a solution

```bash
javac ProblemName.java
java ProblemName < input.txt
```

CSES takes input/output via stdin/stdout, so I use `BufferedReader`/`StringBuilder` instead of `Scanner` — `Scanner` is too slow for the larger constraints on some problems.

## Keeping this README up to date

The **Progress** and **Solutions** sections above are generated from the files on disk by [`tools/generate_readme.py`](tools/generate_readme.py) — everything between the `BEGIN:`/`END:` marker comments is rewritten, everything else (including this section) is hand-written and left alone.

A `pre-commit` hook regenerates and stages the README on every commit, so it can't drift. Enable it once per clone:

```bash
./tools/install-hooks.sh
```

To run it by hand:

```bash
python3 tools/generate_readme.py           # rewrite the README
python3 tools/generate_readme.py --check    # exit 1 if it's stale, don't write
```

Problem→CSES-task-id links live in [`tools/problems.json`](tools/problems.json); a problem with no entry there just renders without a link, so add ids as you go.
