package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class B15961 {

    static int N, d, k, c, ans = 0;
    static int[] visited, arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);

        visited[c]++;
        ans++;
        for (int i = 0; i < k; i++) {
            getAns(arr[i]);
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N - 1; i++) {
            visited[arr[i]]--;
            if (visited[arr[i]] == 0) ans--;

            getAns(arr[(i + k) % N]);
            max = Math.max(max, ans);
        }

        bw.write(String.valueOf(max));
        bw.flush();
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        visited = new int[d + 1];
        arr = new int[N];

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine().trim());
    }

    private static void getAns(int index) {
        if (visited[index] == 0) ans++;
        visited[index]++;
    }

}
