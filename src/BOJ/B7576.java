package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7576 {

    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    static int M, N;
    static int[][] map;
    static int[][] dist;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dist = new int[N][M];

        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) queue.offer(new Point(i, j));
            }
        }

        int ret = bfs(queue);
        ret = isValid() ? ret : -1;

        bw.write(String.valueOf(ret));
        bw.flush();
    }

    private static boolean isValid() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) return false;
            }
        }
        return true;
    }

    private static int bfs(Queue<Point> queue) {
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Point cur = queue.poll();
                int ci = cur.i;
                int cj = cur.j;
                for (int d = 0; d < 4; d++) {
                    int ni = ci + di[d];
                    int nj = cj + dj[d];
                    if (ni >= N || ni < 0 || nj >= M || nj < 0 || map[ni][nj] == -1 || map[ni][nj] == 1) continue;
                    map[ni][nj] = 1;
                    dist[ni][nj] = dist[ci][cj] + 1;
                    ans = dist[ni][nj];
                    queue.offer(new Point(ni, nj));
                }
            }
        }

        return ans;
    }

}
