import java.util.*;
import java.io.*;

public class Optimal {

    static class Flight {
        int to;
        long cost;

        Flight(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Flight>[] flights = new ArrayList[n + 1];
        List<Flight>[] reverseFlights = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            flights[i] = new ArrayList<>();
            reverseFlights[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            flights[a].add(new Flight(b, c));
            reverseFlights[b].add(new Flight(a, c));
        }

        long[] distFromStart = dijkstra(flights, 1, n);

        long[] distToEnd = dijkstra(reverseFlights, n, n);

        long ans = Long.MAX_VALUE;

        for (int city = 1; city <= n; city++) {

            for (Flight flight : flights[city]) {

                int nextCity = flight.to;
                long cost = flight.cost;

                if (distFromStart[city] == Long.MAX_VALUE
                        || distToEnd[nextCity] == Long.MAX_VALUE) {
                    continue;
                }

                long currentCost =
                        distFromStart[city]
                        + cost / 2
                        + distToEnd[nextCity];

                ans = Math.min(ans, currentCost);
            }
        }

        System.out.println(ans);
    }


    static long[] dijkstra(
            List<Flight>[] flights,
            int src,
            int n
    ) {

        long[] dist = new long[n + 1];

        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<Flight> pq =
                new PriorityQueue<>(
                        (a, b) -> Long.compare(a.cost, b.cost)
                );

        dist[src] = 0;

        pq.add(new Flight(src, 0));

        while (!pq.isEmpty()) {

            Flight curr = pq.poll();

            int city = curr.to;
            long cost = curr.cost;

            if (cost > dist[city]) {
                continue;
            }

            for (Flight flight : flights[city]) {

                int nextCity = flight.to;
                long nextCost = cost + flight.cost;

                if (nextCost < dist[nextCity]) {

                    dist[nextCity] = nextCost;

                    pq.add(new Flight(nextCity, nextCost));
                }
            }
        }

        return dist;
    }
}