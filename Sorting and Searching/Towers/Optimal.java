import java.io.*;
import java.util.*;

public class Optimal {

    static int upperBound(int[] top, int n, int x) {
        int lo = 0;
        int hi = n - 1;

        int ans = n;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (top[mid] > x) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] top = new int[n];
        int towers = 0;

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(st.nextToken());

            int ans = upperBound(top, towers, x);

            if (ans == towers) {
                top[towers] = x;
                towers++;
            } else {
                top[ans] = x;
            }
        }

        System.out.println(towers);
    }
}