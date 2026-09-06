import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RecursiveMemo {

    static final int MOD = 1000000007;

    public static int calPaths(char[][] grid, int n, int i, int j, int[][] paths) {

        if (i >= n || j >= n || grid[i][j] == '*') {
            return 0;
        }

        if (paths[i][j] != -1) {
            return paths[i][j];
        }

        if (i == n - 1 && j == n - 1) {
            paths[i][j] = 1;
            return 1;
        }

        int down = calPaths(grid, n, i + 1, j, paths);
        int right = calPaths(grid, n, i, j + 1, paths);

        paths[i][j] = (down + right) % MOD;

        return paths[i][j];
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        int n = Integer.parseInt(br.readLine().trim());

        char[][] grid = new char[n][n];

        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().trim().toCharArray();
        }

        int[][] paths = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(paths[i], -1);
        }

        System.out.println(calPaths(grid, n, 0, 0, paths));
    }
}