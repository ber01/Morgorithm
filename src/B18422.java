import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B18422 {

    private static class Point {
        int i;
        int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int[] di = {-1, 1, 0, 0};
        int[] dj = {0, 0, -1, 1};

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        Queue<Point> queue = new LinkedList<>();
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '*' && !visited[i][j]) {
                    queue.offer(new Point(i, j));
                    while (!queue.isEmpty()) {
                        Point point = queue.poll();
                        int ci = point.i;
                        int cj = point.j;
                        visited[ci][cj] = true;

                        for (int d = 0; d < 4; d++) {
                            int ni = ci + di[d];
                            int nj = cj + dj[d];
                            if (ni >= N || ni < 0 || nj >= M || nj < 0 || map[ni][nj] == '.' || visited[ni][nj]) continue;
                            queue.offer(new Point(ni, nj));
                            visited[ni][nj] = true;
                        }
                    }
                    result++;
                }
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
    }

}
