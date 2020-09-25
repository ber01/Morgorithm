package Expert;

import java.io.*;
import java.util.StringTokenizer;

public class No3307 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine().trim());
            int[] A = new int[N];
            int[] dp = new int[N];

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

            int max = 0;
            for (int next : dp) max = Math.max(next, max);

            sb.append("#").append(t).append(" ").append(max).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

}
