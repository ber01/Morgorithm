package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B8061 {

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
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        Queue<Point> queue = new LinkedList<>();
        int[][] map = new int[W][H];
        for (int i = 0; i < W; i++) {
            char[] chars = br.readLine().trim().toCharArray();
            for (int j = 0; j < H; j++) {
                map[i][j] = chars[j] - '0';
                if (map[i][j] == 1) {
                    queue.offer(new Point(i, j));
                }
            }
        }

        int[] di = {-1, 1, 0, 0};
        int[] dj = {0, 0, -1, 1};
        int dist = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point point = queue.poll();
                int ci = point.i;
                int cj = point.j;;
                for (int d = 0; d < 4; d++) {
                    int ni = ci + di[d];
                    int nj = cj + dj[d];
                    if (ni >= W || ni < 0 || nj >= H || nj < 0 || map[ni][nj] != 0) continue;
                    map[ni][nj] = dist + 1;
                    queue.offer(new Point(ni, nj));
                }
            }
            dist++;
        }

        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                map[i][j]--;
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

}
