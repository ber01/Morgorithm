package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class B11053 {

    static int N, max;
    static int[] A, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine().trim());
        A = new int[N];
        dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; st.hasMoreTokens(); i++) A[i] = Integer.parseInt(st.nextToken());

        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j]) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
        }

        for (int next : dp) max = Math.max(max, next);

        bw.write(max + "");
        bw.flush();
    }

}
