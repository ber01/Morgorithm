package BOJ;

import java.io.*;

public class B2839 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine().trim());

        int mod3 = 0;
        int mod5 = N / 5;

        N %= 5;

        while (mod5 >= 0) {
            if (N % 3 == 0) {
                mod3 = N / 3;
                N %= 3;
                break;
            }
            mod5--;
            N += 5;
        }

        System.out.println(mod3 + " " + mod5);

        int result = N == 0 ? mod3 + mod5 : -1;

        bw.write(result + "");
        bw.flush();
    }

}
