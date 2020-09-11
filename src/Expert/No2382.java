package Expert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class No2382 {

    static class Group {
        int size, dir;
        boolean flag;

        public Group(int size, int dir, boolean flag) {
            this.size = size;
            this.dir = dir;
            this.flag = flag;
        }
    }
    static int N, M, K;
    static List<Group>[][] map;
    static int[] di = {0, -1, 1, 0, 0};
    static int[] dj = {0, 0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            init(br);
            solve();
            int result = checkSize();
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static int checkSize() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].size() != 0) {
                    result += map[i][j].get(0).size;
                }
            }
        }
        return result;
    }

    private static void solve() {
        while (M-- > 0) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j].size() != 0) {
                        Group group = map[i][j].get(0);
                        if (group.flag) continue;
                        map[i][j].remove(group);
                        int size = group.size;
                        int dir = group.dir;
                        int ni = i + di[dir];
                        int nj = j + dj[dir];
                        // (상: 1, 하: 2, 좌: 3, 우: 4)
                        if (ni == 0 || nj == 0 || ni == N - 1 || nj == N - 1) {
                            size = size / 2;
                            if (size == 0) {
                                map[i][j].clear();
                            }
                            if (dir == 1) {
                                dir = 2;
                            } else if (dir == 2) {
                                dir = 1;
                            } else if (dir == 3) {
                                dir = 4;
                            } else {
                                dir = 3;
                            }
                        }
                        Group newGroup = new Group(size, dir, true);
                        map[ni][nj].add(newGroup);
                    }
                }
            }
            checkList();
        }
    }

    private static void checkList() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].size() > 1) {
                    int max = Integer.MIN_VALUE;
                    int dir = -1;
                    for (Group group : map[i][j]) {
                        if (group.size > max) {
                            dir = group.dir;
                            max = group.size;
                        }
                    }
                    while (map[i][j].size() != 1) {
                        Group g1 = map[i][j].remove(0);
                        Group g2 = map[i][j].remove(0);
                        int size = g1.size + g2.size;
                        map[i][j].add(new Group(size, dir, false));
                    }
                } else if (map[i][j].size() == 1 && map[i][j].get(0).flag) {
                    map[i][j].get(0).flag = false;
                }
            }
        }
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            map[i][j].add(new Group(size, dir, false));
        }
    }

}
