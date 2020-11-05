package BOJ;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B17143 {

    static int R, C, M, ans;
    static int[] di = {0, -1, 1, 0, 0};
    static int[] dj = {0, 0, 0, 1, -1};
    static List<Shark>[][] map;
    static class Shark {
        int r, c, s, d, z;
        boolean flag;

        public Shark(int r, int c, int s, int d, int z, boolean flag) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
            this.flag = flag;
        }

        @Override
        public String toString() {
            return "(" + s + ", " + print(d) + ", " + z + ", " + flag + ")";
        }
    }

    public static String print(int d) {
        if (d == 1) return "상";
        else if (d == 2) return "하";
        else if (d == 3) return "우";
        return "좌";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);

        solve();

        bw.write(ans + "");
        bw.flush();
    }

    private static void solve() {

        for (int t = 1; t <= C; t++) {
            // 낚시
            fish(t);

            // System.out.println(t + "초 경과");
            for (int i = 1; i < R + 1; i++) {
                for (int j = 1; j < C + 1; j++) {
                    if (!map[i][j].isEmpty()) { // 상어가 있는경우 이동
                        boolean flag = true;
                        for (Shark shark : map[i][j]) {
                            if (!shark.flag) {
                                flag = false;
                                break;
                            }
                        }
                        if (!flag) goShark(map[i][j]);
                    }
                }
            }

            // 상어가 이동했다는 처리를 false로 되돌린다.
            restore();

            // 2개 이상 들어있는 상어를 죽이자.
            kill();

            // printMap();
        }
    }

    private static void kill() {
        for (int i = 1; i < R + 1; i++) {
            for (int j = 1; j < C + 1; j++) {
                if (map[i][j].size() > 1) {
                    int MAX = 0;
                    Shark newShark = null;
                    for (Shark shark : map[i][j]) {
                        if (shark.z > MAX) {
                            newShark = shark;
                            MAX = shark.z;
                        }
                    }
                    map[i][j].clear();
                    map[i][j].add(newShark);
                }
            }
        }
    }

    private static void restore() {
        for (int i = 1; i < R + 1; i++) {
            for (int j = 1; j < C + 1; j++) {
                if (!map[i][j].isEmpty()) {
                    for (Shark shark : map[i][j]) {
                        shark.flag = false;
                    }
                }
            }
        }
    }

    private static void fish(int time) {
        for (int i = 1; i < R + 1; i++) {
            if (!map[i][time].isEmpty()) {
                ans += map[i][time].get(0).z;
                map[i][time].clear();
                return;
            }
        }
    }

    private static void goShark(List<Shark> sharks) {
        Shark shark = null;
        int idx = 0;
        for (Shark next : sharks) {
            if (next.flag) {
                idx++;
                continue;
            }
            shark = next;
            sharks.remove(idx);
            break;
        }

        int sec = shark.s;
        int dir = shark.d;

        int ci = shark.r;
        int cj = shark.c;

        int ni = ci, nj = cj;
        while (sec-- > 0) {
            ni = ci + di[dir];
            nj = cj + dj[dir];
            if (ni > R || ni < 1 || nj > C || nj < 1) {
                switch (dir) {
                    case 1 : dir = 2; break;
                    case 2 : dir = 1; break;
                    case 3 : dir = 4; break;
                    case 4 : dir = 3; break;
                }
                ni = ci + di[dir];
                nj = cj + dj[dir];
            }
            ci = ni;
            cj = nj;
        }

        Shark newShark = new Shark(ni, nj, shark.s, dir, shark.z, true);
        map[ni][nj].add(newShark);
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new ArrayList[R + 1][C + 1];
        for (int i = 0; i < R + 1; i++) {
            for (int j = 0; j < C + 1; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int r = Integer.parseInt(st.nextToken()); // 행
            int c = Integer.parseInt(st.nextToken()); // 열
            int s = Integer.parseInt(st.nextToken()); // 속력
            int d = Integer.parseInt(st.nextToken()); // 이동방향
            int z = Integer.parseInt(st.nextToken()); // 크기
            Shark shark = new Shark(r, c, s, d, z, false);
            map[r][c].add(shark);
        }
    }

    private static void printMap() {
        for (int i = 1; i < R + 1; i++) {
            for (int j = 1; j < C + 1; j++) {
                System.out.printf("%15s", map[i][j]);
            }
            System.out.println();
        }

        System.out.println("\n");
    }

}
