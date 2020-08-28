package BOJ;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class B1922_priority {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine().trim());
        int M = Integer.parseInt(br.readLine().trim());

        parent = new int[N + 1];
        IntStream.rangeClosed(0, N).forEach(i -> parent[i] = i);

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            priorityQueue.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken())));
        }

        int cnt = 0;
        long result = 0;
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.remove();
            if (union(edge.v1, edge.v2)) {
                result += edge.weight;
                if (++cnt == N - 1) break;
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
    }

    private static class Edge implements Comparable<Edge> {

        int v1;
        int v2;
        long weight;

        public Edge(int v1, int v2, long weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return (int) (this.weight - o.weight);
        }

    }

    private static int find(int a) {
        if (a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        parent[a] = b;
        return true;
    }

}
