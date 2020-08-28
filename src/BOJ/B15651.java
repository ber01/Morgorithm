package BOJ;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B15651 {

    static int N;
    static int M;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = i + 1;
        }

        sb = new StringBuilder();
        List<Integer> tmp = new ArrayList<>();
        backtrack(nums, tmp);

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void backtrack(int[] nums, List<Integer> tmp) {
        if (tmp.size() == M) {
            for (int t : tmp) {
                sb.append(t).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int num : nums) {
            tmp.add(num);
            backtrack(nums, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

}
