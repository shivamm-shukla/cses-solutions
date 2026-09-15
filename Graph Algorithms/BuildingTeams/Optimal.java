import java.io.*;
import java.util.*;

public class Optimal {

    static ArrayList<Integer>[] adj;
    static int[] color;

    static boolean bfs(int start) {

        Queue<Integer> q = new ArrayDeque<>();

        q.add(start);
        color[start] = 1;

        while (!q.isEmpty()) {

            int node = q.poll();

            for (int neighbour : adj[node]) {

                if (color[neighbour] == 0) {

                    color[neighbour] = 3 - color[node];
                    q.add(neighbour);

                } else if (color[neighbour] == color[node]) {

                    return false;
                }
            }
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st =
                new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        color = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].add(v);
            adj[v].add(u);
        }

        for (int i = 1; i <= n; i++) {

            if (color[i] == 0) {

                if (!bfs(i)) {
                    System.out.println("IMPOSSIBLE");
                    return;
                }
            }
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            ans.append(color[i]).append(" ");
        }

        System.out.println(ans);
    }
}