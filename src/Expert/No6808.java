package Expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No6808 {

    private static final int N = 9;
    private static int[] nums = new int[N];
    private static final boolean[] isSelected = new boolean[N + 1];
    private static final int[] gu = new int[N];
    private static final int[] in = new int[N];
    private static int win = 0;
    private static int lose = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            boolean[] all = new boolean[19];
            StringTokenizer st = new StringTokenizer(br.readLine().trim());

            for (int i = 0; i < N; i++) {
                gu[i] = Integer.parseInt(st.nextToken());
                all[gu[i]] = true;
            }

            for (int i = 1, j = 0; i < all.length; i++) {
                if (!all[i]) {
                    in[j] = i;
                    j++;
                }
            }

            permutation(0);

            sb.append("#").append(t).append(" ").append(win).append(" ").append(lose).append("\n");
            win = 0;
            lose = 0;
        }

        System.out.print(sb.toString().trim());
    }

    private static void permutation(int cnt) {
        if (cnt == N) {
            calc(nums);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isSelected[i]) continue;
            nums[cnt] = in[i];
            isSelected[i] = true;
            permutation(cnt + 1);
            isSelected[i] = false;
        }
    }

    private static void calc(int[] nums) {

        int gSum = 0;
        int iSum = 0;
        for (int i = 0; i < N; i++) {
            if (gu[i] > nums[i]) {
                gSum += (gu[i] + nums[i]);
            } else {
                iSum += (gu[i] + nums[i]);
            }
        }

        if (gSum > iSum) {
            win++;
        } else if (iSum > gSum) {
            lose++;
        }
    }

}
