package Expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class No1289 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {

            String str = br.readLine().trim();
            int[] memory = new int[str.length()];
            int[] zero = new int[str.length()];

            for (int i = 0; i < str.length(); i++) {
                memory[i] = str.charAt(i) - '0';
            }

            int result = 0;
            for (int i = 0; i < memory.length; i++) {
                if (memory[i] != zero[i]) {
                    result++;
                    int change = memory[i];
                    for (int j = i; j < memory.length; j++) {
                        zero[j] = change;
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.print(sb.toString().trim());
    }
}
