package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B11399 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                sum += arr[j];
            }
        }
        bw.write(String.valueOf(sum));
        bw.flush();
    }

}
