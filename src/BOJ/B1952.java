package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class B1952 {

    static int R, C;
    static int[][] map;
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        int i = 0, j = 0, d = 0, cnt = 1, curve = 0;
        while (true) {
            map[i][j] = cnt;
            if (cnt == R * C) break;
            int ni = i + di[d];
            int nj = j + dj[d];
            if (ni < 0 || ni >= R || nj < 0 || nj >= C || map[ni][nj] != 0) {
                d++;
                if (d == 4) d = 0;
                ni = i + di[d];
                nj = j + dj[d];
                curve++;
            }

            i = ni;
            j = nj;

            cnt++;
        }

        /*
        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                System.out.printf("%3d", map[x][y]);
            }
            System.out.println();
        }
         */

        bw.write(curve + "");
        bw.flush();
    }

}

