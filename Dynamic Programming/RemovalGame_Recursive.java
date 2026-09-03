import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RemovalGame_Recursive {

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

    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    }
  
}


// Homework Problems:
// Book Shop (1158)
// Edit Distance(1639)