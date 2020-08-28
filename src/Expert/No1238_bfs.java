package Expert;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No1238_bfs {

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

            sb.append("#").append(t).append(" ").append(bfs(adjList, start)).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static int bfs(LinkedList<Integer>[] adjList, int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];
        int[] dist = new int[101];

        queue.offer(start);
        visited[start] = true;
        dist[start] = 0;
        while (!queue.isEmpty()) {
            int q = queue.poll();
            for (Integer w : adjList[q]) {
                if (!visited[w]) {
                    visited[w] = true;
                    dist[w] = dist[q] + 1;
                    queue.offer(w);
                }
            }
        }

        int max = 0;
        int index = 0;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] >= max) {
                max = dist[i];
                index = i;
            }
        }

        return index;
    }

}
