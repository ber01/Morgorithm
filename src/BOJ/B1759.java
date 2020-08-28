package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1759 {

    static int N, M;
    static char[] nums;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new char[M];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; st.hasMoreTokens(); i++) {
            nums[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(nums);

        sb = new StringBuilder();
        backtrack(0, 0, "");
        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void backtrack(int cnt, int idx, String str) {
        if (cnt == N) {
            int vowel = 0;
            int consonant = 0;
            for (char ch : str.toCharArray()) {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowel++;
                } else {
                    consonant++;
                }
            }
            if (vowel >= 1 && consonant >= 2) {
                sb.append(str.trim()).append("\n");
            }
            return;
        }

        for (int i = idx; i < M; i++) {
            backtrack(cnt + 1, i + 1, str + "" + nums[i]);
        }
    }

}
