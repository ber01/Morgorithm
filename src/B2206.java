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
            return "{(" + i + ", " + j + "), dist => " + dist + ", wall = " + wall + "}";
        }
    }

    static int N, M;
    static int[][] map;
    static int[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = chars[j] - '0';
                visited[i][j] = 1000001;
            }
        }

        bw.write(String.valueOf(bfs()));
        bw.flush();
    }

    private static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, 1, 0));

        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            System.out.println("CURRENT => " + poll);
            int ci = poll.i;
            int cj = poll.j;
            int dist = poll.dist;
            int wall = poll.wall;

            if (ci == N - 1 && cj == M - 1) return dist;

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if (ni >= N || ni < 0 || nj >= M || nj < 0 || visited[ni][nj] <= wall) continue;
                if (map[ni][nj] == 1) { // 벽일 때,
                    if (wall == 0) { // 벽을 뚫고오지 않았다면
                        visited[ni][nj] = 1;
                        queue.offer(new Point(ni, nj, dist + 1, 1));
                    }
                } else { // 벽이 아니라면,
                    visited[ni][nj] = wall;
                    queue.offer(new Point(ni, nj, dist + 1, wall));
                }
            }
            System.out.println(queue);
            printVisited();
        }

        return -1;
    }

    private static void printVisited() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf("%10d", visited[i][j]);
            }
            System.out.println();
        }
    }

}
