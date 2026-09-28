import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Optimal {
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

        List<Integer> path = new ArrayList<>();

        boolean[] visited = new boolean[n + 1];
        boolean[] pathVisited = new boolean[n + 1];

        int[] parent = new int[n + 1];

        int[] cycle = new int[2];

        for (int i = 1; i <= n; i++) {

            if (!visited[i]) {

                if (dfs(adj, visited, pathVisited, parent, i, cycle)) {
                    break;
                }
            }
        }

        int start = cycle[0];
        int end = cycle[1];

        if (start == 0) {
            return null;
        }

      
        while (end != start) {

            path.add(end);

            end = parent[end];
        }

        path.add(start);

        Collections.reverse(path);

        path.add(start);

        return path;
    }


    static boolean dfs(
            List<List<Integer>> adj,
            boolean[] visited,
            boolean[] pathVisited,
            int[] parent,
            int node,
            int[] cycle) {

        visited[node] = true;
        pathVisited[node] = true;

        for (int nei : adj.get(node)) {

            if (!visited[nei]) {

                parent[nei] = node;

                if (dfs(adj, visited, pathVisited, parent, nei, cycle)) {
                    return true;
                }
            }

            else if (pathVisited[nei]) {

                cycle[0] = nei;
                cycle[1] = node;

                return true;
            }
        }

        pathVisited[node] = false;

        return false;
    }
}
