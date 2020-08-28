package Expert;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class No1238_dfs {

    private static int[] dist;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int N = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            LinkedList<Integer>[] adjList = new LinkedList[101];
            for (int i = 0; i < adjList.length; i++) {
                adjList[i] = new LinkedList<>();
            }

            String[] input = br.readLine().split(" ");
            for (int i = 0; i < N; i += 2) {
                adjList[Integer.parseInt(input[i])].add(Integer.parseInt(input[i + 1]));
            }

            dist = new int[101];
            visited = new boolean[101];
            dfs(adjList, start);

            int max = 0;
            int index = 0;
            for (int i = 0; i < dist.length; i++) {
                if (dist[i] >= max) {
                    max = dist[i];
                    index = i;
                }
            }

            sb.append("#").append(t).append(" ").append(index).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void dfs(LinkedList<Integer>[] adjList, int start) {
        for (Integer w : adjList[start]) {
            if (dist[w] == 0) {
                dist[w] = dist[start] + 1;
            } else if (dist[w] != 0 && dist[w] > dist[start] + 1) {
                dist[w] = dist[start] + 1;
            }
            dfs(adjList, w);
        }
    }

}
