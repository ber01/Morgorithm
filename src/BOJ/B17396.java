package BOJ;

import java.io.*;
import java.util.*;

public class B17396 {

    static class Node implements Comparable<Node> {
        int vertex;
        long time;

        public Node(int vertex, long time) {
            this.vertex = vertex;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.time, o.time);
        }
    }
    static int N, M;
    static int[] vision;
    static List<Node>[] adjList;
    static long[] dist;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);
        dijkstra();

        bw.write(String.valueOf(dist[N - 1] == Long.MAX_VALUE ? -1 : dist[N - 1]));
        bw.flush();
    }

    private static void dijkstra() {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Node(0, 0));
        dist[0] = 0;

        while (!priorityQueue.isEmpty()) {
            Node cur = priorityQueue.poll();

            if (visited[cur.vertex]) continue;
            visited[cur.vertex] = true;

            for (Node next : adjList[cur.vertex]) {
                if (dist[next.vertex] > dist[cur.vertex] + next.time) {
                    dist[next.vertex] = dist[cur.vertex] + next.time;
                    priorityQueue.offer(new Node(next.vertex, dist[next.vertex]));
                }
            }
        }
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        vision = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) vision[i] = Integer.parseInt(st.nextToken());
        vision[N - 1] = 0;
        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) adjList[i] = new ArrayList<>();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            if (vision[a] == 0) adjList[a].add(new Node(b, t));
            if (vision[b] == 0) adjList[b].add(new Node(a, t));
        }
        dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);
        visited = new boolean[N];
    }

}
