package BOJ;

import java.io.*;
import java.util.*;

public class B2477 {

    static class Point {
        int dir, dist;

        public Point(int dir, int dist) {
            this.dir = dir;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine().trim());
        int width = 0, height = 0;
        List<Point> lst = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int dir = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            lst.add(new Point(dir, dist));

            if (dir == 1 || dir == 2) width = Math.max(width, dist);
            if (dir == 3 || dir == 4) height = Math.max(height, dist);
        }

        int big = width * height;
        int small = 0;
        for (int i = 0; i < lst.size() - 1; i++) {
            Point cur = lst.get(i);
            Point next = lst.get(i + 1);
            small += cur.dist * next.dist;
        }
        small += lst.get(0).dist * lst.get(lst.size() - 1).dist;

        System.out.println(big);
        System.out.println(small);

        small -= big * 2;

        bw.write(small * N + "");
        bw.flush();
    }

}
