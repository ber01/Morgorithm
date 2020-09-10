package BOJ;

import java.io.*;
import java.util.*;

public class B14502 {

    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "(" + i + ", " + j + ")";
        }
    }
    static int N, M, max;
    static int[][] map;
    static int[] num;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static List<Point> zeroList;
    static Queue<Point> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);
        backtrack(0, 0);

        bw.write(max + "");
        bw.flush();
    }

    private static void backtrack(int idx, int cnt) {
        if (cnt == 3) {
            int[][] copy = copyMap();
            for (int i = 0; i < 3; i++) {
                Point point = zeroList.get(num[i]);
                int x = point.i;
                int y = point.j;
                copy[x][y] = 1;
            }
            bfs(copy);
            max = Math.max(max, checkArea(copy));
            return;
        }
        for (int i = idx; i < zeroList.size(); i++) {
            num[cnt] = i;
            backtrack(i + 1, cnt + 1);
        }
    }

    private static int checkArea(int[][] copy) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void bfs(int[][] copy) {
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int ci = cur.i;
            int cj = cur.j;
            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if (ni >= N || ni < 0 || nj >= M || nj < 0 || copy[ni][nj] == 2 || copy[ni][nj] == 1) continue;
                copy[ni][nj] = 2;
                queue.offer(new Point(ni, nj));
            }
        }
    }

    private static int[][] copyMap() {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = map[i][j];
                if (copy[i][j] == 2) {
                    queue.offer(new Point(i, j));
                }
            }
        }
        return copy;
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        zeroList = new ArrayList<>();
        queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    zeroList.add(new Point(i, j));
                }
            }
        }
        num = new int[3];
        max = Integer.MIN_VALUE;
    }

}
