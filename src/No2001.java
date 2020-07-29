
import java.util.Scanner;

public class No2001 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {

            int N = sc.nextInt();
            int M = sc.nextInt();
            int[][] arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            int m = M - 1;
            int max = 0;
            for (int i = 0; i < N; i++) {
                if (i + m == N) break;
                for (int j = 0; j < N; j++) {
                    if (j + m == N) break;
                    int sum = 0;
                    for (int x = i; x < i + M; x++) {
                        for (int y = j; y < j + M; y++) {
                            sum += arr[x][y];
                        }
                    }
                    max = Math.max(max, sum);
                }
            }
            sb.append("#").append(t).append(" ").append(max).append("\n");
        }
        System.out.print(sb.toString().trim());
    }

}