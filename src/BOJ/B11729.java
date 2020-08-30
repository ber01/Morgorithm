package BOJ;

import java.io.*;

public class B11729 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        sb.append((1 << n) - 1).append("\n");
        divide(n, 1, 3, sb);

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void divide(int n, int x, int y, StringBuilder sb) {
        if (n == 0 ) return;
        divide(n - 1, x, 6 - x - y, sb);
        sb.append(x).append(" ").append(y).append("\n");
        divide(n - 1, 6 - x - y, y, sb);
    }

}
