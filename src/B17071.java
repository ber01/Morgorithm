import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B17071 {

    static int N, K;

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
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);

        int sec = 0;
        while (!queue.isEmpty()) {
            if (K > 500000) return -1;
            int size = queue.size();
            while (size-- > 0) {
                int n = queue.poll();
                if (n == K) return sec;
                if (n - 1 > 0) queue.offer(n - 1);
                if (n + 1 <= 500000) queue.offer(n + 1);
                if (n * 2 <= 500000) queue.offer(n * 2);
            }
            K += (sec++ + 1);
        }

        return -1;
    }

}
