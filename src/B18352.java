import java.io.*;
import java.util.*;

public class B18352 {

    static int N, M, K, X;
    static List<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            adjList[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
        }

        bw.write(bfs());
        bw.flush();
    }

    private static String bfs() {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        int[] dist = new int[N + 1];
        queue.offer(X);
        dist[X] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : adjList[cur]) {
                if (dist[next] != 0) continue;
                dist[next] = dist[cur] + 1;
                queue.offer(next);
            }
        }

        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                sb.append(i).append("\n");
            }
        }

        return sb.length() == 0 ? "-1" : sb.toString().trim();
    }

}
