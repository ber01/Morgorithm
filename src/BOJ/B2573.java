package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class B2573 {

    static int N, M, year;
    static int[][] map, sea;
    static boolean[][] visited;
    static int[] di = {1, -1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);
        solve();

        bw.write(year + "");
        bw.flush();
    }

    private static void solve() {
        while (true) {
            visited = new boolean[N][M];
            sea = new int[N][M];
            int lump = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0 && !visited[i][j]) {
                        dfs(i, j);
                        lump++;
                        if (lump >= 2) return;
                    }
                }
            }
            if (lump == 0) {
                year = 0;
                return;
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0) {
                        checkSea(i, j);
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] = Math.max(0, map[i][j] -= sea[i][j]);
                }
            }
            year++;
        }
    }

    private static void checkSea(int i, int j) {
        int temp = 0;
        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            if (ni >= N || ni < 0 || nj >= M || nj < 0) continue;
            if (map[ni][nj] == 0) temp++;
        }
        sea[i][j] = temp;
    }

    private static void dfs(int i, int j) {
        visited[i][j] = true;

        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            if (ni >= N || ni < 0 || nj >= M || nj < 0 || map[ni][nj] == 0 || visited[ni][nj]) continue;
            dfs(ni, nj);
        }
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

}
