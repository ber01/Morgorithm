package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2606_arr {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int V = Integer.parseInt(br.readLine().trim());
        int N = Integer.parseInt(br.readLine().trim());
        boolean[][] arr = new boolean[V + 1][V + 1];
        boolean[] visited = new boolean[V + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            arr[n1][n2] = true;
            arr[n2][n1] = true;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        int cnt = -1;
        while (!queue.isEmpty()) {
            int q = queue.poll();
            System.out.print(q + " ");
            cnt++;
            for (int i = 1; i < V + 1; i++) {
                if (arr[q][i] && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }

        System.out.println();
        bw.write(cnt + "");
        bw.flush();
    }

}
