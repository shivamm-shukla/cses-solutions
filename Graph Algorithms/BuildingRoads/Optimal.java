import java.io.*;
import java.util.*;

public class Optimal {

    static ArrayList<Integer>[] adj;
    static boolean[] visited;

    static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbour : adj[node]) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        StringTokenizer st = new StringTokenizer(line);

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
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

        visited = new boolean[n + 1];
        List<Integer> representatives = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                representatives.add(i);
                bfs(i);
            }
        }

        int roadsNeeded = representatives.size() - 1;
        StringBuilder sb = new StringBuilder();
        sb.append(roadsNeeded).append('\n');

        int first = representatives.get(0);
        for (int i = 1; i < representatives.size(); i++) {
            sb.append(first).append(' ').append(representatives.get(i)).append('\n');
        }

        System.out.print(sb);
    }
}