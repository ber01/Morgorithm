package BOJ;

import java.io.*;
import java.util.*;

public class B13424 {

    static class Node implements Comparable<Node> {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    static int N, M, K;
    static List<Node>[] adjList;
    static int[][] dist;
    static boolean[] visited;
    static int[] friends;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            init(br);
            for (int i = 0; i < K; i++) {
                dijkstra(i, friends[i]);
            }
            sb.append(calc()).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static int calc() {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 1; i < N + 1; i++) {
            int sum = 0;
            for (int j = 0; j < K; j++) {
                sum += dist[j][i];
            }
            if (min > sum) {
                min = sum;
                index = i;
            }
        }
        return index;
    }

    private static void dijkstra(int i, int S) {
        Arrays.fill(dist[i], Integer.MAX_VALUE);
        visited = new boolean[N + 1];

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Node(S, 0));
        dist[i][S] = 0;

        while (!priorityQueue.isEmpty()) {
            Node cur = priorityQueue.poll();
            if (visited[cur.vertex]) continue;
            visited[cur.vertex] = true;

            for (Node next : adjList[cur.vertex]) {
                if (dist[i][next.vertex] > dist[i][cur.vertex] + next.weight) {
                    dist[i][next.vertex] = dist[i][cur.vertex] + next.weight;
                    priorityQueue.offer(new Node(next.vertex, dist[i][next.vertex]));
                }
            }
        }

    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) adjList[i] = new ArrayList<>();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjList[a].add(new Node(b, c));
            adjList[b].add(new Node(a, c));
        }
        K = Integer.parseInt(br.readLine().trim());
        friends = new int[K];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < K; i++) friends[i] = Integer.parseInt(st.nextToken());
        dist = new int[K][N + 1];
    }

}
