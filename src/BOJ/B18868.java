package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class B18868 {

    static int M;
    static int N;
    static int[][] map;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = i + 1; j < M; j++) {
                check(map[i], map[j]);
            }
        }

        bw.write(cnt + "");
        bw.flush();
    }

    private static void check(int[] A, int[] B) {
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < B.length; j++) {
                if (!allCheck(A, i, B, j)) return;
            }
        }
        cnt++;
    }

    private static boolean allCheck(int[] A, int i, int[] B, int j) {
        boolean isFlag = false;
        if (A[i] > A[j]) {
            if (B[i] > B[j]) isFlag = true;
        } else if (A[i] == A[j]) {
            if (B[i] == B[j]) isFlag = true;
        } else if (A[i] < A[j]) {
            if (B[i] < B[j]) isFlag = true;
        }
        return isFlag;
    }

}
