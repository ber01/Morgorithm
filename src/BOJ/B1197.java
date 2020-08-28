package BOJ;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class B1197 {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 정점의 개수, 간선의 개수
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // parent를 정점의 개수 만큼 생성 후 초기화
        parent = new int[V + 1];
        IntStream.rangeClosed(0, V).forEach(i -> parent[i] = i);

        // 간선의 정보를 저장할 리스트 생성
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            // from, to, weight
            st = new StringTokenizer(br.readLine().trim());
            edgeList.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken())));
        }

        // 간선 정렬
        edgeList.sort(Comparator.comparingLong(Edge::getWeight));

        // 횟수 확인, 값 누적
        int cnt = 0;
        long result = 0;
        for (Edge edge : edgeList) {
            if (union(edge.from, edge.to)) {
                result += edge.weight;
                if (++cnt == V - 1) break;
            }
        }

        bw.write(result + "");
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

    private static class Edge {

        int from;
        int to;
        long weight;

        public Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public long getWeight() {
            return weight;
        }

    }

}
