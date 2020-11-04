package Expert;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class No1249 {

    static int N;
    static int[][] map, dp;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {

            init(br);
            bfs();

            sb.append("#").append(t).append(" ").append(dp[N - 1][N - 1] + "").append("\n");
        }

        bw.write(sb.toString().trim() + "");
        bw.flush();
    }

    private static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0));
        dp[0][0] = 0;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int ci = cur.i;
            int cj = cur.j;
            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                if (dp[ni][nj] > dp[ci][cj] + map[ni][nj]) {
                    dp[ni][nj] = dp[ci][cj] + map[ni][nj];
                    queue.offer(new Point(ni, nj));
                }
            }
        }
    }

    private static void init(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().trim().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = chars[j] - '0';
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
    }

}
