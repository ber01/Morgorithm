package Expert;

import java.io.*;
import java.util.*;

public class No1251 {

    static class Node implements Comparable<Node> {
        int vertex;
        long dist;

        public Node(int vertex, long dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "(" + this.vertex + ", " + this.dist +  ")";
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.dist, o.dist);
        }
    }
    static int N;
    static int[] X, Y;
    static double E;
    static List<Node>[] adjList;
    static long ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            init(br);
            prim();
            sb.append("#").append(t).append(" ").append(Math.round(ans * E)).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void prim() {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];

        queue.offer(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            List<Node> nodes = adjList[vertex];

            for (Node node : nodes) {
                if (visited[node.vertex]) continue;
                priorityQueue.offer(node);
            }

            while (!priorityQueue.isEmpty()) {
                Node cur = priorityQueue.poll();
                if (visited[cur.vertex]) continue;
                visited[cur.vertex] = true;
                queue.offer(cur.vertex);
                ans += cur.dist;
                break;
            }
        }
    }

    private static void init(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        X = new int[N];
        Y = new int[N];
        String x = br.readLine().trim();
        String y = br.readLine().trim();
        StringTokenizer st = new StringTokenizer(x);
        for (int i = 0; i < N; i++) X[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(y);
        for (int i = 0; i < N; i++) Y[i] = Integer.parseInt(st.nextToken());
        E = Double.parseDouble(br.readLine().trim());
        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) adjList[i] = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                long dist = (long) (Math.pow(X[i] - X[j], 2) + Math.pow(Y[i] - Y[j], 2));
                adjList[i].add(new Node(j, dist));
            }
        }
        ans = 0;
    }

}
