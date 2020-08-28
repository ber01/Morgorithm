package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B1747 {

    private static final int L = 2000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        boolean[] visited = new boolean[L + 1];
        Arrays.fill(visited, true);

        visited[1] = false;
        for (int i = 2; i <= Math.sqrt(L); i++) {
            for (int j = i * i; j <= L; j+= i) {
                visited[j] = false;
            }
        }

        for (int i = 2; i < visited.length; i++) {
            if (visited[i] && i >= N) {
                StringBuilder sb = new StringBuilder(String.valueOf(i));
                int check = Integer.parseInt(sb.reverse().toString());
                if (i == check) {
                    System.out.print(i);
                    return;
                }
            }
        }
    }

}
