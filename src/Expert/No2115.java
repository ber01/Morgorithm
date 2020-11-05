package Expert;

import java.io.*;
import java.util.StringTokenizer;

public class No2115 {

    static int N, M, C, IDX, IDX2, MAX, MAX2, MAX3;
    static int[] result, result2;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            init(br);

            for (int i = 0; i < N; i++) {
                IDX = i;
                visited = new boolean[N][N];
                backtrack(0, 0);
            }

            // System.out.println(MAX);
            sb.append("#").append(t).append(" ").append(MAX).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void backtrack(int cnt, int idx) {
        if (cnt == M) {
            if (M == 1) {
                if (visited[IDX][result[0]]) return;
            }
            for (int i = 0; i < M - 1; i++) {
                if (result[i] + 1 != result[i + 1]) return;
            }

            // 첫 번재 꿀통 선택 완료
            for (int next : result) visited[IDX][next] = true;

            // 여기서 다른 꿀통을 찾는다.
            for (int i = 0; i < N; i++) {
                IDX2 = i;
                backtrack2(0, 0);
            }

            for (int next : result) visited[IDX][next] = false;
            return;
        }

        for (int i = idx; i < N; i++) {
            result[cnt] = i;
            backtrack(cnt + 1, i + 1);
        }
    }

    private static void backtrack2(int cnt, int idx) {
        if (cnt == M) {
            if (M == 1) {
                if (visited[IDX2][result2[0]]) return;
            }
            for (int i = 0; i < M - 1; i++) {
                if (visited[IDX2][result2[i]]) return;
                if (visited[IDX2][result2[i] + 1]) return;
                if (result2[i] + 1 != result2[i + 1]) return;
            }

            int sum = 0;
            int pow = 0;
            for (int next : result) {
                sum += map[IDX][next];
                pow += map[IDX][next] * map[IDX][next];
            }

            MAX2 = 0;
            if (sum > C) {
                for (int i = 1; i <= M; i++) {
                    boolean[] num = new boolean[M];
                    combination(0, i, num);
                }
                sum = MAX2;
            } else {
                sum = pow;
            }

            int sum2 = 0;
            int pow2 = 0;
            for (int next : result2) {
                sum2 += map[IDX2][next];
                pow2 += map[IDX2][next] * map[IDX2][next];
            }

            MAX3 = 0;
            if (sum2 > C) {
                for (int i = 1; i <= M; i++) {
                    boolean[] num = new boolean[M];
                    combination2(0, i, num);
                }
                sum2 = MAX3;
            } else {
                sum2 = pow2;
            }

            // System.out.println("sum => " + sum + " sum2 => " + sum2);

            MAX = Math.max(MAX, sum + sum2);
            return;
        }

        for (int i = idx; i < N; i++) {
            result2[cnt] = i;
            backtrack2(cnt + 1, i + 1);
        }
    }

    private static void combination(int idx, int cnt, boolean[] num) {
        if (cnt == 0) {
            int pow = 0;
            int sum = 0;
            for (int i = 0; i < num.length; i++) {
                if (!num[i]) continue;
                int v = map[IDX][result[i]];
                sum += v;
                pow += v * v;
            }
            if (sum > C) return;
            MAX2 = Math.max(MAX2, pow);
            return;
        }

        for (int i = idx; i < M; i++) {
            num[i] = true;
            combination(i + 1, cnt - 1, num);
            num[i] = false;
        }
    }

    private static void combination2(int idx, int cnt, boolean[] num) {
        if (cnt == 0) {
            int pow = 0;
            int sum = 0;
            for (int i = 0; i < num.length; i++) {
                if (!num[i]) continue;
                int v = map[IDX2][result2[i]];
                sum += v;
                pow += v * v;
            }
            if (sum > C) return;
            MAX3 = Math.max(MAX3, pow);
            return;
        }

        for (int i = idx; i < M; i++) {
            num[i] = true;
            combination2(i + 1, cnt - 1, num);
            num[i] = false;
        }
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        result = new int[M];
        result2 = new int[M];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        MAX = Integer.MIN_VALUE;
    }

}
