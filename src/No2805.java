
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class No2805 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine().trim());
            int[][] farm = inputFarm(br, N);
            sb.append("#").append(t).append(" ").append(revenue(farm, N)).append("\n");
        }

        System.out.print(sb.toString().trim());
    }

    private static int[][] inputFarm(BufferedReader br, int n) throws IOException {
        int[][] farm = new int[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine().trim();
            for (int j = 0; j < n; j++) {
                farm[i][j] = str.charAt(j) - '0';
            }
        }
        return farm;
    }

    private static int revenue(int[][] farm, int N) {
        if (N == 1) {
            return farm[0][0];
        }

        int colStart = 0;
        int colEnd = N - 1;
        int row = N / 2;
        int sum = 0;

        for (int i = colStart; i <= colEnd; i++) {
            sum += farm[row][i];
        }

        int rowUp = row;
        int rowDown = row;
        while (colStart != colEnd) {
            colStart++;
            colEnd--;
            rowUp--;
            rowDown++;
            for (int i = colStart; i <= colEnd; i++) {
                sum += farm[rowUp][i];
                sum += farm[rowDown][i];
            }
        }

        return sum;
    }
}
