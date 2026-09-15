import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Recursive {

    static class Triple {
        int a;
        int b;
        int c;

        Triple(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static Triple[] projects;

    public static long maxMoney(int index) {

        
        if (index < 0) {
            return 0;
        }


        long skip = maxMoney(index - 1);

        int previous = index - 1;

        while (previous >= 0 &&
               projects[previous].b >= projects[index].a) {
            previous--;
        }

        long take = projects[index].c + maxMoney(previous);

        return Math.max(skip, take);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        projects = new Triple[n];

        for (int i = 0; i < n; i++) {

            StringTokenizer st =
                    new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            projects[i] = new Triple(a, b, c);
        }

       
        Arrays.sort(projects, (x, y) ->
                Integer.compare(x.b, y.b));

        System.out.println(maxMoney(n - 1));
    }
}
