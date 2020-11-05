package BOJ;

import java.io.*;
import java.util.*;

public class B16988 {

    static int N, M, MAX;
    static int[] result;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int[][] map;
    static List<Point> lst;
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

        init(br);

        result = new int[2];
        backtrack(0, 0);

        bw.write(MAX + "");
        bw.flush();
    }

    private static void backtrack(int idx, int cnt) {
        if (cnt == 2) {
            Point p1 = lst.get(result[0]);
            Point p2 = lst.get(result[1]);
            map[p1.i][p1.j] = 1;
            map[p2.i][p2.j] = 1;
            check();
            map[p1.i][p1.j] = 0;
            map[p2.i][p2.j] = 0;
            return;
        }

        for (int i = idx; i < lst.size(); i++) {
            result[cnt] = i;
            backtrack(i + 1, cnt + 1);
        }
    }

    private static void check() {
        int ans = 0;
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2 && !visited[i][j]) {
                    List<Point> bfs = bfs(new Point(i, j), visited);
                    ans += cnt(bfs);
                }
            }
        }

        MAX = Math.max(MAX, ans);
    }

    private static int cnt(List<Point> bfs) {
        for (Point next : bfs) {
            int ci = next.i;
            int cj = next.j;
            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if (ni >= N || ni < 0 || nj >= M || nj < 0) continue;
                if (map[ni][nj] == 0) return 0;
            }
        }

        return bfs.size();
    }

    private static List<Point> bfs(Point point, boolean[][] visited) {
        List<Point> temp = new ArrayList<>();
        temp.add(point);
        visited[point.i][point.j] = true;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(point);

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int ci = cur.i;
            int cj = cur.j;

            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if (ni >= N || ni < 0 || nj >= M || nj < 0 || map[ni][nj] != 2 || visited[ni][nj]) continue;
                visited[ni][nj] = true;
                queue.offer(new Point(ni, nj));
                temp.add(new Point(ni, nj));
            }
        }

        return temp;
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        lst = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    lst.add(new Point(i, j));
                }
            }
        }
    }

}
