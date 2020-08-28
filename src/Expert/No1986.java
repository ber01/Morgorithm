package Expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class No1986 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine().trim());
            int sum = 0;
            for (int i = 1; i <= N; i++) {
                if (i % 2 == 1) {
                    sum += i;
                } else {
                    sum -= i;
                }
            }
            sb.append("#").append(t).append(" ").append(sum).append("\n");
        }
        System.out.print(sb.toString().trim());
    }

}
