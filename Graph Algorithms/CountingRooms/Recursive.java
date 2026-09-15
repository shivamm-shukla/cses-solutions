import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Recursive {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static void dfs(char[][] grid, boolean[][] vis, int row, int col) {

        vis[row][col] = true;

        for (int i = 0; i < 4; i++) {

            int nrow = row + dx[i];
            int ncol = col + dy[i];

            if (nrow < 0 || nrow >= grid.length ||
                ncol < 0 || ncol >= grid[0].length) {
                continue;
            }

            if (grid[nrow][ncol] == '#' || vis[nrow][ncol]) {
                continue;
            }

            dfs(grid, vis, nrow, ncol);
        }
    }

    static int countRooms(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] vis = new boolean[n][m];

        int rooms = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == '.' && !vis[i][j]) {

                    rooms++;

                    dfs(grid, vis, i, j);
                }
            }
        }

        return rooms;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st =
                new StringTokenizer(br.readLine().strip());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] grid = new char[n][m];

        for (int i = 0; i < n; i++) {

            String row = br.readLine().strip();

            grid[i] = row.toCharArray();
        }

        System.out.println(countRooms(grid));
    }
}