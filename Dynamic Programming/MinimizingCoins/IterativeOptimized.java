import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class IterativeOptimized {

    public static int minCoins(int val, int[] coins) {

        // dp[i] = minimum coins needed to make amount i
        int[] dp = new int[val + 1];

        // Initially, every amount is considered impossible
        Arrays.fill(dp, val + 1);

        // Base case
        dp[0] = 0;

        // Build answer from 1 to val
        for (int amount = 1; amount <= val; amount++) {

            for (int coin : coins) {

                if (coin <= amount) {
                    dp[amount] = Math.min(
                        dp[amount],
                        1 + dp[amount - coin]
                    );
                }
            }
        }

        // If val is still impossible
        if (dp[val] == val + 1) {
            return -1;
        }

        return dp[val];
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st =
            new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int val = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(minCoins(val, coins));
    }
}