import java.io.*;
import java.util.*;

public class B13549_PriorityQueue {

    static class Location implements Comparable<Location> {
        int cur;
        int sec;

        public Location(int cur, int sec) {
            this.cur = cur;
            this.sec = sec;
        }

        @Override
        public int compareTo(Location o) {
            return this.sec - o.sec;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "cur=" + cur +
                    ", sec=" + sec +
                    '}';
        }
    }

    static int N, K;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bw.write(String.valueOf(bfs()));
        bw.flush();
    }

    private static int bfs() {
        PriorityQueue<Location> queue = new PriorityQueue<>();
        queue.offer(new Location(N, 0));
        visited = new boolean[100001];
        visited[N] = true;

        while (!queue.isEmpty()) {
            Location poll = queue.poll();
            if (poll.cur == K) return poll.sec;
            visitedCheck(queue, poll);
        }

        return -1;
    }

    private static void visitedCheck(PriorityQueue<Location> queue, Location poll) {
        int cur = poll.cur;
        int sec = poll.sec;
        visited[cur] = true;

        if (cur - 1 >= 0 && !visited[cur - 1]) queue.offer(new Location(cur - 1, sec + 1));
        if (cur + 1 <= 100000 && !visited[cur + 1]) queue.offer(new Location(cur + 1, sec + 1));
        if (cur * 2 <= 100000 && !visited[cur * 2]) queue.offer(new Location(cur * 2, sec));
    }

}
