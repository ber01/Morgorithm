package BOJ;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class B15655 {

    static int N, M;
    static int[] nums;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; st.hasMoreTokens(); i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        sb = new StringBuilder();
        List<Integer> tmp = new ArrayList<>();
        backtrack(tmp, 0);

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void backtrack(List<Integer> tmp, int idx) {
        if (tmp.size() == M) {
            for (int t : tmp) {
                sb.append(t).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            tmp.add(nums[i]);
            backtrack(tmp, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }

}
