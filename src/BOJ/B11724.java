package BOJ;

import java.io.*;
import java.util.*;

public class B11724 {

    static int N;
    static int M;
    static List<Integer>[] adjList;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

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

        visited = new boolean[N + 1];
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                bfs(i);
                dfs(i);
                ans++;
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
    }

    private static void dfs(int i) {
        visited[i] = true;

        for (int next : adjList[i]) {
            if (visited[next]) continue;
            dfs(next);
        }
    }

    private static void bfs(int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        visited[i] = true;

        while (!queue.isEmpty()) {
            int poll = queue.poll();
            for (int next : adjList[poll]) {
                if (visited[next]) continue;
                visited[next] = true;
                queue.offer(next);
            }
        }

    }

}