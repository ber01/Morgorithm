package BOJ;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B2564 {

    static class Point {
        int i, j, dir;

        public Point(int i, int j, int dir) {
            this.i = i;
            this.j = j;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "(" + i + ", " + j + ")[" + dir + "]";
        }
    }
    static int X, Y;
    static List<Point> lst;
    static Point D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);

        bw.write(solve() + "");
        bw.flush();
    }

    private static int solve() {
        // 1 : 북, 2 : 남, 3 : 서, 4 : 동
        List<Integer> dist = new ArrayList<>();
        for (Point p : lst) {
            int a = p.i;
            int b = p.j;
            int dir = p.dir;
            if (D.dir == 1) { // 북
                if (dir == 1) { // 북
                    int dist1 = Math.abs(b - D.j);
                    int dist2 = X + 2 * Y + X - Math.abs(b - D.j);
                    dist.add(Math.min(dist1, dist2));
                } else if (dir == 2) { // 남
                    int dist1 = D.j + Y + b;
                    int dist2 = X - D.j + Y + X - b;
                    dist.add(Math.min(dist1, dist2));
                } else if (dir == 3) { // 서
                    int dist1 = D.j + (Y - a);
                    int dist2 = X - D.j + Y + X + a;
                    dist.add(Math.min(dist1, dist2));
                } else { // 동
                    int dist1 = X - D.j + Y - a;
                    int dist2 = D.j + Y + X + a;
                    dist.add(Math.min(dist1, dist2));
                }
            } else if (D.dir == 2) { // 남
                if (dir == 1) { // 북
                    int dist1 = D.j + Y + b;
                    int dist2 = X - D.j + Y + X - b;
                    dist.add(Math.min(dist1, dist2));
                } else if (dir == 2) { // 남
                    int dist1 = Math.abs(b - D.j);
                    int dist2 = X + 2 * Y + X - Math.abs(b - D.j);
                    dist.add(Math.min(dist1, dist2));
                } else if (dir == 3) { // 서
                    int dist1 = D.j + a;
                    int dist2 = X - D.j + Y + X + Y - a;
                    dist.add(Math.min(dist1, dist2));
                } else { // 동
                    int dist1 = X - D.j + a;
                    int dist2 = D.j + Y + X + Y - a;
                    dist.add(Math.min(dist1, dist2));
                }
            } else if (D.dir == 3) { // 서
                if (dir == 1) { // 북
                    int dist1 = Y - D.i + b;
                    int dist2 = D.i + X + Y + X - b;
                    dist.add(Math.min(dist1, dist2));
                } else if (dir == 2) { // 남
                    int dist1 = D.i + b;
                    int dist2 = Y - D.i + X + Y + X - b;
                    dist.add(Math.min(dist1, dist2));
                } else if (dir == 3) { // 서
                    int dist1 = Math.abs(a - D.i);
                    int dist2 = 2 * X + Y + Y - Math.abs(a - D.i);
                    dist.add(Math.min(dist1, dist2));
                } else { // 동
                    int dist1 = X + Y - D.i + Y - a;
                    int dist2 = X + D.i + a;
                    dist.add(Math.min(dist1, dist2));
                }
            } else { // 동
                if (dir == 1) { // 북
                    int dist1 = X - b + Y - D.i;
                    int dist2 = X + Y + D.i + b;
                    dist.add(Math.min(dist1, dist2));
                } else if (dir == 2) { // 남
                    int dist1 = D.i + X - b;
                    int dist2 = X + Y + Y - D.i + b;
                    dist.add(Math.min(dist1, dist2));
                } else if (dir == 3) { // 서
                    int dist1 = X + D.i + a;
                    int dist2 = X + Y - D.i + Y - a;
                    dist.add(Math.min(dist1, dist2));
                } else { // 동
                    int dist1 = Math.abs(a - D.i);
                    int dist2 = 2 * X + Y + Y - Math.abs(a - D.i);
                    dist.add(Math.min(dist1, dist2));
                }
            }
        }
        return dist.stream().mapToInt(Integer::intValue).sum();
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        lst = new ArrayList<>();
        int N = Integer.parseInt(br.readLine().trim());
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == 1) { // 북
                lst.add(new Point(Y, b, a));
            } else if (a == 2) { // 남
                lst.add(new Point(0, b, a));
            } else if (a == 3) { // 서
                lst.add(new Point(Y - b, 0, a));
            } else { // 동
                lst.add(new Point(Y - b, X, a));
            }
        }
        st = new StringTokenizer(br.readLine().trim());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        if (a == 1) { // 북
            D = new Point(Y, b, a);
        } else if (a == 2) { // 남
            D = new Point(0, b, a);
        } else if (a == 3) { // 서
            D = new Point(Y - b, 0, a);
        } else { // 동
            D = new Point(Y - b, X, a);
        }
    }

}
