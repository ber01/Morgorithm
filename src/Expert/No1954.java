package Expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class No1954 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine().trim());
            int[][] nums = new int[N][N];
            int x = 0;
            int y = -1;
            int direction = 1;
            int cnt = 1;

            while (true) {
                // 행 검사
                for (int i = 0; i < N; i++) {
                    // x는 그대로 y만 증가
                    y += direction;
                    nums[x][y] = cnt++;
                }
                // 행 종료 시 반복 횟수 감소
                N--;
                if (N == 0) break;
                // 열 검사
                for (int i = 0; i < N; i++) {
                    // y는 그대로 x만 증가
                    x += direction;
                    nums[x][y] = cnt++;
                }
                // 방향 변경
                direction *= -1;
            }

            StringBuilder result = new StringBuilder();
            for (int[] num : nums) {
                for (int i : num) {
                    result.append(i).append(" ");
                }
                result.append("\n");
            }
            sb.append("#").append(t).append("\n").append(result);
        }
        System.out.print(sb.toString().trim());
    }

}
