import java.io.*;
import java.util.*;

public class Optimal {

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;

                if (len == -1) {
                    return -1;
                }
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int num = 0;

            while (c > ' ') {
                num = num * 10 + (c - '0');
                c = read();
            }

            return num;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int m = fs.nextInt();

        TreeMap<Integer, Integer> tickets = new TreeMap<>();

        // Store ticket price -> frequency
        for (int i = 0; i < n; i++) {
            int price = fs.nextInt();
            tickets.put(price, tickets.getOrDefault(price, 0) + 1);
        }

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int maxPrice = fs.nextInt();

            Integer price = tickets.floorKey(maxPrice);

            if (price == null) {
                out.append(-1).append('\n');
            } else {
                out.append(price).append('\n');

                int count = tickets.get(price);

                if (count == 1) {
                    tickets.remove(price);
                } else {
                    tickets.put(price, count - 1);
                }
            }
        }

        System.out.print(out);
    }
}