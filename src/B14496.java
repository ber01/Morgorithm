import java.io.*;
import java.util.*;

public class B14496 {

    static int a, b, N, M;
    static List<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            adjList[n1].add(n2);
            adjList[n2].add(n1);
        }

        bw.write(bfs());
        bw.flush();
    }

    private static String bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(a);
        int[] dist = new int[N + 1];
        dist[a] = 0;

        if (a == b) return "0";

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : adjList[cur]) {
                if (dist[next] != 0) continue;
                dist[next] = dist[cur] + 1;
                queue.offer(next);
            }
        }

        return dist[b] == 0 ? "-1" : String.valueOf(dist[b]);
    }

}
