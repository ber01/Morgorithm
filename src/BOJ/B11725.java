package BOJ;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B11725 {

    static int N;
    static List<Integer>[] adjList;
    static boolean[] visited;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            adjList[n1].add(n2);
            adjList[n2].add(n1);
        }

        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < N  + 1; i++) {
            sb.append(parent[i]).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void dfs(int cur) {
        visited[cur] = true;
        for (int next : adjList[cur]) {
            if (visited[next]) continue;
            parent[next] = cur;
            dfs(next);
        }
    }

    private static void init(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        adjList = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }
        visited = new boolean[N + 1];
        parent = new int[N + 1];
    }

}
