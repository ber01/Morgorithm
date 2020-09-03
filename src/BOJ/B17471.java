package BOJ;

import java.io.*;
import java.util.*;

public class B17471 {

    static int N, R, min;
    static int[] person;
    static List<Integer>[] adjList;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);
        for (int i = 1; i <= N / 2; i++) {
            R = i;
            backtrack(0, 0);
        }

        min = min == Integer.MAX_VALUE ? -1 : min;
        bw.write(String.valueOf(min));
        bw.flush();
    }

    private static void backtrack(int idx, int cnt) {
        if (cnt == R) {
            List<Integer> A = new ArrayList<>();
            List<Integer> B = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    A.add(i + 1);
                } else {
                    B.add(i + 1);
                }
            }

            if (connect(A, false) && connect(B, true)) calc(A, B);
            return;
        }

        for (int i = idx; i < N; i++) {
            visited[i] = true;
            backtrack(i + 1, cnt + 1);
            visited[i] = false;
        }
    }

    private static void calc(List<Integer> A, List<Integer> B) {
        int sumA = 0, sumB = 0;
        for (int next : A) sumA += person[next];
        for (int next : B) sumB += person[next];
        min = Math.min(min, Math.abs(sumA - sumB));
    }

    private static boolean connect(List<Integer> area, boolean flag) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] check = new boolean[N + 1];
        queue.offer(area.get(0));

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            check[cur] = true;
            for (int next : adjList[cur]) {
                if (check[next] || visited[next - 1] == flag) continue;
                check[next] = true;
                queue.offer(next);
            }
        }

        for (int next : area) if (!check[next]) return false;
        return true;
    }

    private static void init(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        person = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for (int i = 1; i <= N; i++) person[i] = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int n = Integer.parseInt(st.nextToken());
            while (n-- > 0) {
                adjList[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        visited = new boolean[N];
        min = Integer.MAX_VALUE;
    }

}