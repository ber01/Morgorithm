import java.io.*;
import java.util.StringTokenizer;

public class B2636_2 {

    static int N, M;
    static int[][] cheese;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        createCheese();

        int hour = 0;
        do {
            hour++;
            visited = new boolean[N][M];
            dfs(0, 0);
            meltingCheese();
        } while (isCheese());

        sb.append(hour).append("\n").append(result);

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static boolean isCheese() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cheese[i][j] == 1) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void meltingCheese() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cheese[i][j] == -1) {
                    for (int d = 0; d < 4; d++) {
                        int ni = i + di[d];
                        int nj = j + dj[d];
                        if (ni >= N || ni < 0 || nj >= M || nj < 0 || visited[ni][nj]) continue;
                        if (cheese[ni][nj] == 1) {
                            cheese[ni][nj] = 2;
                        }
                    }
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cheese[i][j] == 2) {
                    cnt++;
                    cheese[i][j] = -1;
                }
            }
        }
        result = cnt;
    }

    private static void createCheese() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cheese = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void dfs(int i, int j) {
        visited[i][j] = true;
        cheese[i][j] = -1;

        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            if (ni >= N || ni < 0 || nj >= M || nj < 0 || visited[ni][nj] || cheese[ni][nj] == 1) continue;
            dfs(ni, nj);
        }
    }

}
