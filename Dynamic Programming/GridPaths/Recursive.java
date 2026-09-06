import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Recursive {

    static final int MOD = 1000000007;

    public static int calPaths(char[][] grid, int n, int i, int j) {

        if (i >= n || j >= n || grid[i][j] == '*') {
            return 0;
        }

        if (i == n - 1 && j == n - 1) {
            return 1;
        }

        int down = calPaths(grid, n, i + 1, j);
        int right = calPaths(grid, n, i, j + 1);

        return (down + right) % MOD;
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

        System.out.println(calPaths(grid, n, 0, 0));
    }
}
