package BOJ;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class B2605 {

    static int N;
    static int[] arr;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);
        solve();

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void solve() {
        List<Integer> lst = new ArrayList<>();
        int cnt = 1;
        for (int i = 0; i < N; i++) {
            lst.add(lst.size() - arr[i], cnt);
            cnt++;
        }
        sb.append(lst.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static void init(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();
    }

}
