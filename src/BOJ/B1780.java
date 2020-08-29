package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class B1780 {

    static int[] cnt;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine().trim());

        cnt = new int[3];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0, 0, N);

        StringBuilder sb = new StringBuilder();
        for (int next : cnt) sb.append(next).append("\n");

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void divide(int x, int y, int N) {
        if (isFlag(x, y, N)) {
            int index = map[x][y] + 1;
            cnt[index]++;
            return;
        }

        int M = N / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                divide(x + i * M, y + j * M, M);
            }
        }
    }

    private static boolean isFlag(int x, int y, int N) {
        for (int i = x; i < x + N; i++) {
            for (int j = y; j < y + N; j++) {
                if (map[x][y] != map[i][j]) return false;
            }
        }

        return true;
    }

}
