package BOJ;

import java.io.*;
import java.util.Arrays;

public class B2309 {

    static final int N = 9, R = 7;
    static int[] arr;
    static int[] result;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);
        backtrack(0, 0);

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void init(BufferedReader br) throws IOException {
        arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine().trim());
        Arrays.sort(arr);
        result = new int[R];
    }

    private static void backtrack(int idx, int cnt) {
        if (cnt == R) {
            int sum = 0;
            for (int next : result) {
                sum += next;
            }
            if (sum == 100) {
                sb = new StringBuilder();
                for (int next : result) {
                    sb.append(next).append("\n");
                }
            }
            return;
        }

        for (int i = idx; i < N; i++) {
            result[cnt] = arr[i];
            backtrack(i + 1, cnt + 1);
        }
    }

}
