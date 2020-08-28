package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B11370 {

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

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            if (W == 0 && H == 0) break;

            Queue<Point> queue = new LinkedList<>();
            char[][] map = new char[H][W];
            for (int i = 0; i < H; i++) {
                char[] chars = br.readLine().toCharArray();
                for (int j = 0; j < W; j++) {
                    map[i][j] = chars[j];
                    if (map[i][j] == 'S') {
                        queue.offer(new Point(i, j));
                    }
                }
            }

            int[] di = {-1, 1, 0, 0};
            int[] dj = {0, 0, -1, 1};
            while (!queue.isEmpty()) {
                Point q = queue.poll();
                int ci = q.i;
                int cj = q.j;

                for (int d = 0; d < 4; d++) {
                    int ni = ci + di[d];
                    int nj = cj + dj[d];
                    if (ni >= H || ni < 0 || nj >= W || nj < 0 || map[ni][nj] == '.' || map[ni][nj] == 'S') continue;
                    queue.offer(new Point(ni, nj));
                    map[ni][nj] = 'S';
                }
            }

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void printMap(char[][] map, int H, int W) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

}
