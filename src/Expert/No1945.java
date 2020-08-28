package Expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class No1945 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            int[] arr = new int[12];
            int N = Integer.parseInt(br.readLine().trim());
            for (int i = 2; i <= N; i++) {
                while (N % i == 0) {
                    N /= i;
                    arr[i]++;
                }
            }
            sb.append("#").append(t).append(" ").append(arr[2]).append(" ").append(arr[3]).append(" ").append(arr[5]).append(" ").append(arr[7]).append(" ").append(arr[11]).append("\n");
        }
        System.out.print(sb.toString().trim());
    }

}
