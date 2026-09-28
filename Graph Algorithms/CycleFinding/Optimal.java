import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Optimal {
    static class Edge{
      int from;
      int to;
      int cost;

      Edge(int from, int to, int cost){
        this.from = from;
        this.to = to;
        this.cost = cost;
      }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[m];

        for (int i = 0; i < m; i++){
          st = new StringTokenizer(br.readLine());
          int a = Integer.parseInt(st.nextToken());
          int b = Integer.parseInt(st.nextToken());
          int wt = Integer.parseInt(st.nextToken());

          edges[i] = new Edge(a, b, wt);
        }

        StringBuilder res = solve(edges, n);

        if (res == null) System.out.println("NO");
        else {
          System.out.println("YES");
          System.out.println(res);
        }
    }

    static StringBuilder solve(Edge[] edges, int n){

      long[] dist = new long[n+1];
      int[] parent = new int[n+1];
      int x = -1;
      for (int i = 0; i < n; i++){
          x = -1;
          for (Edge e : edges){
            long cost = dist[e.from] + e.cost;
            if (dist[e.to] > cost){
              dist[e.to] = cost;
              parent[e.to] = e.from;
              x = e.to;
            }
          }
      }

      if (x == -1) return null;

      for (int i = 0; i < n; i++){
          x = parent[x];
      }

      StringBuilder sb = new StringBuilder();
      int curr = x;
      sb.append(curr).append(" ");
      curr = parent[curr];

      while (curr != x){
        sb.append(curr).append(" ");
        curr = parent[curr];
      }
      sb.append(curr);


      // reversing
      String[] vertices = sb.toString().split(" ");

        StringBuilder result = new StringBuilder();

        for (int i = vertices.length - 1; i >= 0; i--) {
            result.append(vertices[i]).append(" ");
        }

        return result;
    }
}
