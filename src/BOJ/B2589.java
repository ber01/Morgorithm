package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2589 {

    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    static int N, M, ans;
    static char[][] map;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);

        bw.write(solve() + "");
        bw.flush();
    }

    // 육지 L, 바다 W
    public static int solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L' && !visited[i][j]) {
                    bfs(new Point(i, j));
                }
                visited = new boolean[N][M];
            }
        }

        return ans;
    }

    private static void bfs(Point point) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(point);
        visited[point.i][point.j] = true;

        int dist = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Point cur = queue.poll();
                int ci = cur.i;
                int cj = cur.j;

                for (int d = 0; d < 4; d++) {
                    int ni = ci + di[d];
                    int nj = cj + dj[d];
                    if (ni >= N || ni < 0 || nj >= M || nj < 0 || map[ni][nj] == 'W' || visited[ni][nj]) continue;
                    if (map[ni][nj] == 'L') {
                        ans = Math.max(ans, dist);
                    }
                    queue.offer(new Point(ni, nj));
                    visited[ni][nj] = true;
                }
            }
            dist++;
        }

    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = 0;

        map = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();
    }

}
