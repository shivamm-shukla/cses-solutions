import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecursiveMemo {

    static int[] dp;

    public static int minSteps(int n) {

        if (n == 0) return 0;

        if (dp[n] != 0) {
            return dp[n];
        }

        List<Integer> digits = getDigits(n);
        int res = Integer.MAX_VALUE;

        for (int d : digits) {
            if (d > 0) {
                res = Math.min(res, minSteps(n - d) + 1);
            }
        }

        dp[n] = res;

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

        dp = new int[n + 1];

        System.out.println(minSteps(n));

        sc.close();
    }
}