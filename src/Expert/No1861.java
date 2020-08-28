package Expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No1861 {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine().trim());
            int[][] square = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    square[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int x = 0;
            int max = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int result = check(N, i, j, square) + 1;
                    if (result > max) {
                        max = result;
                        x = square[i][j];
                    } else if (result == max) {
                        x = Math.min(square[i][j], x);
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(x).append(" ").append(max).append("\n");
        }
        System.out.print(sb.toString().trim());
    }

    private static int check(int N, int row, int col, int[][] square) {
        int result = 0;
        int nx;
        int ny;
        while (true) {
            boolean isTrue = false;
            for (int i = 0; i < 4; i++) {
                nx = row + dx[i];
                ny = col + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (square[nx][ny] == square[row][col] + 1) {
                    row = nx;
                    col = ny;
                    isTrue = true;
                    result++;
                }
            }
            if (!isTrue) break;
        }
        return result;
    }
}