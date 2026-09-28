import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Optimal {

    static final long INF = Long.MAX_VALUE / 4;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        long[][] dist = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int wt = Integer.parseInt(st.nextToken());

            dist[a][b] = Math.min(dist[a][b], wt);
            dist[b][a] = Math.min(dist[b][a], wt);
        }

        solve(dist, n);

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < q; i++) {

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (dist[a][b] == INF) {
                res.append(-1).append("\n");
            } else {
                res.append(dist[a][b]).append("\n");
            }
        }

        System.out.print(res);
    }


    static void solve(long[][] dist, int n) {

        for (int via = 1; via <= n; via++) {

            for (int from = 1; from <= n; from++) {

                if (dist[from][via] == INF) {
                    continue;
                }

                for (int to = 1; to <= n; to++) {

                    if (dist[via][to] == INF) {
                        continue;
                    }

                    long newDist =
                            dist[from][via] + dist[via][to];

                    if (newDist < dist[from][to]) {
                        dist[from][to] = newDist;
                    }
                }
            }
        }
    }
}