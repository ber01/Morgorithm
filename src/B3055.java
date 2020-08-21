import java.io.*;
import java.util.*;

public class B3055 {

    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "(" + i + ", " + j + ")" ;
        }
    }

    static int R, C;
    static char[][] map;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        Point sPoint = null;

        Queue<Point> water = new LinkedList<>();
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine().trim();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'S') sPoint = new Point(i, j);
                else if (map[i][j] == '*') water.offer(new Point(i, j));
            }
        }

        bw.write(bfs(water, sPoint));
        bw.flush();
    }

    private static String bfs(Queue<Point> water, Point sPoint) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        boolean isFlag = water.size() == 0;

        Queue<Point> tempQueue = new LinkedList<>();
        while (!water.isEmpty()) {
            Point poll = water.poll();
            queue.offer(poll);
            tempQueue.offer(poll);
            visited[poll.i][poll.j] = true;
        }

        List<List<Point>> lst = null;
        if (!isFlag) {
            lst = new ArrayList<>();
            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Point> temp = new ArrayList<>();
                while (size-- > 0) {
                    Point poll = queue.poll();
                    int ci = poll.i;
                    int cj = poll.j;
                    for (int d = 0; d < 4; d++) {
                        int ni = ci + di[d];
                        int nj = cj + dj[d];
                        if (ni < 0 || ni >= R || nj < 0 || nj >= C || map[ni][nj] == 'X' || map[ni][nj] == 'D' || visited[ni][nj]) continue;
                        Point nPoint = new Point(ni, nj);
                        queue.offer(nPoint);
                        visited[ni][nj] = true;
                        temp.add(nPoint);
                    }
                }
                lst.add(temp);
            }
        }

        queue = new LinkedList<>();
        queue.offer(sPoint);
        visited = new boolean[R][C];
        visited[sPoint.i][sPoint.j] = true;
        while (!tempQueue.isEmpty()) {
            Point poll = tempQueue.poll();
            visited[poll.i][poll.j] = true;
        }

        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (!isFlag && dist <= lst.size() - 1) {
                List<Point> points = lst.get(dist);
                for (Point p : points) visited[p.i][p.j] = true;
            }
            while (size-- > 0) {
                Point poll = queue.poll();
                int ci = poll.i;
                int cj = poll.j;
                if (map[ci][cj] == 'D') return String.valueOf(dist);
                for (int d = 0; d < 4; d++) {
                    int ni = ci + di[d];
                    int nj = cj + dj[d];
                    if (ni < 0 || ni >= R || nj < 0 || nj >= C || map[ni][nj] == 'X' || visited[ni][nj]) continue;
                    queue.offer(new Point(ni, nj));
                    visited[ni][nj] = true;
                }
            }
            dist++;
        }

        return "KAKTUS" ;
    }

}
