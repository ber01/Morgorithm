package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B5014 {

    static int F, S, G, U, D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        bw.write(bfs());
        bw.flush();
    }

    private static String bfs() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[][] visited = new boolean[2][F + 1];
        queue.offer(S);

        int btn = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int cur = queue.poll();
                if (cur == G) return String.valueOf(btn);

                int next = cur + U;
                if (next < 1 || next > F || visited[0][next]) continue;
                queue.offer(next);
                visited[0][next] = true;

                next = cur - D;
                if (next < 1 || next > F || visited[1][next]) continue;
                queue.offer(next);
                visited[1][next] = true;
            }
            btn++;
        }

        return "use the stairs";
    }

}
