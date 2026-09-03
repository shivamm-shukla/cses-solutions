import java.util.Scanner;

public class RemovingDigits {

    public static int minSteps(int n) {
      int[] dp = new int[n + 1];

      for (int i = 1; i <= n; i++) {

          int temp = i;
          int res = Integer.MAX_VALUE;

          while (temp > 0) {
              int d = temp % 10;
              temp /= 10;

              if (d > 0) {
                  res = Math.min(res, dp[i - d] + 1);
              }
          }

        dp[i] = res;
    }

      return dp[n];
  }
    public static void main(String[] arg) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println(minSteps(n));

        sc.close();
    }
}