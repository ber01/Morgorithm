package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class B11047 {

    static int N, K;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = N - 1; i >= 0; i--) arr[i] = Integer.parseInt(br.readLine().trim());

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            cnt += K / arr[i];
            K %= arr[i];
        }

        bw.write(cnt + "");
        bw.flush();
    }

}
