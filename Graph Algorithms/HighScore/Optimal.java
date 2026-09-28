import java.io.*;
import java.util.*;

public class Optimal {

    static class Edge {
        int u, v;
        long w;

        Edge(int u, int v, long w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static long solve(int n, Edge[] edges, ArrayList<Integer>[] reverse) {

        boolean[] canReach = new boolean[n + 1];

        Stack<Integer> stack = new Stack<>();
        stack.push(n);
        canReach[n] = true;

        while (!stack.isEmpty()) {
            int u = stack.pop();

            for (int v : reverse[u]) {
                if (!canReach[v]) {
                    canReach[v] = true;
                    stack.push(v);
                }
            }
        }

        long INF = Long.MAX_VALUE / 4;

        long[] dist = new long[n + 1];
        Arrays.fill(dist, INF);

        dist[1] = 0;

        for (int i = 1; i <= n - 1; i++) {

            for (Edge edge : edges) {

                if (dist[edge.u] == INF)
                    continue;

                if (dist[edge.v] > dist[edge.u] + edge.w) {
                    dist[edge.v] = dist[edge.u] + edge.w;
                }
            }
        }

        for (Edge edge : edges) {

            if (dist[edge.u] == INF)
                continue;

            if (!canReach[edge.v])
                continue;

            if (dist[edge.v] > dist[edge.u] + edge.w) {
                return -1;
            }
        }

        return -dist[n];
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st =
                new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[m];

        ArrayList<Integer>[] reverse = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            reverse[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());

            edges[i] = new Edge(u, v, -w);

            reverse[v].add(u);
        }

        long answer = solve(n, edges, reverse);

        System.out.println(answer);
    }
}