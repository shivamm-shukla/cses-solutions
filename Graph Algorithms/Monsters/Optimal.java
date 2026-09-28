import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Optimal {

    static int[] drow = {-1, 0, 1, 0};
    static int[] dcol = {0, 1, 0, -1};
    static char[] dir = {'U', 'R', 'D', 'L'};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] grid = new char[n][m];

        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        String res = solve(grid, n, m);

        if (res != null) {
            System.out.println("YES");
            System.out.println(res.length());
            System.out.println(res);
        } else {
            System.out.println("NO");
        }
    }

    static String solve(char[][] grid, int n, int m) {

        int ar = -1;
        int ac = -1;

        Queue<int[]> monsters = new ArrayDeque<>();
        int[][] monsterTime = new int[n][m];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == 'A') {
                    ar = i;
                    ac = j;
                }

                if (grid[i][j] == 'M') {
                    monsters.offer(new int[]{i, j});
                } else {
                  monsterTime[i][j] = -1;
                }
            }
        }


        while (!monsters.isEmpty()) {

            int[] curr = monsters.poll();

            int row = curr[0];
            int col = curr[1];

            for (int i = 0; i < 4; i++) {

                int nrow = row + drow[i];
                int ncol = col + dcol[i];

                if (nrow < 0 || nrow >= n ||
                    ncol < 0 || ncol >= m) {
                    continue;
                }

                if (grid[nrow][ncol] == '#') {
                    continue;
                }

                if (monsterTime[nrow][ncol] != -1) {
                    continue;
                }

                monsterTime[nrow][ncol] = monsterTime[row][col] + 1;

                monsters.offer(new int[]{nrow, ncol});
            }
        }


        int[][] playerTime = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                playerTime[i][j] = -1;
            }
        }

        char[][] parent = new char[n][m];

        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{ar, ac});
        playerTime[ar][ac] = 0;

        while (!q.isEmpty()) {

            int[] curr = q.poll();

            int row = curr[0];
            int col = curr[1];

          
            // Reached boundary - escaped.
     
            if (row == 0 || row == n - 1 ||
                col == 0 || col == m - 1) {

                return buildPath(parent, ar, ac, row, col);
            }

            for (int i = 0; i < 4; i++) {

                int nrow = row + drow[i];
                int ncol = col + dcol[i];

                if (nrow < 0 || nrow >= n ||
                    ncol < 0 || ncol >= m) {
                    continue;
                }

                if (grid[nrow][ncol] == '#') {
                    continue;
                }

                if (playerTime[nrow][ncol] != -1) {
                    continue;
                }

                int nextTime = playerTime[row][col] + 1;

                //  Monster reaches this cell at the same time
                // or earlier - cannot go there.
                 
                if (monsterTime[nrow][ncol] != -1 &&
                    monsterTime[nrow][ncol] <= nextTime) {
                    continue;
                }

                playerTime[nrow][ncol] = nextTime;

                parent[nrow][ncol] = dir[i];

                q.offer(new int[]{nrow, ncol});
            }
        }

        return null;
    }

    static String buildPath(
            char[][] parent,
            int ar,
            int ac,
            int er,
            int ec) {

        StringBuilder path = new StringBuilder();

        int row = er;
        int col = ec;

        while (row != ar || col != ac) {

            char move = parent[row][col];

            path.append(move);

            if (move == 'U') {
                row++;
            } else if (move == 'D') {
                row--;
            } else if (move == 'L') {
                col++;
            } else if (move == 'R') {
                col--;
            }
        }

        return path.reverse().toString();
    }
}