import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class B11448 {

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
        int[] di = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dj = {0, 0, -1, 1, -1, 1, -1, 1};

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());
            Queue<Point> queue = new LinkedList<>();
            char[][] map = new char[N][N];
            for (int i = 0; i < N; i++) {
                char[] chars = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    map[i][j] = chars[j];
                    if (map[i][j] == 'w') {
                        queue.offer(new Point(i, j));
                    }
                }
            }

            int cnt = 0;
            while (!queue.isEmpty()) {
                Point p = queue.poll();
                int ci = p.i;
                int cj = p.j;

                for (int d = 0; d < 8; d++) {
                    int ni = ci + di[d];
                    int nj = cj + dj[d];
                    if (ni >= N || ni < 0 || nj >= N || nj < 0 || map[ni][nj] == 'b' || map[ni][nj] == 'w') continue;
                    map[ni][nj] = 'w';
                    queue.offer(new Point(ni, nj));
                    cnt++;
                }
            }
            sb.append(cnt).append("\n");
        }
        bw.write(sb.toString().trim());
        bw.flush();
    }

}
