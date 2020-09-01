package BOJ;

import java.io.*;
import java.util.*;

public class B1753_list {

    static class Node implements Comparable<Node> {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + vertex + ", " + weight + ")";
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
    static int V, E, K;
    static List<Node>[] adjList;
    static int[] dist;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);
        dijkstra();

        bw.write(print());
        bw.flush();
    }

    private static void dijkstra() {
        dist[K] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(K, 0));

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
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine().trim());

        adjList = new ArrayList[V + 1];
        for (int i = 1; i < V +1; i++) {
            adjList[i] = new ArrayList<>();
        }

        while (E-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[u].add(new Node(v, w));
        }

        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        visited = new boolean[V + 1];
    }

    private static String print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < V + 1; i++) sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
        return sb.toString().trim();
    }

}
