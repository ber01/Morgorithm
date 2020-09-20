package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class B2563 {

    static int N;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);

        bw.write(solve() + "");
        bw.flush();
    }

    private static int solve() {
        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (arr[i][j] != 0) cnt++;
            }
        }
        return cnt;
    }

    private static void init(BufferedReader br) throws IOException {
        arr = new int[100][100];
        N = Integer.parseInt(br.readLine().trim());
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            for (int i = a; i < a + 10; i++) {
                for (int j = b; j < b + 10; j++) {
                    arr[i][j]++;
                }
            }
        }
    }

}
