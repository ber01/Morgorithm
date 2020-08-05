
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B12851 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        queue.offer(N);
        visited[N] = true;
        int dist = 0;
        boolean isValid = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                if (queue.isEmpty()) break;
                int now = queue.poll();
                visited[now] = true;
                if (now == K) {
                    isValid = true;
                    break;
                }
                validNow(queue, visited, now);
            }
            if (isValid) break;
            dist++;
        }

        queue = new LinkedList<>();
        visited = new boolean[100001];
        queue.offer(N);
        visited[N] = true;
        int newDist = 0;
        int cnt = 0;
        while (!queue.isEmpty()) {
            if (newDist > dist) break;
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                if (queue.isEmpty()) break;
                int now = queue.poll();
                visited[now] = true;
                if (now == K && newDist == dist) {
                    cnt++;
                }
                validNow(queue, visited, now);
            }
            newDist++;
        }

        sb.append(dist).append("\n").append(cnt);
        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void validNow(Queue<Integer> queue, boolean[] visited, int now) {
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

}
