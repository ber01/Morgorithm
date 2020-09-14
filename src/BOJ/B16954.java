package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class B16954 {

    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static final int N = 8;
    static char[][] map;
    static int[] di = {0, -1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dj = {0, 0, 0, -1, 1, -1, 1, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);

        bw.write(bfs() + "");
        bw.flush();
    }

    private static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(N - 1, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Point cur = queue.poll();
                int ci = cur.i;
                int cj = cur.j;

                if (ci == 0) return 1;
                if (map[ci][cj] == '#') continue;

                for (int d = 0; d < 9; d++) {
                    int ni = ci + di[d];
                    int nj = cj + dj[d];
                    if (ni < 0 || ni >= N || nj < 0 || nj >= N || map[ni][nj] == '#') continue;
                    queue.offer(new Point(ni, nj));
                }
            }
            downMap();
        }

        return 0;
    }

    private static void downMap() {
        for (int i = 7; i > 0; i--) {
            for (int j = 0; j < 8; j++) {
                map[i][j] = map[i - 1][j];
            }
        }

        for (int i = 0; i < 8; i++) {
            map[0][i] = '.';
        }
    }

    private static void init(BufferedReader br) throws IOException {
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine().trim();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
            }
        }
    }
}
