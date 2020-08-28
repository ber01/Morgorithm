package Expert;

import java.io.*;

public class No1233 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            int N = Integer.parseInt(br.readLine().trim());
            int ans = 1;
            for (int i = 0; i < N; i++) {
                String[] line = br.readLine().split(" ");
                char ch = line[1].charAt(0);
                if (((ch == '+' || ch == '-' || ch == '*' || ch == '/') && line.length < 4) || ((ch >= '0' && ch <= '9') && line.length > 2)) {
                    ans = 0;
                }
            }
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        bw.write(sb.toString().trim());
        bw.flush();
    }

}
