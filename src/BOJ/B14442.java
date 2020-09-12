package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B14442 {

    static class Point {
        int i, j, k;

        public Point(int i, int j, int k) {
            this.i = i;
            this.j = j;
            this.k = k;
        }
    }
    static int N, M, K;
    static int[][] map;
    static boolean[][][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);

        bw.write(bfs() + "");
        bw.flush();
    }

    private static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, 0));

        int dist = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Point cur = queue.poll();
                int ci = cur.i;
                int cj = cur.j;
                int k = cur.k;
                if (ci == N - 1 && cj == M - 1) return dist;
                for (int d = 0; d < 4; d++) {
                    int ni = ci + di[d];
                    int nj = cj + dj[d];
                    if (ni < 0 || ni >= N || nj < 0 || nj >= M || visited[ni][nj][k]) continue;
                    if (map[ni][nj] == 1) {
                        if (k + 1 > K) continue;
                        visited[ni][nj][k + 1] = true;
                        queue.offer(new Point(ni, nj, k + 1));
                    } else {
                        visited[ni][nj][k] = true;
                        queue.offer(new Point(ni, nj, k));
                    }
                }
            }
            dist++;
        }

        return -1;
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][K + 1];
        for (int i = 0; i < N; i++) {
            String str = br.readLine().trim();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
    }

}

