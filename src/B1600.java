import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1600 {

    static class Point {
        int i;
        int j;
        int dist;
        int cnt;

        public Point(int i, int j, int dist, int cnt) {
            this.i = i;
            this.j = j;
            this.dist = dist;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "i=" + i +
                    ", j=" + j +
                    ", dist=" + dist +
                    ", cnt=" + cnt +
                    '}';
        }
    }
    static int K;
    static int W;
    static int H;
    static int[][] map;
    static boolean[][][] visited;
    static int[] di = {-1, 1, 0, 0, -2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dj = {0, 0, -1, 1, -1, 1, -2, 2, -2, 2, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        K = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visited = new boolean[H][W][K + 1];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(String.valueOf(bfs()));
        bw.flush();
    }

    private static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int ci = poll.i;
            int cj = poll.j;
            int dist = poll.dist;
            int cnt = poll.cnt;

            if (ci == H - 1 && cj == W - 1) {
                return dist;
            }

            for (int d = 0; d < 12; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if (ni >= H || ni < 0 || nj >= W || nj < 0 || map[ni][nj] == 1) continue;
                if (d < 4) { // 상, 하, 좌, 우를 탐색했는데
                    if (visited[ni][nj][cnt]) continue;
                    visited[ni][nj][cnt] = true;
                    queue.offer(new Point(ni, nj, dist + 1, cnt));
                } else { // 말의 이동을 할 때,
                    if (K > cnt) { // K가 더 크다면
                        if (visited[ni][nj][cnt + 1]) continue;
                        visited[ni][nj][cnt + 1] = true;
                        queue.offer(new Point(ni, nj, dist + 1, cnt + 1));
                    }
                }
            }

        }

        return -1;
    }

}
