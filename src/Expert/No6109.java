package Expert;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No6109 {

static int N;
static int[][] copy;
public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine().trim());
    for (int t = 1; t <= T; t++) {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        String S = st.nextToken();

        int dir = 0;
        switch (S) {
            case "right" : dir = 0; break;
            case "left" : dir = 1; break;
            case "down" : dir = 2; break;
            case "up" : dir = 3; break;
        }

        copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                copy[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        switch (dir) {
            case 0 : right(); break; // 동
            case 1 : left(); break; // 서
            case 2 : down(); break; // 남
            case 3 : up(); break; // 북
        }

        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp.append(copy[i][j]).append(" ");
            }
            temp.append("\n");
        }

        sb.append("#").append(t).append("\n").append(temp.toString().trim()).append("\n");
    }

    bw.write(sb.toString().trim());
    bw.flush();
}

// 동
private static void right() {
    Queue<Integer> queue = new LinkedList<>();

    for (int i = 0; i < N; i++) {
        for (int j = N - 1; j >= 0; j--) {
            if (copy[i][j] != 0) queue.offer(copy[i][j]);
            copy[i][j] = 0;
        }

        int j = N - 1;
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            if (copy[i][j] == 0) {
                copy[i][j] = poll;
            } else if (copy[i][j] == poll) {
                copy[i][j] = poll * 2;
                j--;
            } else {
                j--;
                copy[i][j] = poll;
            }
        }

    }
}

// 서
private static void left() {
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (copy[i][j] != 0) queue.offer(copy[i][j]);
            copy[i][j] = 0;
        }

        int j = 0;
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            if (copy[i][j] == 0) {
                copy[i][j] = poll;
            } else if (copy[i][j] == poll) {
                copy[i][j] = poll * 2;
                j++;
            } else {
                j++;
                copy[i][j] = poll;
            }
        }
    }
}

// 남
private static void down() {
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < N; i++) {
        for (int j = N - 1; j >= 0; j--) {
            if (copy[j][i] != 0) queue.offer(copy[j][i]);
            copy[j][i] = 0;
        }

        int j = N - 1;
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            if (copy[j][i] == 0) {
                copy[j][i] = poll;
            } else if (copy[j][i] == poll) {
                copy[j][i] = poll * 2;
                j--;
            } else {
                j--;
                copy[j][i] = poll;
            }
        }
    }
}

// 북
private static void up() {
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (copy[j][i] != 0) queue.offer(copy[j][i]);
            copy[j][i] = 0;
        }

        int j = 0;
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            if (copy[j][i] == 0) {
                copy[j][i] = poll;
            } else if (copy[j][i] == poll) {
                copy[j][i] = poll * 2;
                j++;
            } else {
                j++;
                copy[j][i] = poll;
            }
        }
    }
}
}