import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RecursiveMemo {

    public static int minCoins(int n, int[] coins, int[] dp) {

        // Base case
        if (n == 0) {
            return 0;
        }

        // Invalid case
        if (n < 0) {
            return Integer.MAX_VALUE;
        }

        // Already calculated
        if (dp[n] != -1) {
            return dp[n];
        }

        int ans = Integer.MAX_VALUE;

        // Try every coin
        for (int coin : coins) {

            int result = minCoins(n - coin, coins, dp);

            if (result != Integer.MAX_VALUE) {
                ans = Math.min(ans, 1 + result);
            }
        }

        // Store answer
        dp[n] = ans;

        return dp[n];
    }

    public static void main(String[] args) throws Exception {
      
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int val = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }

        // 1D DP array
        int[] dp = new int[val + 1];

        Arrays.fill(dp, -1);

        int ans = minCoins(val, coins, dp);

        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }
}