package BOJ;

import java.io.*;
import java.util.*;

public class B5972 {

    static class Node implements Comparable<Node> {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
    static int N, M;
    static List<Node>[] adjList;
    static int[] dist;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);
        dijkstra();

        bw.write(String.valueOf(dist[N]));
        bw.flush();
    }

    private static void dijkstra() {
        dist[1] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(1, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (visited[cur.vertex]) continue;
            visited[cur.vertex] = true;

            for (Node next : adjList[cur.vertex]) {
                if (dist[next.vertex] > dist[cur.vertex] + next.weight) {
                    dist[next.vertex] = dist[cur.vertex] + next.weight;
                    queue.offer(new Node(next.vertex, dist[next.vertex]));
                }
            }
        }
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N + 1];
        dist = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i < N + 1; i++) adjList[i] = new ArrayList<>();
        Arrays.fill(dist, Integer.MAX_VALUE);

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[u].add(new Node(v, w));
            adjList[v].add(new Node(u, w));
        }
    }

}
