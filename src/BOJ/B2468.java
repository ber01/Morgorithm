package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2468 {

    private static class Point {
        int i;
        int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private static final int[] di = {-1, 1, 0, 0};
    private static final int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine().trim());
        int[][] mapOrigin = new int[N][N];
        int height = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                mapOrigin[i][j] = Integer.parseInt(st.nextToken());
                height = Math.max(mapOrigin[i][j], height);
            }
        }

        int max = 0;
        for (int h = 0; h < height; h++) {
            int[][] map = makeMap(mapOrigin);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (h >= map[i][j]) {
                        map[i][j] = 0;
                    }
                }
            }

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] != 0 && map[i][j] != -1) {
                        dfs(map, i, j);
                        // bfs(map, i, j);
                        cnt++;
                    }
                }
            }

            max = Math.max(max, cnt);
        }

        bw.write(String.valueOf(max));
        bw.flush();
    }

    private static void dfs(int[][] map, int ci, int cj) {
        int N = map.length;
        map[ci][cj] = -1;

        for (int d = 0; d < 4; d++) {
            int ni = ci + di[d];
            int nj = cj + dj[d];
            if (ni >= N || ni < 0 || nj >= N || nj < 0 || map[ni][nj] == 0 || map[ni][nj] == -1) continue;
            dfs(map, ni, nj);
        }
    }

    private static void bfs(int[][] map, int i, int j) {
        int N = map.length;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(i, j));

        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            int ci = poll.i;
            int cj = poll.j;
            map[ci][cj] = -1;

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if (ni >= N || ni < 0 || nj >= N || nj < 0 || map[ni][nj] == 0 || map[ni][nj] == -1) continue;
                queue.offer(new Point(ni, nj));
                map[ni][nj] = -1;
            }
        }
    }

    private static int[][] makeMap(int[][] mapOrigin) {
        int N = mapOrigin.length;
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(mapOrigin[i], 0, temp[i], 0, N);
        }
        return temp;
    }

}
