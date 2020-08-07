import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class B17135 {

    private static class Location {
        int j1;
        int j2;
        int j3;

        public Location(int j1, int j2, int j3) {
            this.j1 = j1;
            this.j2 = j2;
            this.j3 = j3;
        }

        @Override
        public String toString() {
            return "(" + j1 + ", " + j2 + ", " + j3 + ")";
        }
    }

    private static int N, M, D;
    private static int[] input;
    private static int[] numbers;
    private static final int[] di = {0, -1, 1, 0};
    private static final int[] dj = {-1, 0, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        int[][] mapOrigin = new int[N + 1][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                mapOrigin[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        input = new int[M];
        IntStream.range(0, M).forEach(i -> input[i] = i);
        numbers = new int[3];

        List<Location> locations = new ArrayList<>();
        // 궁수의 모든 좌표 생성 (mC3)
        combination(0, 0, locations);
        System.out.println(locations);

        int[][] map = copyMap(mapOrigin);
        for (Location location : locations) {
            // 궁수 좌표 설정
            map[N][location.j1] = 9;
            map[N][location.j2] = 9;
            map[N][location.j3] = 9;
            printMap(map);
            System.out.println("==================");

            boolean isFlag = false;
            // 적의 좌표가 없을 때 까지 반복
            while (true) {
                // 턴이 끝나면 적은 내려간다.
                downMap(map);
                printMap(map);
                System.out.println("==================");
                if (isEnemy(map)) {
                    isFlag = true;
                    break;
                }
            }

            if (isFlag) break;

            // 궁수 좌표 초기화, map 초기화
            for (int i = 0; i < M; i++) {
                map[N][i] = 0;
            }
            map = copyMap(mapOrigin);
        }

        String result = "result";
        bw.write(result);
        bw.flush();
    }

    private static void downMap(int[][] map) {
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    map[i + 1][j] = map[i][j];
                }
            }
        }
        for (int i = 0; i < M; i++) {
            map[N - 1][i] = 0;
        }
    }

    private static int[][] copyMap(int[][] mapOrigin) {
        int[][] map = new int[N + 1][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = mapOrigin[i][j];
            }
        }
        return map;

    }

    private static void combination(int cnt, int start, List<Location> list) {
        if (cnt == 3) {
            list.add(new Location(numbers[0], numbers[1], numbers[2]));
            return;
        }

        for (int i = start; i < M; i++) {
            numbers[cnt] = input[i];
            combination(cnt + 1, i + 1, list);
        }
    }

    private static void printMap(int[][] map) {
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean isEnemy(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

}
