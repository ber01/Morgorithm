import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7562 {
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

    static final int[] di = {-2, -2, 2, 2, -1, -1, 1, 1};
    static final int[] dj = {-1, 1, -1, 1, -2, 2, -2, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());
            int[][] map = new int[N][N];
            boolean[][] visited = new boolean[N][N];
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine().trim());
            Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            sb.append(bfs(map, visited, N, start, end)).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static int bfs(int[][] map, boolean[][] visited, int N, Point start, Point end) {
        map[start.i][start.j] = 1;
        map[end.i][end.j] = 2;

        if (map[start.i][start.j] == 2) {
            return 0;
        }

        int cnt = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(start.i, start.j));
        boolean isFlag = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                Point poll = queue.poll();
                int ci = poll.i;
                int cj = poll.j;
                for (int d = 0; d < di.length; d++) {
                    int ni = ci + di[d];
                    int nj = cj + dj[d];
                    if (ni >= N || ni < 0 || nj >= N || nj < 0 || visited[ni][nj]) continue;
                    if (map[ni][nj] == 2){
                        isFlag = true;
                        break;
                    }
                    queue.offer(new Point(ni, nj));
                    visited[ni][nj] = true;
                }
                if (isFlag) break;
            }
            cnt++;
            if (isFlag) break;
        }

        return cnt;
    }

}
