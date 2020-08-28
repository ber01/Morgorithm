package Expert;

import java.io.*;
import java.util.StringTokenizer;

public class No4012 {

    static int N, R, min;
    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            visited = new boolean[N];
            R = N / 2;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            min = Integer.MAX_VALUE;
            backtrack(0, 0);

            sb.append("#").append(t).append(" ").append(min).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void backtrack(int cnt, int idx) {
        if (cnt == R) {
            int A = 0, B = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i] && visited[j]) A += map[i][j];
                    if (!visited[i] && !visited[j]) B += map[i][j];
                }
            }

            min = Math.min(Math.abs(A - B), min);
            return;
        }

        if (idx == N) return;

        visited[idx] = true;
        backtrack(cnt + 1, idx + 1);
        visited[idx] = false;
        backtrack(cnt, idx + 1);
    }

}
