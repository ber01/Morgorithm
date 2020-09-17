package BOJ;

import java.io.*;
import java.util.*;

public class B16953 {

    static Long A, B;
    static Set<Long> visited;
    static Queue<Long> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);

        bw.write(bfs() + "");
        bw.flush();
    }

    private static int bfs() {
        queue.offer(A);
        visited.add(A);

        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                long cur = queue.poll();
                if (cur == B) return dist + 1;
                long next = cur * 2;
                if (!visited.contains(next) && next <= B) queue.offer(next);
                next = Long.parseLong((String.valueOf(cur) + 1));
                if (!visited.contains(next) && next <= B) queue.offer(next);
            }
            dist++;
        }

        return -1;
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        visited = new HashSet<>();
        queue = new LinkedList<>();
    }

}
