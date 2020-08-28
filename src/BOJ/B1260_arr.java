package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1260_arr {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        // 정점의 개수, 간선의 개수, 탐색 시작 정점 번호
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        boolean[][] arr = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            arr[n1][n2] = true;
            arr[n2][n1] = true;
        }

        boolean[] visited = new boolean[N + 1];
        dfs(arr, visited, N, V);
        sb.append("\n");
        visited = new boolean[N + 1];
        bfs(arr, visited, N, V);

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void dfs(boolean[][] arr, boolean[] visited, int N, int V) {
        visited[V] = true;
        sb.append(V).append(" ");
        for (int i = 1; i < N + 1; i++) {
            if (arr[V][i] && !visited[i]) {
                dfs(arr, visited, N, i);
            }
        }
    }

    private static void bfs(boolean[][] arr, boolean[] visited, int N, int V) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(V);
        visited[V] = true;
        while (!queue.isEmpty()) {
            int q = queue.poll();
            sb.append(q).append(" ");
            for (int i = 1; i < N + 1; i++) {
                if (arr[q][i] && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }

}
