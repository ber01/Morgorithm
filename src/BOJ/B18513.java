package BOJ;

import java.io.*;
import java.util.*;

public class B18513 {

    static class Point {
        long cur;
        long dist;

        public Point(long cur, long dist) {
            this.cur = cur;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "(" + cur + ", " + dist + ")";
        }
    }
    static int N, K;
    static int[] di = {-1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Queue<Point> queue = new LinkedList<>();
        Set<Long> visited = new HashSet<>();
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            long cur = Long.parseLong(st.nextToken());
            queue.offer(new Point(cur, 1));
            visited.add(cur);
        }

        bw.write(String.valueOf(bfs(queue, visited)));
        bw.flush();
    }

    private static Long bfs(Queue<Point> queue, Set<Long> visited) {
        long result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Point cur = queue.poll();
                for (int d = 0; d < 2; d++) {
                    long next = cur.cur + di[d];
                    long dist = cur.dist;
                    if (next > 100000000 || next < -100000000 || visited.contains(next)) continue;
                    result += dist;
                    queue.offer(new Point(next, dist + 1));
                    visited.add(next);
                    K--;
                    if (K == 0) return result;
                }
            }
        }

        return result;
    }

}
