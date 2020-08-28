package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class B16956 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int[] di = {-1, 1, 0, 0};
        int[] dj = {0, 0, -1, 1};

        boolean isSheep = false;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'W') {
                    for (int d = 0; d < 4; d++) {
                        int ni = i + di[d];
                        int nj = j + dj[d];
                        if (ni >= R || ni < 0 || nj >= C || nj < 0) continue;
                        if (map[ni][nj] == 'S') {
                            isSheep = true;
                            break;
                        }
                    }
                }
                if (isSheep) break;
            }
            if (isSheep) break;
        }

        bw.write(isSheep ? "0" : "1");
        bw.flush();
    }

}
