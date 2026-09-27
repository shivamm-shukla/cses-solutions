import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Optimal {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        char[][] grid = new char[m][n];

        for (int i = 0; i < m; i++) {
            String s = br.readLine();
            char[] arr = s.toCharArray();
            for (int j = 0; j < n; j++) {
                grid[i][j] = arr[j];
            }
        }
        
        String res = solve(grid, m, n);
        if (res != null) {
            System.out.println("YES");
            System.out.println(res.length());
            System.out.println(res);
        } else {
            System.out.println("NO");
        }
    }

    static String solve(char[][] grid, int m, int n) {
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new ArrayDeque<>();
        char[][] parent = new char[m][n];

        char[] dir = {'U', 'R', 'D', 'L'};
        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};

        int startRow = -1, startCol = -1;
        int targetRow = -1, targetCol = -1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'A') {
                    startRow = i;
                    startCol = j;
                    visited[i][j] = true;
                    q.offer(new int[]{i, j});
                } else if (grid[i][j] == 'B') {
                    targetRow = i;
                    targetCol = j;
                }
            }
        }

        boolean found = false;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int row = curr[0];
            int col = curr[1];

            if (row == targetRow && col == targetCol) {
                found = true;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nrow = row + drow[i];
                int ncol = col + dcol[i];

                if (nrow >= 0 && nrow < m && ncol >= 0 && ncol < n && !visited[nrow][ncol] && grid[nrow][ncol] != '#') {
                    visited[nrow][ncol] = true;
                    parent[nrow][ncol] = dir[i];
                    q.offer(new int[]{nrow, ncol});
                }
            }
        }

        if (!found) return null;

        StringBuilder sb = new StringBuilder();
        int crow = targetRow;
        int ccol = targetCol;

        while (crow != startRow || ccol != startCol) {
            char step = parent[crow][ccol];
            sb.append(step);

            if (step == 'U') crow++;      
            else if (step == 'D') crow--; 
            else if (step == 'L') ccol++; 
            else if (step == 'R') ccol--; 
        }

        return sb.reverse().toString();
    }
}
