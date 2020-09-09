package BOJ;

import java.io.*;
import java.util.*;

/*
1. 섬들을 정점으로 만든다.
2. 간선 만들기
 */
public class B17472 {

    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static class Edge implements Comparable<Edge> {
        int st, end, dist;

        public Edge(int st, int end, int dist) {
            this.st = st;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "st=" + st +
                    ", end=" + end +
                    ", dist=" + dist +
                    '}';
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    static int N, M, bridgeLength, E;
    static int[][] map;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int[] disjoint;
    static PriorityQueue<Edge> priorityQueue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);
        step1();
        step2();
        step3();

        bw.write(bridgeLength + "");
        bw.flush();
    }

    private static void step3() {
        List<Edge> mst = new ArrayList<>();
        while (!priorityQueue.isEmpty() && mst.size() < E - 1) {
            Edge edge = priorityQueue.poll();
            if (union(edge.st, edge.end)) {
                mst.add(edge);
            }
        }
        if (mst.size() == E - 1) {
            for (Edge e : mst) {
                bridgeLength += e.dist;
            }
        } else {
            bridgeLength = -1;
        }
    }

    // 섬들에서 간선을 뻗는 함수, 섬의 모든 땅에서 간선이 출발한다.
    private static void step2() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) { // 섬에 존재하는 땅일 경우, 상, 하, 좌, 우로 간선을 뻗는다.
                    int land = map[i][j]; // 간선이 출발하는 정보 기록, 간선을 뻗었는데 같은 섬에 도착하면 패스하여야 한다.
                    up(i, j, land);
                    down(i, j, land);
                    right(i, j, land);
                    left(i, j, land);
                }
            }
        }
    }

    private static void left(int i, int j, int land) {
        int dist;
        int tj;
        tj = j - 1;
        dist = 0;
        while (tj >= 0 && map[i][tj] == 0) {
            dist++;
            tj--;
        }
        if (tj >= 0 && dist > 1 && land != map[i][tj]) {
            priorityQueue.offer(new Edge(land, map[i][tj], dist));
        }
    }

    private static void right(int i, int j, int land) {
        int dist;
        int tj;
        tj = j + 1;
        dist = 0;
        while (tj < M && map[i][tj] == 0) {
            dist++;
            tj++;
        }
        if (tj < M && dist > 1 && land != map[i][tj]) {
            priorityQueue.offer(new Edge(land, map[i][tj], dist));
        }
    }

    private static void down(int i, int j, int land) {
        int dist;
        int ti;
        ti = i + 1;
        dist = 0;
        while (ti < N && map[ti][j] == 0) {
            dist++;
            ti++;
        }
        if (ti < N && dist > 1 && map[ti][j] != land) {
            // 출발점, 도착점, 간선의 길이 삽입
            priorityQueue.offer(new Edge(land, map[ti][j], dist));
        }
    }

    private static void up(int i, int j, int land) {
        int dist;
        int ti;
        ti = i - 1;
        dist = 0;
        while (ti >= 0 && map[ti][j] == 0) {
            dist++;
            ti--;
        }
        // 유효 범위 검사 (배열 밖, 길이가 2이상, 출발섬과 도착섬이 같은지 확인
        if (ti >= 0 && dist > 1 && map[ti][j] != land) {
            // 출발점, 도착점, 간선의 길이 삽입
            priorityQueue.offer(new Edge(land, map[ti][j], dist));
        }
    }

    // 섬에 번호 붙이기
    private static void step1() {
        int landCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    landCnt++;
                    bfs(new Point(i, j), landCnt);
                }
            }
        }
        // printMap();
        E = landCnt;
        disjoint = new int[landCnt + 1];
        for (int i = 0; i < disjoint.length; i++) disjoint[i] = i;
    }

    private static void bfs(Point point, int landCnt) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(point);
        visited[point.i][point.j] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int ci = cur.i;
            int cj = cur.j;
            map[ci][cj] = landCnt;
            for (int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if (ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] == 0 || visited[ni][nj]) continue;
                visited[ni][nj] = true;
                queue.offer(new Point(ni, nj));
            }
        }
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        priorityQueue = new PriorityQueue<>();
    }

    static int find(int a) {
        if (a == disjoint[a]) return a;
        return disjoint[a] = find(disjoint[a]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        disjoint[a] = b;
        return true;
    }

}
