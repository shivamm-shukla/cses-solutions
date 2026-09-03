import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GridPaths {

    static final int MOD = 1000000007;

    public static int calPaths(char[][] grid, int n, int i, int j, int[][] paths) {

        if (grid[n - 1][n - 1] == '*') {
            return 0;
        }

        paths[n - 1][n - 1] = 1;

        for (i = n - 1; i >= 0; i--) {
            for (j = n - 1; j >= 0; j--) {

                if (grid[i][j] == '*') {
                    paths[i][j] = 0;
                    continue;
                }

                if (i == n - 1 && j == n - 1) {
                    continue;
                }

                int up = 0;
                int left = 0;

                if (i + 1 < n) {
                    up = paths[i + 1][j];
                }

                if (j + 1 < n) {
                    left = paths[i][j + 1];
                }

                paths[i][j] = (up + left) % MOD;
            }
        }

        return paths[0][0];
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