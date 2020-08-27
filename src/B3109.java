import java.io.*;
import java.util.StringTokenizer;

public class B3109 {

    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static int[] di = {-1, 0, 1};
    static int[] dj = {1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) map[i] = br.readLine().trim().toCharArray();

        int ans = 0;
        for (int i = 0; i < R; i++) ans += dfs(i, 0);

        bw.write(String.valueOf(ans));
        bw.flush();
    }

    private static int dfs(int i, int j) {
        visited[i][j] = true;

        if (j == C - 1) return 1;

        for (int d = 0; d < 3; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            if (ni >= R || ni < 0 || nj >= C || nj < 0 || map[ni][nj] == 'x' || visited[ni][nj]) continue;
            if (dfs(ni, nj) == 1) return 1;
        }

        return 0;
    }

}
