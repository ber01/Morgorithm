package Expert;

import java.io.*;
import java.util.StringTokenizer;

public class No9229_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] a = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; st.hasMoreTokens(); i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            int result = -1;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    int weight = a[i] + a[j];
                    if (M == weight) {
                        result = M;
                        break;
                    } else if (M > weight) {
                        result = Math.max(result, weight);
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

}