package BOJ;

import java.io.*;

public class B10870 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine().trim());
        int[] dp = new int[21];

        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= 20; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        bw.write(dp[N] + "");
        bw.flush();
    }

}
