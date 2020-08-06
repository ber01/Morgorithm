import java.io.*;
import java.util.*;

public class B2667 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine().trim());
        int[][] map = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        Queue<Point> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            char[] arr = br.readLine().trim().toCharArray();
            for (int j = 0; j < arr.length; j++) {
                map[i][j] = arr[j] - '0';
            }
        }

        StringBuilder sb = new StringBuilder();
        int[] di = {-1, 1, 0, 0};
        int[] dj = {0, 0, -1, 1};
        int home = 0;
        int cnt = 0;
        List<Integer> cList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 || visited[i][j]) continue;
                queue.offer(new Point(i, j));
                visited[i][j] = true;
                while (!queue.isEmpty()) {
                    Point p = queue.poll();
                    visited[p.i][p.j] = true;
                    cnt++;
                    int nowi = p.i;
                    int nowj = p.j;
                    for (int d = 0; d < 4; d++) {
                        int ni = nowi + di[d];
                        int nj = nowj + dj[d];
                        if (ni >= N || ni < 0 || nj >= N || nj < 0 || map[ni][nj] == 0 || visited[ni][nj]) continue;
                        visited[ni][nj] = true;
                        queue.offer(new Point(ni, nj));
                    }
                }
                cList.add(cnt);
                cnt = 0;
                home++;
            }
        }

        Collections.sort(cList);
        sb.append(home).append("\n");
        for (int c : cList) {
            sb.append(c).append("\n");
        }
        bw.write(sb.toString().trim());
        bw.flush();
    }

    static class Point {
        int i;
        int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "(" + i + ", " + j + ")";
        }
    }

}
