import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1012 {

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
        StringBuilder sb = new StringBuilder();

        int[] di = {-1, 1, 0, 0};
        int[] dj = {0, 0, -1, 1};

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[][] map = new int[M][N];
            boolean[][] visited = new boolean[M][N];
            while (K-- > 0) {
                st = new StringTokenizer(br.readLine().trim());
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                map[i][j] = 1;
            }

            Queue<Point> queue = new LinkedList<>();
            int result = 0;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        queue.offer(new Point(i, j));
                        while (!queue.isEmpty()) {
                            Point point = queue.poll();
                            int ci = point.i;
                            int cj = point.j;
                            visited[ci][cj] = true;
                            for (int d = 0; d < 4; d++) {
                                int ni = ci + di[d];
                                int nj = cj + dj[d];
                                if (ni >= M || ni < 0 || nj >= N || nj < 0 || map[ni][nj] == 0 || visited[ni][nj]) continue;
                                queue.offer(new Point(ni, nj));
                                visited[ni][nj] = true;
                            }
                        }
                        result++;
                    }
                }
            }

            sb.append(result).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

}
