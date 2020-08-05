import java.io.*;
import java.util.*;

public class B2606_list {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int V = Integer.parseInt(br.readLine().trim());
        int N = Integer.parseInt(br.readLine().trim());
        List<Integer>[] adjList = new LinkedList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            adjList[i] = new LinkedList<>();
        }
        boolean[] visited = new boolean[V + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            adjList[n1].add(n2);
            adjList[n2].add(n1);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        int cnt = -1;
        while (!queue.isEmpty()) {
            int q = queue.poll();
            cnt++;
            for (Integer w : adjList[q]) {
                if (!visited[w]) {
                    queue.offer(w);
                    visited[w] = true;
                }
            }
        }

        bw.write(cnt + "");
        bw.flush();
    }

}
