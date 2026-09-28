import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Optimal {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<int[]>> adj = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int wt = Integer.parseInt(st.nextToken());

            adj.get(a).add(new int[]{b, wt});
        }

        long[] dist = solve(adj, 1, n);

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            sb.append(dist[i]).append(" ");
        }

        System.out.println(sb);
    }


    static long[] solve(List<List<int[]>> adj, int node, int n) {

    
        long[] dist = new long[n + 1];

        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq =
                new PriorityQueue<>(
                        (a, b) -> Long.compare(a[1], b[1])
                );


        dist[node] = 0;

        pq.add(new long[]{node, 0});


        while (!pq.isEmpty()) {

            long[] curr = pq.poll();

            int city = (int) curr[0];
            long wt = curr[1];

            if (wt > dist[city]) {
                continue;
            }

            for (int[] child : adj.get(city)) {

                int ncity = child[0];
                long edgeWt = child[1];

                long nwt = wt + edgeWt;

                if (nwt < dist[ncity]) {

                    dist[ncity] = nwt;

                    pq.add(new long[]{ncity, nwt});
                }
            }
        }

        return dist;
    }
}