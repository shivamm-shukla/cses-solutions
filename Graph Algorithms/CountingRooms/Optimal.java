import java.io.*;
import java.util.*;

public class Optimal {

    static int n, m;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static void bfs(char[][] grid, int sr, int sc) {

        Queue<Integer> q = new ArrayDeque<>();

        q.add(sr * m + sc);

        grid[sr][sc] = '#';

        while (!q.isEmpty()) {

            int curr = q.poll();

            int row = curr / m;
            int col = curr % m;

            // 4 directions
            for (int i = 0; i < 4; i++) {

                int nrow = row + dx[i];
                int ncol = col + dy[i];

                if (nrow < 0 || nrow >= n ||
                    ncol < 0 || ncol >= m) {
                    continue;
                }

                if (grid[nrow][ncol] == '#') {
                    continue;
                }

                grid[nrow][ncol] = '#';

                q.add(nrow * m + ncol);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st =
                new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        char[][] grid = new char[n][m];

        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        int rooms = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == '.') {

                    rooms++;

                    bfs(grid, i, j);
                }
            }
        }

        System.out.println(rooms);
    }
}