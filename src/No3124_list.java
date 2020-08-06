import java.io.*;
import java.util.*;

public class No3124_list {

    private static class Edge {

        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

    }

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            List<Edge> edgeList = new ArrayList<>();
            parent = new int[V + 1];
            for (int i = 1; i <= V; i++) {
                parent[i] = i;
            }

            int from;
            int to;
            int weight;
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine().trim());
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());
                weight = Integer.parseInt(st.nextToken());
                edgeList.add(new Edge(from, to, weight));
            }

            edgeList.sort(Comparator.comparingInt(Edge::getWeight));

            int cnt = 0;
            long result = 0;
            for (Edge edge : edgeList) {
                if (union(edge.from, edge.to)) {
                    result += edge.weight;
                    if (++cnt == V - 1) break;
                }
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        bw.write(sb.toString().trim());
        bw.flush();
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
