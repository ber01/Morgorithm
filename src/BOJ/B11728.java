package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class B11728 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        int[] B = new int[M];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < M; i++) B[i] = Integer.parseInt(st.nextToken());

        int[] C = new int[N + M];
        int i = 0, j = 0, k = 0;
        while (i < N && j < M) C[k++] = A[i] >= B[j] ? B[j++] : A[i++];
        while (i < N) C[k++] = A[i++];
        while (j < M) C[k++] = B[j++];

        for (int next : C) sb.append(next).append(" ");

        bw.write(sb.toString().trim());
        bw.flush();
    }

}
