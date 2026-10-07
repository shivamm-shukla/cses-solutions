import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Optimal {
     public static void main(String[] args) throws Exception{
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          StringTokenizer st = new StringTokenizer(br.readLine());
          int n = Integer.parseInt(st.nextToken());
          int x = Integer.parseInt(st.nextToken());

          int[] weight = new int[n];
          st = new StringTokenizer(br.readLine());

          for (int i = 0; i < n; i++){
            weight[i] = Integer.parseInt(st.nextToken());
          }

          Arrays.sort(weight);

          int lightest = 0;
          int heaviest = n-1;
          int count = 0;

          while (lightest <= heaviest){
            if (weight[lightest] + weight[heaviest] <= x){
              lightest++;
              heaviest--;
            }
            else heaviest--;
            count++;
          }

          System.out.println(count);
 }
}
