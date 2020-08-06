import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class B1922_list {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine().trim());
        int M = Integer.parseInt(br.readLine().trim());

        parent = new int[N + 1];
        IntStream.rangeClosed(0, N).forEach(i -> parent[i] = i);

        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            edgeList.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken())));
        }
        edgeList.sort(Comparator.comparingLong(Edge::getWeight));

        int cnt = 0;
        long result = 0;
        for (Edge edge : edgeList) {
            if (union(edge.v1, edge.v2)) {
                result += edge.weight;
                if (++cnt == N - 1) break;
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
    }

    private static class Edge {

        int v1;
        int v2;
        long weight;

        public Edge(int v1, int v2, long weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        public long getWeight() {
            return weight;
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
