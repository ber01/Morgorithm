package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B9019 {

    static class Point {
        int n;
        String result;

        public Point(int n, String result) {
            this.n = n;
            this.result = result;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "n=" + n +
                    ", result='" + result + '\'' +
                    '}';
        }
    }
    static int A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder result = new StringBuilder();

        int a = 0;
        int temp = 1;
        for (int i = 1; i < 10; i++) {
            System.out.println(i + " " + temp);
            int b = i * (temp *= 10);
            a += b;
            System.out.println(a + " " + b);
        }

        int N = Integer.parseInt(br.readLine().trim());
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            result.append(bfs()).append("\n");
        }

        bw.write(result.toString().trim());
        bw.flush();
    }

    private static String bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(A, ""));

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Point poll = queue.poll();
                String str = String.valueOf(poll.n);
                String result = poll.result;

                if (poll.n == B) {
                    return poll.result;
                }

                queue.offer(new Point(Integer.parseInt(moveD(str)), result + "D"));
                queue.offer(new Point(Integer.parseInt(moveS(str)), result + "S"));
                queue.offer(new Point(Integer.parseInt(moveL(str)), result + "L"));
                queue.offer(new Point(Integer.parseInt(moveR(str)), result + "R"));
            }
        }

        return null;
    }

    private static String moveD(String str) {
        int n = Integer.parseInt(str);
        n *= 2;
        if (n > 9999) return String.valueOf(n % 10000);
        return String.valueOf(n);
    }

    private static String moveS(String str) {
        int n = Integer.parseInt(str);
        n -= 1;
        if (n == 0) return String.valueOf(9999);
        return String.valueOf(n);
    }

    private static String moveL(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < str.length(); i++) {
            sb.append(str.charAt(i));
        }
        sb.append(str.charAt(0));
        return String.valueOf(Integer.parseInt(sb.toString()));
    }

    private static String moveR(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(str.length() - 1));
        for (int i = 0; i < str.length() - 1; i++) {
            sb.append(str.charAt(i));
        }
        return String.valueOf(Integer.parseInt(sb.toString()));
    }

}
