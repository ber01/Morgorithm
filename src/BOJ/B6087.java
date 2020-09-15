package BOJ;

import java.io.*;
import java.util.*;

public class B6087 {

    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "(" + i + ", " + j + ")";
        }
    }

    static int N, M;
    static char[][] map;
    static int[][] dist;
    static Point S, E;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);
        bfs();

        bw.write(String.valueOf(dist[E.i][E.j] - 1));
        bw.flush();
    }

    private static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(S);
        dist[S.i][S.j] = 0;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int ci = cur.i;
            int cj = cur.j;

            if (ci == E.i && cj == E.j) return;

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                while (ni >= 0 && ni < M && nj >= 0 && nj < N && map[ni][nj] != '*') {
                    if (dist[ni][nj] == -1) {
                        dist[ni][nj] = dist[ci][cj] + 1;
                        queue.offer(new Point(ni, nj));
                    }
                    ni += di[d];
                    nj += dj[d];
                }
            }
        }
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[M][N];
        dist = new int[M][N];
        boolean flag = false;
        for (int i = 0; i < M; i++) {
            String str = br.readLine().trim();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'C' && !flag) {
                    S = new Point(i, j);
                    flag = true;
                } else if (map[i][j] == 'C' && flag) {
                    E = new Point(i, j);
                }
            }
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = -1;
            }
        }
    }

    private static void printDist() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%5d", dist[i][j]);
            }
            System.out.println();
        }
    }

}
