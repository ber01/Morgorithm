package Expert;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No1824 {

    static int R, C;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static char[][] map;
    static String result;
    static class Point {
        int i, j, dir, memory;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public Point(int i, int j, int dir, int memory) {
            this.i = i;
            this.j = j;
            this.dir = dir;
            this.memory = memory;
        }
    }
    static boolean[][][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            init(br);

            bfs();

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, 3, 0));
        visited[0][0][3][0] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int ci = cur.i;
            int cj = cur.j;
            int dir = cur.dir;
            int memory = cur.memory;

            if (map[ci][cj] >= '0' && map[ci][cj] <= '9') memory = map[ci][cj] - '0';
            if (map[ci][cj] == '<' || (map[ci][cj] == '_' && memory != 0)) dir = 2;
            else if (map[ci][cj] == '>' || (map[ci][cj] == '_' && memory == 0)) dir = 3;
            else if (map[ci][cj] == '^' || (map[ci][cj] == '|' && memory != 0)) dir = 0;
            else if (map[ci][cj] == 'v' || (map[ci][cj] == '|' && memory == 0)) dir = 1;
            else if (map[ci][cj] == '+') {
                if (memory == 15) memory = 0;
                else memory += 1;
            }
            else if (map[ci][cj] == '-') {
                if (memory == 0) memory = 15;
                else memory -= 1;
            }
            else if (map[ci][cj] == '?') {
                for (int d = 0; d < 4; d++) {
                    valid(queue, ci, cj, memory, d);
                }
            }
            else if (map[ci][cj] == '@') {
                result = "YES";
                return;
            }

            valid(queue, ci, cj, memory, dir);
        }

    }

    private static void valid(Queue<Point> queue, int ci, int cj, int memory, int d) {
        int ni = ci + di[d];
        int nj = cj + dj[d];

        if (ni < 0) ni = R - 1;
        else if (ni >= R)ni = 0;
        if (nj < 0) nj = C - 1;
        else if (nj >= C) nj = 0;

        if (visited[ni][nj][d][memory]) return;
        queue.offer(new Point(ni, nj, d, memory));
        visited[ni][nj][d][memory] = true;
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) map[i] = br.readLine().toCharArray();
        result = "NO";
        visited = new boolean[R][C][4][16];
    }
}