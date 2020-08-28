package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B12851 {

    static int N, K;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int dist = bfs();
        sb.append(dist).append("\n").append(bfs(dist));

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static int bfs(int dist) {
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[100001];
        queue.offer(N);
        visited[N] = true;

        int cnt = 0;
        int curDist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                if (cur == K && curDist == dist) cnt++;
                visited(queue, cur);
            }
            curDist++;
            if (curDist > dist) return cnt;
        }

        return -1;
    }

    private static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[100001];
        queue.offer(N);
        visited[N] = true;

        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                if (cur == K) return dist;
                visited(queue, cur);
            }
            dist++;
        }

        return -1;
    }

    private static void visited(Queue<Integer> queue, int cur) {
        visited[cur] = true;
        if (cur - 1 >= 0 && !visited[cur - 1]) {
            queue.offer(cur - 1);
        }
        if (cur + 1 <= 100000 && !visited[cur + 1]) {
            queue.offer(cur + 1);
        }
        if (cur * 2 <= 100000 && !visited[cur * 2]) {
            queue.offer(cur * 2);
        }
    }


}
