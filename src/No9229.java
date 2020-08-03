import java.io.*;
import java.util.*;

public class No9229 {

    private static int N, M;
    private static int[] numbers;
    private static int[] a;
    private static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            a = new int[N];
            numbers = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; st.hasMoreTokens(); i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            list = new ArrayList<>();
            combination(0, 0);
            Collections.sort(list);

            int result = list.size() == 0 ? -1 : list.get(list.size() - 1);
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void combination(int cnt, int start) {
        if (cnt == 2) {
            int weight = numbers[0] + numbers[1];
            if (M >= weight) {
                list.add(weight);
            }
            return;
        }

        for (int i = start; i < N; i++) {
            numbers[cnt] = a[i];
            combination(cnt + 1, i + 1);
        }
    }

}
