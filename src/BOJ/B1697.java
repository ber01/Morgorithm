package BOJ;

import java.io.*;
import java.util.*;

public class B1697 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        queue.offer(N);
        visited[N] = true;
        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                if (queue.isEmpty()) break;
                int now = queue.poll();
                visited[now] = true;
                if (now == K) {
                    System.out.print(dist);
                    return;
                }
                if (now + 1 <= 100000 && !visited[now + 1]) {
                    queue.offer(now + 1);
                }
                if (now - 1 >= 0 && !visited[now - 1]) {
                    queue.offer(now - 1);
                }
                if (now * 2 <= 100000 && !visited[now * 2]) {
                    queue.offer(now * 2);
                }
            }
            dist++;
        }
    }

}