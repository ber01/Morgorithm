package Expert;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No1953 {

    static int N, M, R, C, L;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visited;
    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            init(br);

            bfs();

            sb.append("#").append(t).append(" ").append(cntVisited()).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        add(queue, R, C);

        int hour = 1;

        if (L == 1) return;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                Point cur = queue.poll();
                int ci = cur.i;
                int cj = cur.j;

                switch (map[ci][cj]) {
                    case 1: { // ┼ 0, 1, 2, 3
                        int[] arr = {0, 1, 2, 3};
                        for (int d : arr) solve(queue, ci + di[d], cj + dj[d], d);
                    } break;
                    case 2: { // │ 0, 1
                        int[] arr = {0, 1};
                        for (int d : arr) solve(queue, ci + di[d], cj + dj[d], d);
                    } break;
                    case 3: { // ─ 2, 3
                        int[] arr = {2, 3};
                        for (int d : arr) solve(queue, ci + di[d], cj + dj[d], d);
                    } break;
                    case 4: { // └ 0, 3
                        int[] arr = {0, 3};
                        for (int d : arr) solve(queue, ci + di[d], cj + dj[d], d);
                    } break;
                    case 5: { // ┌ 1, 3
                        int[] arr = {1, 3};
                        for (int d : arr) solve(queue, ci + di[d], cj + dj[d], d);
                    } break;
                    case 6: { // ┐ 1, 2
                        int[] arr = {1, 2};
                        for (int d : arr) solve(queue, ci + di[d], cj + dj[d], d);
                    } break;
                    case 7: { // ┘ 0, 2
                        int[] arr = {0, 2};
                        for (int d : arr) solve(queue, ci + di[d], cj + dj[d], d);
                    } break;
                }
            }

            if (L == ++hour) return;
        }
    }

    private static void solve(Queue<Point> queue, int ni1, int nj1, int d) {
        int ni = ni1;
        int nj = nj1;
        if (isValid(ni, nj)) return;
        if (d == 0) { // 상
            if (map[ni][nj] == 1 || map[ni][nj] == 2 || map[ni][nj] == 5 || map[ni][nj] == 6) add(queue, ni, nj);
        } else if (d == 1) { // 하
            if (map[ni][nj] == 1 || map[ni][nj] == 2 || map[ni][nj] == 4 || map[ni][nj] == 7) add(queue, ni, nj);
        } else if (d == 2) { // 좌
            if (map[ni][nj] == 1 || map[ni][nj] == 3 || map[ni][nj] == 4 || map[ni][nj] == 5) add(queue, ni, nj);
        } else if (d == 3) { // 우
            if (map[ni][nj] == 1 || map[ni][nj] == 3 || map[ni][nj] == 6 || map[ni][nj] == 7) add(queue, ni, nj);
        }
    }

    private static void add(Queue<Point> queue, int ni, int nj) {
        queue.offer(new Point(ni, nj));
        visited[ni][nj] = true;
    }

    private static boolean isValid(int ni, int nj) {
        return ni >= N || ni < 0 || nj >= M || nj < 0 || visited[ni][nj];
    }

    private static int cntVisited() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

}

