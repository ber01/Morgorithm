package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16948 {

    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    static int N;
    static boolean[][] visited;
    static Point start, end;
    static int[] di = {-2, -2, 0, 0, 2, 2};
    static int[] dj = {-1, 1, -2, 2, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);

        bw.write(bfs() + "");
        bw.flush();
    }

    private static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.i][start.j] = true;

        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Point cur = queue.poll();
                int ci = cur.i;
                int cj = cur.j;
                if (ci == end.i && cj == end.j) return dist;
                for (int d = 0; d < 6; d++) {
                    int ni = ci + di[d];
                    int nj = cj + dj[d];
                    if (ni < 0 || ni >= N || nj < 0 || nj >= N || visited[ni][nj]) continue;
                    queue.offer(new Point(ni, nj));
                    visited[ni][nj] = true;
                }
            }
            dist++;
        }

        return -1;
    }

    private static void init(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        visited = new boolean[N][N];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }

}
