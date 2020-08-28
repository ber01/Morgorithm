package BOJ;

import java.io.*;
import java.util.*;

public class B3187 {

    static class Point {
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

    static final int[] di = {-1, 1, 0, 0};
    static final int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        boolean[][] visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        bw.write(bfs(map, visited, R, C));
        bw.flush();
    }

    private static String bfs(char[][] map, boolean[][] visited, int R, int C) {
        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                List<Point> oPoint = new ArrayList<>();
                List<Point> vPoint = new ArrayList<>();
                if (map[i][j] == 'k' && !visited[i][j]) {
                    queue.offer(new Point(i, j));
                    visited[i][j] = true;
                    oPoint.add(new Point(i, j));
                    while (!queue.isEmpty()) {
                        Point poll = queue.poll();
                        int ci = poll.i;
                        int cj = poll.j;
                        for (int d = 0; d < 4; d++) {
                            int ni = ci + di[d];
                            int nj = cj + dj[d];
                            if (ni >= R || ni < 0 || nj >= C || nj < 0 || map[ni][nj] == '#' || visited[ni][nj]) continue;
                            if (map[ni][nj] == 'k') oPoint.add(new Point(ni, nj));
                            if (map[ni][nj] == 'v') vPoint.add(new Point(ni, nj));
                            queue.offer(new Point(ni, nj));
                            visited[ni][nj] = true;
                        }
                    }
                }
                if (oPoint.size() != 0 && vPoint.size() != 0) {
                    if (oPoint.size() > vPoint.size()) {
                        for (Point v : vPoint) {
                            map[v.i][v.j] = '.';
                        }
                    } else {
                        for (Point o : oPoint) {
                            map[o.i][o.j] = '.';
                        }
                    }
                }
            }
        }

        int oCnt = 0;
        int vCnt = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'k') oCnt++;
                if (map[i][j] == 'v') vCnt++;
            }
        }

        return oCnt + " " + vCnt;
    }

}
