package BOJ;

import java.io.*;
import java.util.*;

public class B1916 {

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
    static int N, M, S, E;
    static List<Node>[] adjList;
    static int[] dist;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);
        dijkstra();

        bw.write(String.valueOf(dist[E]));
        bw.flush();
    }

    private static void dijkstra() {
        dist[S] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(S, 0));

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
        N = Integer.parseInt(br.readLine().trim());
        M = Integer.parseInt(br.readLine().trim());
        adjList = new ArrayList[N + 1];
        dist = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++) adjList[i] = new ArrayList<>();
        Arrays.fill(dist, Integer.MAX_VALUE);

        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[start].add(new Node(end, weight));
        }

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
    }

}

