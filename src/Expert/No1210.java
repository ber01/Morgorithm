package Expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No1210 {

    private static final int N = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            int num = Integer.parseInt(br.readLine().trim());
            int[][] data = new int[N][N];

            int x = 99;
            int y = 0;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    data[i][j] = Integer.parseInt(st.nextToken());
                    if (data[99][j] == 2) {
                        y = j;
                    }
                }
            }

            while (x != 0) {
                if (y - 1 >= 0 && data[x][y - 1] == 1) { // left
                    data[x][y] = 0;
                    y--;
                } else if (y + 1 < N && data[x][y + 1] == 1) { // right
                    data[x][y] = 0;
                    y++;
                } else if (data[x - 1][y] == 1) { // up
                    data[x][y] = 0;
                    x--;
                }
            }

            sb.append("#").append(num).append(" ").append(y).append("\n");
        }
        System.out.print(sb.toString().trim());
    }

}
