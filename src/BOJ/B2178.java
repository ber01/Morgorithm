package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2178 {

    private static class Point {
        int i;
        int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] di = {-1, 1, 0, 0};
        int[] dj = {0, 0, -1, 1};

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[][] dist = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0));
        dist[0][0] = 1;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int ci = point.i;
            int cj = point.j;

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if (ni >= N || ni < 0 || nj >= M || nj < 0 || map[ni][nj] == 0 || dist[ni][nj] != 0) continue;
                queue.offer(new Point(ni, nj));
                dist[ni][nj] = dist[ci][cj] + 1;
            }
        }

        bw.write(dist[N - 1][M - 1] + "");
        bw.flush();
    }

}
