package BOJ;

import java.io.*;
import java.util.*;

public class B1260_list {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        // 정점의 개수, 간선의 개수, 탐색 시작 정점 번호
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        List<Integer>[] adjList = new LinkedList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adjList[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            adjList[n1].add(n2);
            adjList[n2].add(n1);
        }

        for (int i = 1; i < N + 1; i++) {
            Collections.sort(adjList[i]);
        }

        boolean[] visited = new boolean[N + 1];
        dfs(adjList, visited, V);
        sb.append("\n");
        visited = new boolean[N + 1];
        bfs(adjList, visited, V);

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void dfs(List<Integer>[] adjList, boolean[] visited, int cur) {
        visited[cur] = true;
        sb.append(cur).append(" ");
        for (int next : adjList[cur]) {
            if (!visited[next]) {
                dfs(adjList, visited, next);
            }
        }
    }

    private static void bfs(List<Integer>[] adjList, boolean[] visited, int cur) {
        Queue<Integer> queue = new LinkedList<>();
        visited[cur] = true;
        queue.offer(cur);

        while (!queue.isEmpty()) {
            int q = queue.poll();
            sb.append(q).append(" ");
            for (int next : adjList[q]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }

}
