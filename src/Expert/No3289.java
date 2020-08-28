package Expert;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No3289 {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            parent = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }
            System.out.println(Arrays.toString(parent));
            StringBuilder result = new StringBuilder();
            while (M-- > 0) {
                st = new StringTokenizer(br.readLine().trim());
                int operator = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                switch (operator) {
                    case 0 : {
                        union(a, b);
                    } break;

                    case 1 : {
                        result.append(find(a, b));
                    } break;
                }

                System.out.println(Arrays.toString(parent));
            }

            sb.append("#").append(t).append(" ").append(result.toString()).append("\n");
        }
        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void union(int a, int b) {
        parent[find(a)] = find(b);
    }

    private static int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private static int find(int a, int b) {
        return find(a) == find(b) ? 1 : 0;
    }

}
