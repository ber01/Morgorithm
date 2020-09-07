package BOJ;

import java.io.*;
import java.util.*;

public class B9019 {

    static class Node {
        int n;
        String result;

        public Node(int n, String result) {
            this.n = n;
            this.result = result;
        }
    }

    static int A, B;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            init(br);
            sb.append(bfs()).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static String bfs() {
        Queue<Node> queue = new LinkedList<>();
        visited = new boolean[10000];
        queue.offer(new Node(A, ""));

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node cur = queue.poll();
                int n = cur.n;
                String result = cur.result;
                if (n == B) return result;

                int D = (2 * n) % 10000;
                int S = (n == 0) ? 9999 : n - 1 ;
                int L = (n % 1000) * 10 + n / 1000;
                int R = (n % 10) * 1000 + n / 10;

                check(queue, result, D, "D");
                check(queue, result, S, "S");
                check(queue, result, L, "L");
                check(queue, result, R, "R");
            }
        }

        return null;
    }

    private static void check(Queue<Node> queue, String result, int d, String d2) {
        if (!visited[d]) {
            visited[d] = true;
            queue.offer(new Node(d, result + d2));
        }
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
    }

}
