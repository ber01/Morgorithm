import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class B15665 {

    static int N, M;
    static int[] nums;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; st.hasMoreTokens(); i++) nums[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(nums);

        Set<String> ret = new LinkedHashSet<>();
        backtrack(ret, 0, "");

        for (String str : ret) sb.append(str.trim()).append("\n");

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void backtrack(Set<String> ret, int cnt, String str) {
        if (cnt == M) {
            ret.add(str);
            return;
        }

        for (int i = 0; i < N; i++) {
            visited[i] = true;
            backtrack(ret, cnt + 1, str + " " + nums[i]);
            visited[i] = false;
        }
    }

}
