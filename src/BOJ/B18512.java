package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class B18512 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int h1 = Integer.parseInt(st.nextToken());
        int h2 = Integer.parseInt(st.nextToken());

        boolean isFlag = false;
        while (true) {
            while (h1 > h2) {
                h2 += y;
                if (h1 == h2) {
                    isFlag = true; break;
                }
            }
            if (isFlag) break;

            if (h2 >= 10000) break;

            while (h2 > h1) {
                h1 += x;
                if (h1 == h2) {
                    isFlag = true; break;
                }
            }
            if (isFlag) break;

            if (h1 >= 10000) break;
        }

        int result = isFlag ? h1 : -1;
        bw.write(String.valueOf(result));
        bw.flush();
    }

}
