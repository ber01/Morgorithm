package Expert;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No7733 {

    static class Point {
        int i;
        int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());
            int max = 0;
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    max = Math.max(map[i][j], max);
                }
            }

            int result = 0;
            for (int i = 0; i <= 100; i++) {
                eatMap(map, i);
                int cnt = bfs(map);
                result = Math.max(cnt, result);
                if (i == max) break;
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static int bfs(int[][] map) {
        boolean[][] visited = new boolean[N][N];
        Queue<Point> queue = new LinkedList<>();
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    queue.offer(new Point(i, j));
                    while (!queue.isEmpty()) {
                        Point poll = queue.poll();
                        int ci = poll.i;
                        int cj = poll.j;
                        visited[ci][cj] = true;
                        for (int d = 0; d < 4; d++) {
                            int ni = ci + di[d];
                            int nj = cj + dj[d];
                            if (ni >= N || ni < 0 || nj >= N || nj < 0 || map[ni][nj] == 0 || visited[ni][nj]) continue;
                            queue.offer(new Point(ni, nj));
                            visited[ni][nj] = true;
                        }
                    }
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void eatMap(int[][] map, int day) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == day) {
                    map[i][j] = 0;
                }
            }
        }
    }

}