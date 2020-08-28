package BOJ;

import java.io.*;

public class B1110 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine().trim());
        int save = N;
        int cnt = 0;
        do {
            int n1 = N / 10;
            int n2 = N % 10;
            String s = n2 + "" + ((n1 + n2) % 10) + "";
            N = Integer.parseInt(s);
            cnt++;
        } while (save != N);

        bw.write(cnt + "");
        bw.flush();
    }

}
