package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B4963 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int[] di = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dj = {0, 0, -1, 1, -1, 1, -1, 1};

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            if (W == 0 && H == 0) break;
            int[][] arr = new int[H][W];
            boolean[][] visited = new boolean[H][W];

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < W; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int cnt = 0;
            Queue<Point> queue = new LinkedList<>();
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (arr[i][j] == 0 || visited[i][j]) continue;
                    queue.offer(new Point(i, j));
                    visited[i][j] = true;

                    while (!queue.isEmpty()) {
                        Point p = queue.poll();
                        visited[p.i][p.j] = true;

                        int nowi = p.i;
                        int nowj = p.j;
                        for (int d = 0; d < di.length; d++) {
                            int ni = nowi + di[d];
                            int nj = nowj + dj[d];
                            if (ni >= H || ni < 0 || nj >= W || nj < 0 || arr[ni][nj] == 0 || visited[ni][nj]) continue;
                            visited[ni][nj] = true;
                            queue.offer(new Point(ni, nj));
                        }
                    }
                    cnt++;
                }
            }
            sb.append(cnt).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static class Point {
        int i;
        int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "(" + i + ", " + j + ")";
        }
    }

}
