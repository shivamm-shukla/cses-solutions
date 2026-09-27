import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Optimal {
    static int cycleStart = -1;
    static int cycleEnd = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            if (line == null) break;
            st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        List<Integer> res = solve(adj, n);
        if (res == null) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(res.size());
            StringBuilder sb = new StringBuilder();
            for (int city : res) {
                sb.append(city).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
    }

    static List<Integer> solve(List<List<Integer>> adj, int n) {
        boolean[] visited = new boolean[n + 1];
        int[] parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                if (dfs(adj, visited, parent, i, 0)) {

                    List<Integer> cycle = new ArrayList<>();
                    cycle.add(cycleStart);
                    
                    int curr = cycleEnd;
                    while (curr != cycleStart) {
                        cycle.add(curr);
                        curr = parent[curr];
                    }
                    cycle.add(cycleStart);

                    Collections.reverse(cycle);
                    return cycle;
                }
            }
        }
        return null;
    }

    static boolean dfs(List<List<Integer>> adj, boolean[] visited, int[] parent, int u, int p) {
        visited[u] = true;
        parent[u] = p;

        for (int v : adj.get(u)) {

            if (v == p) {
                continue;
            }

            if (visited[v]) {

                cycleStart = v;
                cycleEnd = u;
                return true;
            }

            if (dfs(adj, visited, parent, v, u)) {
                return true;
            }
        }
        return false;
    }
}
