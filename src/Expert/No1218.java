package Expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class No1218 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            int N = Integer.parseInt(br.readLine().trim());
            sb.append("#").append(t).append(" ").append(isValid(br.readLine().toCharArray())).append("\n");
        }

        System.out.print(sb.toString().trim());
    }

    private static int isValid(char[] chars) {
        int cnt1 = 0;
        int cnt2 = 0;
        int cnt3 = 0;
        int cnt4 = 0;
        for (char c : chars) {
            if (c == '(') {
                cnt1++;
            } else if (c == '{') {
                cnt2++;
            } else if (c == '[') {
                cnt3++;
            } else if (c == '<') {
                cnt4++;
            } else if (c == ')') {
                cnt1--;
            } else if (c == '}') {
                cnt2--;
            } else if (c == ']') {
                cnt3--;
            } else if (c == '>') {
                cnt4--;
            }
            if (cnt1 < 0 || cnt2 < 0 || cnt3 < 0 || cnt4 < 0) {
                return 0;
            }
        }
        if (cnt1 == 0 && cnt2 == 0 && cnt3 == 0 && cnt4 == 0) {
            return 1;
        } else {
            return 0;
        }
    }

}
