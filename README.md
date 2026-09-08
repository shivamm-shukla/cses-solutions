# CSES Problem Set — Solutions

My Java solutions to the [CSES Problem Set](https://cses.fi/problemset/), organized by category as they appear on the site.

## Progress

<!-- BEGIN:PROGRESS -->
| Category | Solved | Progress |
| -------- | ------ | -------- |
| Introductory Problems | 0 / 19 | `░░░░░░░░░░░░` 0% |
| Sorting and Searching | 0 / 35 | `░░░░░░░░░░░░` 0% |
| [Dynamic Programming](Dynamic%20Programming/) | 2 / 19 _(+4 in progress)_ | `█░░░░░░░░░░░` 11% |
| Graph Algorithms | 0 / 36 | `░░░░░░░░░░░░` 0% |
| Range Queries | 0 / 20 | `░░░░░░░░░░░░` 0% |
| Tree Algorithms | 0 / 16 | `░░░░░░░░░░░░` 0% |
| Mathematics | 0 / 31 | `░░░░░░░░░░░░` 0% |
| String Algorithms | 0 / 20 | `░░░░░░░░░░░░` 0% |
| Geometry | 0 / 15 | `░░░░░░░░░░░░` 0% |
| Advanced Techniques | 0 / 21 | `░░░░░░░░░░░░` 0% |
| **Total** | **2 / 232** | **1%** |

_4 more problem(s) attempted but not finished — listed under their category below. A problem counts as solved only once it has an optimal solution file._
<!-- END:PROGRESS -->

## Solutions

<!-- BEGIN:SOLUTIONS -->
### Dynamic Programming

| Problem | Solution | Other approaches |
| ------- | -------- | ---------------- |
| [Grid Paths](https://cses.fi/problemset/task/1638) | [OptimalIterative.java](Dynamic%20Programming/GridPaths/OptimalIterative.java) | [Recursive](Dynamic%20Programming/GridPaths/Recursive.java), [Recursive Memo](Dynamic%20Programming/GridPaths/RecursiveMemo.java) |
| [Removing Digits](https://cses.fi/problemset/task/1637) | [OptimalIterative.java](Dynamic%20Programming/RemovingDigits/OptimalIterative.java) | [Iterative](Dynamic%20Programming/RemovingDigits/Iterative.java), [Recursive Memo](Dynamic%20Programming/RemovingDigits/RecursiveMemo.java) |

> **In progress** — attempted, but no final solution committed yet.

| Problem | Attempts so far |
| ------- | --------------- |
| [Book Shop](https://cses.fi/problemset/task/1158) | [Recursive](Dynamic%20Programming/BookShop/Recursive.java) |
| [Edit Distance](https://cses.fi/problemset/task/1639) | [Recursive](Dynamic%20Programming/EditDistance/Recursive.java) |
| [Minimizing Coins](https://cses.fi/problemset/task/1634) | [Iterative Optimized](Dynamic%20Programming/MinimizingCoins/IterativeOptimized.java), [Recursive](Dynamic%20Programming/MinimizingCoins/Recursive.java), [Recursive Memo](Dynamic%20Programming/MinimizingCoins/RecursiveMemo.java) |
| [Removal Game](https://cses.fi/problemset/task/1097) | [Recursive](Dynamic%20Programming/RemovalGame/Recursive.java) |
<!-- END:SOLUTIONS -->

## Structure

```
cses-solutions/
├── Introductory Problems/
├── Sorting and Searching/
├── Dynamic Programming/
│   ├── GridPaths/
│   │   ├── OptimalIterative.java
│   │   ├── RecursiveMemo.java
│   │   ├── Recursive.java
│   │   └── input.txt
│   └── RemovingDigits/
│       ├── OptimalIterative.java
│       ├── Iterative.java
│       ├── RecursiveMemo.java
│       └── input.txt
├── Graph Algorithms/
├── Range Queries/
├── Tree Algorithms/
├── Mathematics/
├── String Algorithms/
├── Geometry/
└── Advanced Techniques/
```

Each category directory contains dedicated folders for each problem, named after the problem title in PascalCase.

## Naming convention

Every problem has its own directory (e.g. `GridPaths/`, `RemovingDigits/`). Inside that directory, solutions represent different approaches and versions explored:

- **Optimal / Final Solution**: Named starting with `Optimal` (e.g. `OptimalIterative.java`, `Optimal.java`). This is the cleanest, most optimized solution that passes all test cases.
- **Alternative Approaches / WIP**: Named descriptively after the approach (e.g. `Recursive.java`, `RecursiveMemo.java`, `Iterative.java`).
- **Test Input**: A sample input file (`input.txt`) can be kept alongside the solutions for quick local verification.

```
Dynamic Programming/RemovingDigits/
├── OptimalIterative.java    # final, most optimized solution
├── Iterative.java           # alternative bottom-up version
├── RecursiveMemo.java       # top-down with memoization
└── input.txt                # sample test case
```

Java requires the public class name to match the filename, so each class matches its respective file (e.g. `public class OptimalIterative`).

A problem counts as **solved** once an optimal solution (`Optimal*.java`) is present in its directory. A problem containing only exploratory approaches (such as `Recursive.java`) is tracked under **In progress** above.

## Running a solution

```bash
# Navigate to the problem folder
cd "Dynamic Programming/GridPaths"

# Compile and run with test input
javac OptimalIterative.java
java OptimalIterative < input.txt
```

CSES takes input/output via stdin/stdout, so I use `BufferedReader`/`StringBuilder` instead of `Scanner` where appropriate — `Scanner` can be too slow for larger constraints.
