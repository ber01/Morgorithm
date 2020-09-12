package BOJ;

import java.io.*;
import java.util.*;

public class B2583 {

    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    static int M, N, K;
    static int[][] map;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);
        List<Integer> lst = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    lst.add(bfs(i, j));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(lst.size()).append("\n");
        Collections.sort(lst);
        for (int next : lst) sb.append(next).append(" ");

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static int bfs(int i, int j) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(i, j));
        visited[i][j] = true;

        int result = 1;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int ci = cur.i;
            int cj = cur.j;
            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if (ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] == 1 || visited[ni][nj]) continue;
                queue.offer(new Point(ni, nj));
                visited[ni][nj] = true;
                result++;
            }
        }

        return result;
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    map[i][j] = 1;
                }
            }
        }
        visited = new boolean[N][M];
    }
}
