package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class B2578 {

    static int N = 5, cnt;
    static int[][] arr;
    static int[][] b;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);
        bingo();

        bw.write(cnt + "");
        bw.flush();
    }

    private static void bingo() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                delete(b[i][j]);
                if (check()) return;
            }
        }
    }

    private static boolean check() {
        int bingo = 0;
        // 가로
        for (int i = 0; i < N; i++) {
            boolean flag = true;
            for (int j = 0; j < N; j++) {
                if (arr[i][j] != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) bingo++;
        }

        // 세로
        for (int i = 0; i < N; i++) {
            boolean flag = true;
            for (int j = 0; j < N; j++) {
                if (arr[j][i] != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) bingo++;
        }

        // 대각선
        boolean flag = true;
        for (int i = 0; i < N; i++) {
            if (arr[i][i] != 0) {
                flag = false;
                break;
            }
        }
        if (flag) bingo++;

        flag = true;
        for (int i = 0, j = N - 1; i < N; i++, j--) {
            if (arr[i][j] != 0) {
                flag = false;
                break;
            }

        }
        if (flag) bingo++;

        return bingo >= 3;
    }

    private static void delete(int num) {
        cnt++;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == num) {
                    arr[i][j] = 0;
                    return;
                }
            }
        }
    }

    private static void init(BufferedReader br) throws IOException {
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        b = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                b[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

}
