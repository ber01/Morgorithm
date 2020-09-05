package BOJ;

import java.io.*;
import java.util.*;

public class B16236 {

    static class Point implements Comparable<Point> {
        int i, j, size, cnt;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public Point(int i, int j, int size) {
            this.i = i;
            this.j = j;
            this.size = size;
        }

        public Point(int i, int j, int size, int cnt) {
            this.i = i;
            this.j = j;
            this.size = size;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "(" + i + ", " + j + ")[" + size + "]{" + cnt + "}";
        }

        @Override
        public int compareTo(Point o) {
            if (this.size == o.size) {
                if (this.i == o.i) {
                    return this.j - o.j;
                } else {
                    return this.i - o.i;
                }
            }
            return this.size - o.size;
        }
    }
    static int N, result;
    static int[][] map;
    static Point babyShark;
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, -1, 0, 1};
    static List<Point> secList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);
        solve();

        bw.write( result + "");
        bw.flush();
    }

    private static void solve() {
        // 9였던 좌표를 0으로 초기화
        map[babyShark.i][babyShark.j] = 0;

        // 필요한 값을 얻을 수 없을 때 까지 무한반복
        while (true) {
            secList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 빈 칸이면 pass, 물고기가 아기상어의 크기보다 크거나 같으면 pass
                    if (map[i][j] == 0 || map[i][j] >= babyShark.size) continue;
                    distanceCheck(i, j);
                }
            }
            // 거리를 측정한 것 들 중 거리가 가장 가까우며, 가장 위쪽, 가장 왼쪽에 있는 물고기를 먹는다. 먹은 물고기의 자리를 아기상어의 좌표로 초기화 한다.
            if (secList.size() > 0) {
                Collections.sort(secList);
                Point point = secList.get(0);
                result += point.size;
                map[point.i][point.j] = 0; // 물고기를 먹었다.
                int size = babyShark.size;
                int cnt = babyShark.cnt + 1;
                if (size == cnt) {
                    size += 1;
                    cnt = 0;
                }
                babyShark = new Point(point.i, point.j, size, cnt); // 아기상어의 좌표를 먹은 물고기의 좌표로 바꾼다.
            } else return; // 먹을 물고기가 없으면 엄마 상어에게 도움을 요청한다
        }

    }

    private static void distanceCheck(int i, int j) {
        // bfs를 돌려서 가능한 거리의 좌표를 넣는다.
        int temp = bfs(i, j);
        // 갈 수 없는 곳은 좌표에 넣지 않는다.
        if (temp == -1) return;
        secList.add(new Point(i, j, temp));
    }

    private static int bfs(int i, int j) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.offer(new Point(babyShark.i, babyShark.j));
        int sec = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Point poll = queue.poll();
                int ci = poll.i;
                int cj = poll.j;
                if (ci == i && cj == j) return sec;
                visited[ci][cj] = true;
                for (int d = 0; d < 4; d++) {
                    int ni = ci + di[d];
                    int nj = cj + dj[d];
                    if (ni >= N || ni < 0 || nj >= N || nj < 0 || visited[ni][nj] || map[ni][nj] > babyShark.size) continue;
                    visited[ni][nj] = true;
                    queue.offer(new Point(ni, nj));
                }
            }
            sec++;
        }

        // 여기 왔다는 것은 해당하는 좌표로 이동할 수 없는 경우를 뜻하므로 -1을 리턴한다.
        return -1;
    }


    private static void init(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) babyShark = new Point(i, j, 2, 0);
            }
        }
    }

}

