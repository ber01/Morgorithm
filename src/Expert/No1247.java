package Expert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class No1247 {

    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "(" + i + ", " + j + ")";
        }
    }
    static int N, MIN;
    static int[] input;
    static int[] result;
    static boolean[] visited;
    static Point company, house;
    static List<Point> customerList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            customerList = new ArrayList<>();
            N = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            company = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            house = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                customerList.add(new Point(x, y));
            }

            init();

            backtrack(0);

            sb.append("#").append(t).append(" ").append(MIN).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static void init() {
        MIN = Integer.MAX_VALUE;
        input = new int[N];
        result = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) input[i] = i;
    }

    private static void backtrack(int cnt) {
        if (cnt == N) {
            int d = 0;
            d += dist(company, customerList.get(result[0]));
            d += dist(house, customerList.get(result[N - 1]));
            for (int i = 0; i < N -1; i++) {
                d += dist(customerList.get(result[i]), customerList.get(result[i + 1]));
            }
            MIN = Math.min(d, MIN);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            result[cnt] = input[i];
            backtrack(cnt + 1);
            visited[i] = false;
        }
    }

    public static int dist(Point p1, Point p2) {
        return Math.abs(p1.i - p2.i) + Math.abs(p1.j - p2.j);
    }

}
