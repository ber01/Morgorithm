import java.io.*;
import java.util.*;

public class B2206 {

    static class Point {
        int i;
        int j;
        int dist;
        int wall;

        public Point(int i, int j, int dist, int wall) {
            this.i = i;
            this.j = j;
            this.dist = dist;
            this.wall = wall;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "i=" + i +
                    ", j=" + j +
                    ", dist=" + dist +
                    ", wall=" + wall +
                    '}';
        }
    }

    static int N, M;
    static int[][] map;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = chars[j] - '0';
            }
        }

        bw.write(String.valueOf(bfs()));
        bw.flush();
    }

    private static int bfs() {
        /*
        정점에 도착했다.
        상, 하, 좌, 우를 검사한다.
        1. 벽이 있다.
            1) 이미 벽을 부순 적이 있다. 그러면 못 부순다. 해당 정점을 큐에 넣지 못한다.
            2) 벽을 부순적이 없다. 그러면 부순다. (0) 해당 정점을 큐에 넣는다.
        2. 벽이 없다.
            1) 이 전에 벽을 부순적이 있다. 그럼 벽 부쉈다고 표기하고 해당 정점을 큐에 넣는다
            2) 이 전에도 벽을 안부쉈다. 그럼 안부쉈다고 하고 해당 정점을 큐에 넣는다

        안 부순 상태는 wall = 0, visited[i][j][0];
        부순 상태는 wall = 1, visited[i][j][1];
        */
        Queue<Point> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2];
        // 처음 좌표 (0, 0), 거리 : 1, 벽 : 없음 (0)
        queue.offer(new Point(0, 0, 1, 0));
        // 첫 좌표는 벽을 안부쉈단다.
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int ci = poll.i;
            int cj = poll.j;
            int dist = poll.dist;
            int wall = poll.wall;

            // 종료조건
            if (ci == N - 1 && cj == M - 1) {
                return dist;
            }

            // 4방을 검사한다.
            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                // 범위에서 벗어나거나 방문하면 넘긴다.
                if (ni >= N || ni < 0 || nj >= M || nj < 0) continue;
                /*
                안 부순 상태는 wall = 0, visited[i][j][0];
                부순 상태는 wall = 1, visited[i][j][1];
                 */
                // 다음 좌표가 벽인지 아닌지 확인한다

                if (map[ni][nj] == 0) {
                    if (visited[ni][nj][wall]) {

                    }
                }

                /*
                if (map[ni][nj] == 1) { // 벽이면
                    if (!visited[ni][nj][wall]) { // 벽인데 이 전에 부순 흔적이 있으면
                        // 방문 표시를 하고 넘긴다.
                        visited[ni][nj][wall] = true;
                    } else { // 벽인데 부순 흔적이 없으면 벽을 부순 흔적을 넣고 길이를 증가시키고 방문 표시를 한다.
                        queue.offer(new Point(ni, nj, dist + 1, 1));
                        visited[ni][nj][wall] = true;
                    }
                } else { // 벽이 아니면
                    if (!visited[ni][nj][wall]) { // 이 전에 부순 흔적이 있으면 부순 흔적을 넣는다.
                        queue.offer(new Point(ni, nj, dist + 1, 1));
                    } else { // 이 전에도 안부쉈으면 안부쉈다고 한다
                        queue.offer(new Point(ni, nj, dist + 1, 0));
                    }
                    // 방문 표시를 한다.
                    visited[ni][nj][wall] = true;
                }
                 */
            }
        }

        return -1;
    }

}
