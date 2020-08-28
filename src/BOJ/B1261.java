package BOJ;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1261 {

    static class Point implements Comparable<Point> {
        int i;
        int j;
        int dist;

        public Point(int i, int j, int d) {
            this.i = i;
            this.j = j;
            this.dist = d;
        }

        @Override
        public int compareTo(Point o) {
            return this.dist - o.dist;
        }
    }

    static int N;
    static int M;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M][N];

        for (int i = 0; i < M; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = chars[j] - '0';
            }
        }

        bw.write(String.valueOf(bfs()));
        bw.flush();
    }

    private static int bfs() {
        Queue<Point> queue = new PriorityQueue<>();
        boolean[][] visited = new boolean[M][N];
        queue.offer(new Point(0, 0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int ci = poll.i;
            int cj = poll.j;
            int dist = poll.dist;
            visited[ci][cj] = true;

            if (ci == M - 1 && cj == N - 1) return dist;

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if (ni >= M || ni < 0 || nj >= N || nj < 0 || visited[ni][nj]) continue;
                visited[ni][nj] = true;
                if (map[ni][nj] == 1) {
                    queue.offer(new Point(ni, nj, dist + 1 ));
                } else {
                    queue.offer(new Point(ni, nj, dist));
                }
            }
        }

        return 0;
    }

}
