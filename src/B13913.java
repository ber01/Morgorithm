import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B13913 {

    static class Location {
        int cur;
        int dist;
        String str;

        public Location(int cur, String str) {
            this.cur = cur;
            this.str = str;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "cur=" + cur +
                    ", str='" + str + '\'' +
                    '}';
        }
    }
    static int N, K;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Location location = bfs();
        sb.append(location.dist).append("\n").append(N).append(" ");
        char[] chars = location.str.toCharArray();
        for (char ch : chars) {
            if (ch - '0' == 1) {
                N -= 1;
                sb.append(N).append(" ");
            } else if (ch - '0' == 2) {
                N += 1;
                sb.append(N).append(" ");
            } else {
                N *= 2;
                sb.append(N).append(" ");
            }
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static Location bfs() {
        Queue<Location> queue = new LinkedList<>();
        visited = new boolean[100001];
        queue.offer(new Location(N, ""));
        visited[N] = true;

        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Location poll = queue.poll();
                if (poll.cur == K) {
                    poll.dist = dist;
                    return poll;
                }
                visitedCheck(queue, poll);
            }
            dist++;
        }

        return null;
    }

    private static void visitedCheck(Queue<Location> queue, Location poll) {
        int cur = poll.cur;
        String str = poll.str;
        visited[cur] = true;

        if (cur - 1 >= 0 && !visited[cur - 1]) {
            queue.offer(new Location(cur - 1, str + "1"));
        }
        if (cur + 1 <= 100000 && !visited[cur + 1]) {
            queue.offer(new Location(cur + 1, str + "2"));
        }
        if (cur * 2 <= 100000 && !visited[cur * 2]) {
            queue.offer(new Location(cur * 2, str + "3"));
        }
    }

}
