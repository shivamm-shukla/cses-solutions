
import java.io.*;
import java.util.*;

public class Optimal {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        long x = Long.parseLong(st.nextToken());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());

        // First window
        long l = x;
        long r = x;
        long sum = x;

        int count = 1;

        while (count < k) {
            r = (a * r + b) % c;
            sum += r;
            count++;
        }

        long ans = sum;

        // Slide the window
        int length = k;

        while (length < n) {
            r = (a * r + b) % c;

            sum = sum + r - l;

            l = (a * l + b) % c;

            ans ^= sum;
            length++;
        }

        System.out.println(ans);
    }
}
