package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class B14888 {

    static int N, MIN, MAX;
    static int[] A, operator;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);

        backtrack(1, 0, 0, 0, 0, A[0]);

        StringBuilder sb = new StringBuilder();
        sb.append(MAX).append("\n").append(MIN);

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void init(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        MIN = Integer.MAX_VALUE;
        MAX = Integer.MIN_VALUE;
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; st.hasMoreTokens(); i++) A[i] = Integer.parseInt(st.nextToken());

        operator = new int[4];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; st.hasMoreTokens(); i++) operator[i] = Integer.parseInt(st.nextToken());
    }

    private static void backtrack(int cnt, int plus, int minus, int mul, int div, int ans) {
        if (cnt == N) {
            MAX = Math.max(MAX, ans);
            MIN = Math.min(MIN, ans);
            return;
        }

        if (operator[0] > plus) backtrack(cnt + 1, plus + 1, minus, mul, div, ans + A[cnt]);
        if (operator[1] > minus) backtrack(cnt + 1, plus, minus + 1, mul, div, ans - A[cnt]);
        if (operator[2] > mul) backtrack(cnt + 1, plus, minus, mul + 1, div, ans * A[cnt]);
        if (operator[3] > div) backtrack(cnt + 1, plus, minus, mul, div + 1, ans / A[cnt]);
    }

}
