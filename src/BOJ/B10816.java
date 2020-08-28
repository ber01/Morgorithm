package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B10816 {

    static int N, M;
    static int[] arr1, arr2;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine().trim());
        arr1 = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) arr1[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr1);

        M = Integer.parseInt(br.readLine().trim());
        arr2 = new int[M];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < M; i++) arr2[i] = Integer.parseInt(st.nextToken());

        for (int next : arr2) {
            sb.append(upper(next) - lower(next)).append(" ");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static int upper(int next) {
        int left = 0;
        int right = N - 1;

        int ret = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr1[mid] == next) {
                ret = mid + 1;
                left = mid + 1;
            } else if (arr1[mid] > next) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ret;
    }

    private static int lower(int next) {
        int left = 0;
        int right = N - 1;

        int ret = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr1[mid] == next) {
                ret = mid;
                right = mid - 1;
            } else if (arr1[mid] > next) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ret;
    }

}
