import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2636 {

    static class Point {
        int i;
        int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    static int N, M;
    static int[][] cheese;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cheese = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int hour = 0;
        do {
            hour++;
            bfs();
            meltingCheese();
        } while (isCheese());

        sb.append(hour).append("\n").append(result);
        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void meltingCheese() {
        int cnt = 0;
        Queue<Point> meltQueue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cheese[i][j] == 1) {
                    for (int d = 0; d < 4; d++) {
                        int ni = i + di[d];
                        int nj = j + dj[d];
                        if (ni >= N || ni < 0 || nj >= M || nj < 0 || visited[i][j]) continue;
                        if (cheese[ni][nj] == -1) {
                            visited[i][j] = true;
                            meltQueue.offer(new Point(i, j));
                        }
                    }
                }
            }
        }

        while (!meltQueue.isEmpty()) {
            Point poll = meltQueue.poll();
            cheese[poll.i][poll.j] = -1;
            cnt++;
        }

        result = cnt;
    }

    private static boolean isCheese() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cheese[i][j] == 1) return true;
            }
        }
        return false;
    }

    private static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        visited = new boolean[N][M];
        queue.offer(new Point(0, 0));
        visited[0][0] = true;
        cheese[0][0] = -1;

        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int ci = poll.i;
            int cj = poll.j;
            visited[ci][cj] = true;

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if (ni >= N || ni < 0 || nj >= M || nj < 0 || cheese[ni][nj] == 1 || visited[ni][nj]) continue;
                cheese[ni][nj] = -1;
                visited[ni][nj] = true;
                queue.offer(new Point(ni, nj));
            }
        }
    }

}
