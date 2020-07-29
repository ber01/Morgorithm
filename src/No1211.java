
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class No1211 {

    private static final int N = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            int num = Integer.parseInt(br.readLine().trim());
            int[][] data = new int[N][N];

            List<Integer> start = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    data[i][j] = Integer.parseInt(st.nextToken());
                    if (i == 0) {
                        if (data[i][j] == 1) {
                            start.add(j);
                        }
                    }
                }
            }

            int min = Integer.MAX_VALUE;
            int index = 0;
            for (Integer integer : start) {
                int x = 0;
                int y = integer;
                int[][] temp = new int[N][N];

                for (int a = 0; a < N; a++) {
                    System.arraycopy(data[a], 0, temp[a], 0, N);
                }

                int tempY = y;

                int result = 0;
                while (x != N - 1) {
                    if (y - 1 >= 0 && temp[x][y - 1] == 1) { // left
                        result++;
                        temp[x][y] = 0;
                        y--;
                    } else if (y + 1 < N && temp[x][y + 1] == 1) { // right
                        result++;
                        temp[x][y] = 0;
                        y++;
                    } else if (temp[x + 1][y] == 1) { // down
                        result++;
                        temp[x][y] = 0;
                        x++;
                    }
                }

                if (min >= result) {
                    min = result;
                    index = tempY;
                }
            }
            sb.append("#").append(num).append(" ").append(index).append("\n");
        }
        System.out.print(sb.toString().trim());
    }

}

