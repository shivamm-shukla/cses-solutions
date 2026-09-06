import java.util.Scanner;

public class Recursive {

    public static int minCoins(int n, int[] coins, int i) {

        if (n == 0) return 0;

        if (n < 0 || i >= coins.length) {
            return Integer.MAX_VALUE;
        }

        if (coins[i] > n) {
            return minCoins(n, coins, i + 1);
        }

        int include = minCoins(n - coins[i], coins, i);

        if (include != Integer.MAX_VALUE) {
            include = 1 + include;
        }

        int exclude = minCoins(n, coins, i + 1);

        return Math.min(include, exclude);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int val = sc.nextInt();

        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        int ans = minCoins(val, coins, 0);

        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }

        sc.close();
    }
}