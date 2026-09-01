# CSES Problem Set — Solutions

My Java solutions to the [CSES Problem Set](https://cses.fi/problemset/), organized by category as they appear on the site.

## Progress

| Category              | Solved |
| --------------------- | ------ |
| Introductory Problems | 0 / 19 |
| Sorting and Searching | 0 / 35 |
| Dynamic Programming   | 0 / 19 |
| Graph Algorithms      | 0 / 36 |
| Range Queries         | 0 / 20 |
| Tree Algorithms       | 0 / 16 |
| Mathematics           | 0 / 31 |
| String Algorithms     | 0 / 20 |
| Geometry              | 0 / 15 |
| Advanced Techniques   | 0 / 21 |

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

Every problem gets its own file, named after the problem title in PascalCase (e.g. `Weird Algorithm` → `WeirdAlgorithm.java`).

If I solved a problem with more than one approach, the plain filename (no suffix) is always the best/final version — the one that's cleanest or most optimized. Other approaches sit next to it with a suffix:

```
Dynamic Programming/
├── CoinCombinations.java              # final, most optimized solution
├── CoinCombinations_Recursive.java    # brute-force recursive
├── CoinCombinations_Memo.java         # top-down with memoization
```

So if you just want the best solution to a problem, grab the file without a suffix. If you want to see the thought process (brute force → optimized), the suffixed files are there too.

## Running a solution

```bash
javac ProblemName.java
java ProblemName < input.txt
```

CSES takes input/output via stdin/stdout, so I use `BufferedReader`/`StringBuilder` instead of `Scanner` — `Scanner` is too slow for the larger constraints on some problems.
