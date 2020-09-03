package BOJ;

import java.io.*;
import java.util.*;

public class B1647 {

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

        @Override
        public String toString() {
            return "(" + vertex + ", " + weight + ")";
        }
    }
    static int N, M, S, max, ans;
    static List<Node>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);
        prim();

        bw.write(String.valueOf(ans - max));
        bw.flush();
    }

    private static void prim() {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        queue.offer(S);
        visited[S] = true;

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            List<Node> nodes = adjList[vertex];

            for (Node node : nodes) {
                if (visited[node.vertex]) continue;
                priorityQueue.offer(node);
            }

            while (!priorityQueue.isEmpty()) {
                Node node = priorityQueue.poll();
                if (visited[node.vertex]) continue;
                visited[node.vertex] = true;
                queue.offer(node.vertex);
                max = Math.max(max, node.weight);
                ans += node.weight;
                break;
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
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            adjList[A].add(new Node(B, C));
            adjList[B].add(new Node(A, C));
        }
        ans = 0;
        max = Integer.MIN_VALUE;
        for (int i = 1; i < N + 1; i++) {
            if (adjList[i].size() != 0) {
                S = i;
                return;
            }
        }
    }

}
