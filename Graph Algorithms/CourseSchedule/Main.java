import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  static boolean hasCycle = false;

  static void dfs(List<Integer>[] adj, Deque<Integer> stack, int[] state, int i) {
    state[i] = 1; // in progress

    for (int ni : adj[i]) {
      if (state[ni] == 1) {
        hasCycle = true;
        return;
      } else if (state[ni] == 0) {
        dfs(adj, stack, state, ni);
        if (hasCycle) return;
      }
    }

    state[i] = 2; // done
    stack.push(i);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    List<Integer>[] adj = new ArrayList[n + 1];

    for (int i = 1; i <= n; i++) {
      adj[i] = new ArrayList<>();
    }

    for (int i = 0; i < m; i++) {
      st = new StringTokenizer(br.readLine());

      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      adj[a].add(b);
    }

    Deque<Integer> stack = new ArrayDeque<>();
    int[] state = new int[n + 1]; // 0 = unvisited, 1 = in progress, 2 = done

    for (int i = 1; i <= n && !hasCycle; i++) {
      if (state[i] == 0) {
        dfs(adj, stack, state, i);
      }
    }

    if (hasCycle) {
      System.out.println("IMPOSSIBLE");
      return;
    }

    StringBuilder sb = new StringBuilder();
    while (!stack.isEmpty()) {
      sb.append(stack.pop()).append(' ');
    }
    System.out.println(sb.toString().trim());
  }
}