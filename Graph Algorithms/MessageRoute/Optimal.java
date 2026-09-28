import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Optimal {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++){
          adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        List<Integer> res = solve(adj);
        
        if (res == null){
          System.out.println("IMPOSSIBLE");
        }
        else {
          System.out.println(res.size());
          for (int i = 0; i < res.size(); i++){
            System.out.print(res.get(i) + " ");
          }
        }
    }

    static List<Integer> solve (List<List<Integer>> adj){
        int n = adj.size();
        int end = n-1;
        int[] parent = new int[n];
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();

        parent[1] = -1;
        visited[1] = true;
        q.offer(1);

        boolean found = false;
        while (!q.isEmpty()){
          int node = q.poll();

          if (node == end){
            found = true;
            break;
          }

          for(int nei : adj.get(node)){
            if (!visited[nei]){
              visited[nei] = true;
              parent[nei] = node;
              q.offer(nei);
            }
          }
        }

        if (!found) return null;

        int curr = end;
        List<Integer> res = new ArrayList<>();
        while (curr != -1){
          res.add(curr);
          curr = parent[curr];
        }

      Collections.reverse(res);

      return res;
    }
}
