
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Recursive {
  
  public static int longIncSubSeq(int i, int[] arr){
    if(i == 0) return 1;
    
    int max = 0;

    for(int j = i - 1; j >= 0; j--){
      if(arr[i] > arr[j]){
        int cur = longIncSubSeq(j, arr);
        max = Math.max(max, cur);
      }
    }

    return 1 + max;
  }

  public static int findMax(int i, int[] arr){
    if(i == 0){
      return longIncSubSeq(0, arr);
    }

    int cur = longIncSubSeq(i, arr);
    int prev = findMax(i - 1, arr);

    return Math.max(cur, prev);
  }

  public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine().trim());
    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] arr = new int[n];
    
    for (int i = 0; i < n; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }

    System.out.println(findMax(n - 1, arr));
  }
}