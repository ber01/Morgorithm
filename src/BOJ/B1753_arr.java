package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1753_arr {

    static int V, E;
    static int[][] map;
    static int[] dist;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        V = Integer.parseInt(st.nextToken()); // 정점
        E = Integer.parseInt(st.nextToken()); // 간선

        int K = Integer.parseInt(br.readLine().trim()); // 시작 번호

        map = new int[V + 1][V + 1];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            map[u][v] = w;
        }

        dist = new int[V + 1];
        visited = new boolean[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[K] = 0;

        dijkstra();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < V + 1; i++) {
            sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void dijkstra() {
        for (int i = 1; i <= V; i++) {
            int u = minIndex();
            visited[u] = true;

            for (int j = 1; j <= V; j++) {
                if (!visited[j] && map[u][j] != 0 && dist[u] != Integer.MAX_VALUE && dist[j] > dist[u] + map[u][j]) {
                    dist[j] = dist[u] + map[u][j];
                }
            }
        }
    }

    private static int minIndex() {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 1; i <= V; i++) {
            if (!visited[i] && min >= dist[i]) {
                min = dist[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

}
