import java.util.*;
import java.io.*;

public class Optimal {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arrive = new int[n];
        int[] leave = new int[n];

        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arrive[i] = a;
            leave[i] = b;
        }

        Arrays.sort(arrive);
        Arrays.sort(leave);

        int maxi = 1;
        int count = 0;

        int a = 0;
        int l = 0;

        while (a < n){
            if (arrive[a] < leave[l]){
                count++;
                a++;
            }
            else {
                count--;
                l++;
            }

            maxi = Math.max(maxi, count);
        }

        System.out.println(maxi);
        
    }  
}
