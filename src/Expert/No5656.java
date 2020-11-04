package Expert;

import java.io.*;
import java.util.StringTokenizer;

public class No5656 {

    static int N, W, H, MIN;
    static int[] order;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int[][] map, origin;
    static class Brick {
        int i, j, value;

        public Brick(int i, int j, int value) {
            this.i = i;
            this.j = j;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Brick{" +
                    "i=" + i +
                    ", j=" + j +
                    ", value=" + value +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            init(br);

            backtrack(0);

            sb.append("#").append(t).append(" ").append(MIN).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void backtrack(int cnt) {
        if (cnt == N) {
            copyMap();
            for (int j : order) {
                Brick brick = search(j);
                if (brick == null) continue;
                boom(brick);
                down();
            }
            MIN = Math.min(MIN, cntMap());
            return;
        }

        for (int w = 0; w < W; w++) {
            order[cnt] = w;
            backtrack(cnt + 1);
        }
    }

    private static Brick search(int j) {
        for (int i = 0; i < H; i++) {
            if (map[i][j] != 0) {
                return new Brick(i, j, map[i][j]);
            }
        }
        return null;
    }

    private static int cntMap() {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] != 0) cnt++;
            }
        }
        return cnt;
    }

    // 아래로 내리기
    private static void down() {
        for (int i = H - 1; i >= 0; i--) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] != 0) {
                    for (int k = i; k < H - 1; k++) {
                        if (map[k + 1][j] == 0) {
                            map[k + 1][j] = map[k][j];
                            map[k][j] = 0;
                        }
                    }
                }
            }
        }
    }

    // 벽돌 폭파
    private static void boom(Brick top) {
        int i = top.i;
        int j = top.j;
        int value = top.value - 1;

        map[i][j] = 0;
        int k = 1;
        while(value-- > 0) {
            for (int d = 0; d < 4; d++) {
                int ni = i + di[d] * k;
                int nj = j + dj[d] * k;
                if (ni >= H || ni < 0 || nj >= W || nj < 0) continue;
                if (map[ni][nj] != 0) {
                    boom(new Brick(ni, nj, map[ni][nj]));
                    map[ni][nj] = 0;
                }
            }
            k++;
        }
    }

    private static void copyMap() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                map[i][j] = origin[i][j];
            }
        }
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        order = new int[N];
        map = new int[H][W];
        origin = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < W; j++) {
                int v = Integer.parseInt(st.nextToken());
                map[i][j] = v;
                origin[i][j] = v;
            }
        }
        MIN = Integer.MAX_VALUE;
    }

}
