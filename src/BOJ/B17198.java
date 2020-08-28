package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class B17198 {

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
        StringBuilder sb = new StringBuilder();

        int N = 10;
        char[][] map = new char[N][N];
        Point pointL = null;
        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = chars[j];
                if (map[i][j] == 'B') {
                    queue.offer(new Point(i, j));
                } else if (map[i][j] == 'L') {
                    pointL = new Point(i, j);
                }
            }
        }

        int[] di = {-1, 1, 0, 0};
        int[] dj = {0, 0, -1, 1};

        int[][] dist = new int[N][N];
        boolean isFlag = false;
        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int ci = poll.i;
            int cj = poll.j;

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if (ni >= N || ni < 0 || nj >= N || nj < 0 || map[ni][nj] == 'R' || map[ni][nj] == 'C') continue;
                if (map[ni][nj] == 'L') {
                    isFlag = true; break;
                }
                map[ni][nj] = 'C';
                dist[ni][nj] = dist[ci][cj] + 1;
                queue.offer(new Point(ni, nj));
            }
            if (isFlag) break;
        }

        int min = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            int ni = pointL.i + di[d];
            int nj = pointL.j + dj[d];
            if (ni >= N || ni < 0 || nj >= N || nj < 0 || dist[ni][nj] == 0) continue;
            min = Math.min(min, dist[ni][nj]);
        }

        sb.append(min);

        bw.write(sb.toString());
        bw.flush();
    }

}
