package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7576 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[M][N];
        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; st.hasMoreTokens(); j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    queue.offer(new Point(i, j));
                }
            }
        }

        int[] di = {-1, 1, 0, 0};
        int[] dj = {0, 0, -1, 1};
        int max = 0;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int nowi = p.i;
            int nowj = p.j;

            for (int d = 0; d < 4; d++) {
                int ni = nowi + di[d];
                int nj = nowj + dj[d];
                if (ni >= M || ni < 0 || nj >= N || nj < 0 || arr[ni][nj] != 0) continue;
                arr[ni][nj] = arr[nowi][nowj] + 1;
                max = Math.max(max, arr[ni][nj]);
                queue.offer(new Point(ni, nj));
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 0) {
                    max = -1;
                    break;
                }
            }
        }

        max = max == 0 ? 0 : max == -1 ? -1 : max - 1;

        bw.write(max + "");
        bw.flush();
    }

    private static class Point {
        int i;
        int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

}
