import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Recursive {

   public static int maximizeScore(int[] arr, int n, int i, int j) {

        if (i > j) return 0;
        if (i == j) return arr[i];

        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += arr[k];
        }

        int first = arr[i] + (sum - maximizeScore(arr, n, i + 1, j));
        int second = arr[j] + (sum - maximizeScore(arr, n, i, j - 1));

        return Math.max(first, second);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int n = Integer.parseInt(line.trim());
        String[] parts = br.readLine().trim().split("\\s+");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        System.out.println(maximizeScore(arr, n, 0, n - 1));
    }
  
}


// Homework Problems:
// Book Shop (1158)
// Edit Distance(1639)