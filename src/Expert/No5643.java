package Expert;

import java.io.*;
import java.util.StringTokenizer;

public class No5643 {

    static int N;
    static int[][] up;
    static int[][] down;
    static boolean[] visited;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());
            int M = Integer.parseInt(br.readLine().trim());

            up = new int[N + 1][N + 1];
            down = new int[N + 1][N + 1];
            cnt = new int[N + 1];

            while (M-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                up[n1][n2] = 1;
                down[n2][n1] = 1;
            }

            for (int i = 1; i < N + 1; i++) {
                visited = new boolean[N + 1];
                dfs(up, i);
            }

            for (int i = 1; i < N + 1; i++) {
                visited = new boolean[N + 1];
                dfs(down, i);
            }

            int result = 0;
            for (int value : cnt) {
                if (value - 2 == N - 1) {
                    result++;
                }
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void dfs(int[][] arr, int start) {
        visited[start] = true;
        cnt[start]++;
        for (int i = 1; i < N + 1; i++) {
            if (arr[start][i] == 1 && !visited[i]) {
                dfs(arr, i);
            }
        }
    }

}
