import java.io.*;
import java.util.*;

public class No1258 {

    public static class Point implements Comparable<Point> {
        public int i;
        public int j;
        public int size;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
            this.size = this.i * this.j;
        }

        @Override
        public String toString() {
            return "(" + i + ", " + j + ")";
        }

        @Override
        public int compareTo(Point o) {
            if (this.size > o.size) {
                return 1;
            }
            if (this.size == o.size) {
                if (this.i > o.i) {
                    return -1;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[] di = {-1, 1, 0, 0};
        int[] dj = {0, 0, -1, 1};

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine().trim());
            int[][] map = new int[N][N];
            boolean[][] visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            List<Point> locationList = new LinkedList<>();
            Queue<Point> queue = new LinkedList<>();
            int cnt = 0;
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    Deque<Point> deque = new LinkedList<>();
                    if (map[i][j] != 0 && !visited[i][j]) {
                        queue.offer(new Point(i, j));
                        while (!queue.isEmpty()) {
                            Point point = queue.poll();
                            deque.offer(point);
                            int ci = point.i;
                            int cj = point.j;
                            visited[ci][cj] = true;
                            for (int d = 0; d < 4; d++) {
                                int ni = ci + di[d];
                                int nj = cj + dj[d];
                                if (ni >= N || ni < 0 || nj >= N || nj < 0 || map[ni][nj] == 0 || visited[ni][nj]) continue;
                                queue.offer(new Point(ni, nj));
                                visited[ni][nj] = true;
                            }
                        }
                        Point start = deque.removeFirst();
                        Point end = deque.removeLast();
                        locationList.add(new Point(end.i - start.i + 1, end.j - start.j + 1));
                        cnt++;
                    }
                }
            }

            result.append(cnt).append(" ");
            Collections.sort(locationList);
            for (Point location : locationList) {
                result.append(location.i).append(" ").append(location.j).append(" ");
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString().trim());
        bw.flush();
    }

}
