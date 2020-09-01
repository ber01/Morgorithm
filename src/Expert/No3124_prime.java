package Expert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class No3124_prime {

    static class Edge implements Comparable<Edge> {
        int vertex;
        int weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    static int V, E;
    static long ans;
    static List<Edge>[] adjList;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            init(br);
            prim();
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void prim() {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        for (Edge e : adjList[1]) {
            queue.offer(new Edge(e.vertex, e.weight));
        }
        visited[1] = true;

        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            if (visited[cur.vertex]) continue;
            visited[cur.vertex] = true;
            ans += cur.weight;

            for (Edge next : adjList[cur.vertex]) {
                if (visited[next.vertex]) continue;
                queue.offer(new Edge(next.vertex, next.weight));
            }
        }

    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[V + 1];
        for (int i = 1; i < V + 1; i++) adjList[i] = new ArrayList<>();
        visited = new boolean[V + 1];

        while (E-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from].add(new Edge(to, weight));
            adjList[to].add(new Edge(from, weight));
        }

        ans = 0;
    }

}
