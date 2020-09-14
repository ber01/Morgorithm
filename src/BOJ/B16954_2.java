package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class B16954_2 {

    static class Point {
        int i, j, t;

        public Point(int i, int j, int t) {
            this.i = i;
            this.j = j;
            this.t = t;
        }
    }

    static final int N = 8;
    static char[][] map;
    static boolean[][][] visited;
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
        queue.offer(new Point(N - 1, 0, 0));
        visited[N - 1][0][0] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int ci = cur.i;
            int cj = cur.j;
            int ct = cur.t;

            if (ci == 0) return 1;

            for (int d = 0; d < 9; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                int nt = Math.min(ct + 1, 8);
                if (ni >= N || ni < 0 || nj >= N || nj < 0 || visited[ni][nj][nt]) continue;
                if (ni - ct >= 0 && map[ni - ct][nj] == '#') continue;
                if (ni - ct - 1 >= 0 && map[ni - ct - 1][nj] == '#') continue;
                visited[ni][nj][nt] = true;
                queue.offer(new Point(ni, nj, nt));
            }

        }

        return 0;
    }

    private static void init(BufferedReader br) throws IOException {
        map = new char[N][N];
        visited = new boolean[N][N][9];
        for (int i = 0; i < N; i++) {
            String str = br.readLine().trim();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
            }
        }
    }
}
