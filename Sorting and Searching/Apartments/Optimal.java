import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Optimal {
    public static void main(String[] args) throws Exception{
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          StringTokenizer st = new StringTokenizer(br.readLine());
          int n = Integer.parseInt(st.nextToken());
          int m = Integer.parseInt(st.nextToken());
          int k = Integer.parseInt(st.nextToken());

          int[] desiredSize = new int[n];
          st = new StringTokenizer(br.readLine());
          for (int i = 0; i < n; i++){
            desiredSize[i] = Integer.parseInt(st.nextToken());
          }

          int [] apartmentSize = new int[m];
          st = new StringTokenizer(br.readLine());

          for (int i = 0; i < m; i++){
            apartmentSize[i] = Integer.parseInt(st.nextToken());
          }

          Arrays.sort(desiredSize);
          Arrays.sort(apartmentSize);

          int i =  0; 
          int j = 0;
          int count = 0;
          while (i < n && j < m) {

              if (apartmentSize[j] < desiredSize[i] - k) {
                  j++;
              }
              else if (apartmentSize[j] > desiredSize[i] + k) {
                  i++;
              }
              else {
                  count++;
                  i++;
                  j++;
              }
          }

          System.out.println(count);
    }  
}
