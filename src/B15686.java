import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B15686 {

    static class Point {
        int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    static int N, M, ret;
    static int[] num;
    static List<Point> home, chicken;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        home = new ArrayList<>();
        chicken = new ArrayList<>();

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) home.add(new Point(i, j));
                if (map[i][j] == 2) chicken.add(new Point(i, j));
            }
        }

        num = new int[M];
        ret = Integer.MAX_VALUE;
        backtrack(0, 0);

        bw.write(String.valueOf(ret));
        bw.flush();
    }

    private static int dist(Point h, Point c) {
        return Math.abs(h.i - c.i) + Math.abs(h.j - c.j);
    }

    private static void backtrack(int idx, int i) {
        if (idx == M) {
            int result = 0;
            for (Point h : home) {
                int min = Integer.MAX_VALUE;
                for (int index : num) {
                    Point c = chicken.get(index);
                    min = Math.min(min, dist(h, c));
                }
                result += min;
            }
            ret = Math.min(ret, result);
            return;
        }

        if (i == chicken.size()) return;

        num[idx] = i;
        backtrack(idx + 1, i + 1);
        backtrack(idx, i + 1);
    }

}
