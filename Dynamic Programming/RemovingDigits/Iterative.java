import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Iterative {

    public static int minSteps(int n) {

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {

            int res = Integer.MAX_VALUE;

            List<Integer> digits = getDigits(i);

            for (int d : digits) {
                if (d > 0) {
                    res = Math.min(res, dp[i - d] + 1);
                }
            }

            dp[i] = res;
        }

        return dp[n];
    }

    private static List<Integer> getDigits(int n) {

        List<Integer> list = new ArrayList<>();

        while (n != 0) {
            int dig = n % 10;
            list.add(dig);
            n = n / 10;
        }

        return list;
    }

    public static void main(String[] arg) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println(minSteps(n));

        sc.close();
    }
}