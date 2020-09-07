package BOJ;

import java.io.*;
import java.util.*;

public class B16928 {

    static int N, M;
    static int[] next, dice;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);

        bw.write(bfs() + "");
        bw.flush();
    }

    private static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        dice[1] = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 1; i <= 6; i++) {
                int n = cur + i;
                if (n > 100) continue;
                n = next[n];
                if (dice[n] == -1) {
                    dice[n] = dice[cur] + 1;
                    queue.offer(n);
                }
            }
        }

        return dice[100];
    }

    private static void init(BufferedReader br) throws IOException {
        next = new int[101];
        dice = new int[101];
        for (int i = 1; i < 101; i++) {
            next[i] = i;
            dice[i] = -1;
        }
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            next[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            next[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }
    }

}
