import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No6186 {

    public static class Point {
        public int i;
        public int j;
        public int size;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
            this.size = this.i * this.j;
        }
    }

    private static int R;
    private static int C;
    private static final int[] di = {-1, 1, 0, 0};
    private static final int[] dj = {0, 0, -1, 1};
    private static char[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0 ; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int cnt = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '#' && !visited[i][j]) {
                    bfs(i, j);
                    cnt++;
                }
            }
        }

        bw.write(String.valueOf(cnt));
        bw.flush();
    }

    private static void bfs(int i, int j) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(i, j));

        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int ci = poll.i;
            int cj = poll.j;
            visited[ci][cj] = true;

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if (ni >= R || ni < 0 || nj >= C || nj < 0 || map[ni][nj] == '.' || visited[ni][nj]) continue;
                visited[ni][nj] = true;
                queue.offer(new Point(ni, nj));
            }
        }
    }

}
