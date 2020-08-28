package BOJ;

import java.io.*;
import java.util.*;

public class B2644 {

    private static int N;
    private static int p1, p2;
    private static List<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine().trim());
        adjList = new LinkedList[N + 1];
        for (int i = 0; i <= N; i++) {
            adjList[i] = new LinkedList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        p1 = Integer.parseInt(st.nextToken());
        p2 = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine().trim());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adjList[x].add(y);
            adjList[y].add(x);
        }

        bw.write(String.valueOf(bfs(p1)));
        bw.flush();
    }

    private static int bfs(int num) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(num);
        visited[num] = true;

        boolean isValid = false;
        int cnt = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                if (queue.isEmpty()) break;
                int cur = queue.poll();
                for (Integer next : adjList[cur]) {
                    if (visited[next]) continue;
                    if (next == p2) {
                        isValid = true;
                        break;
                    }
                    queue.offer(next);
                    visited[next] = true;
                }
            }
            if (isValid) break;
            cnt++;
        }

        return isValid ? cnt : -1;
    }

}
