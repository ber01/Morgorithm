package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class B10157 {

    static int R, C, K;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);

        bw.write(K > R * C ? "0" : solve());
        bw.flush();
    }

    private static String solve() {
        int[] di = {-1, 0, 1, 0};
        int[] dj = {0, 1, 0, -1};

        int i = R - 1, j = 0, d = 0, cnt = 1;
        while (cnt != K) {
            arr[i][j] = cnt;

            int ni = i + di[d];
            int nj = j + dj[d];
            if (ni < 0 || nj < 0 || ni >= R || nj >= C || arr[ni][nj] != 0) {
                // 벽에 왔으니 방향을 바꾸자
                d++;
                if (d == 4) d = 0;
                ni = i + di[d];
                nj = j + dj[d];
            }

            i = ni;
            j = nj;

            cnt++;
        }


        j = j + 1;
        i = R - i;
        return j + " " + i;
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine().trim());
        arr = new int[R][C];
    }

}
