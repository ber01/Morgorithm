package BOJ;

import java.io.*;
import java.util.*;

public class B14891 {

    static int N, M, K;
    static List<Integer>[] gears;
    static char[][] print;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(br);

         // test(gears);
        K = Integer.parseInt(br.readLine().trim());
        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            if (num == 1) {
                if (gears[num].get(2) == gears[num + 1].get(6)) { // 1, 2 비교, 맞닿은 극이 같으면
                    rotation(dir, (LinkedList<Integer>) gears[num]); // 1번만 돌린다
                }  else { // 맞닿은 극이 다르면
                    rotation(dir, (LinkedList<Integer>) gears[num]); // 1번은 일단 돌고
                    num += 1; dir *= -1; // 방향 바꾸고
                    if (gears[num].get(2) == gears[num + 1].get(6)) { // 2, 3 비교, 맞닿은 극이 같다면
                        rotation(dir, (LinkedList<Integer>) gears[num]); // 2번 돌리고 마무리
                    } else { // 맞닿은 극이 다르면
                        rotation(dir, (LinkedList<Integer>) gears[num]); // 2번을 돌리고
                        num += 1; dir *= -1; // 방향 바꾸고
                        if (gears[num].get(2) == gears[num + 1].get(6)) { // 3, 4 비교, 맞닿은 극이 같다면
                            rotation(dir, (LinkedList<Integer>) gears[num]); // 3번만 돌리고 마무리
                        } else { // 맞닿은 극이 다르면
                            rotation(dir, (LinkedList<Integer>) gears[num]); // 3번을 돌리고
                            num += 1; dir *= -1; // 방향 바꾸고
                            rotation(dir, (LinkedList<Integer>) gears[num]); // 4번도 돌린다
                        }
                    }
                }
            } else if (num == 2) {
                boolean leftFlag = gears[num].get(6) == gears[num - 1].get(2); // 1, 2 비교
                boolean rightFlag = gears[num].get(2) == gears[num + 1].get(6); // 2, 3 비교

                // 일단 왼쪽 오른쪽을 비교해서 분기를 시킨다.
                if (leftFlag && rightFlag) {
                   rotation(dir, (LinkedList<Integer>) gears[num]);
                }  else if (leftFlag && !rightFlag) { // 왼쪽, 1, 2 비교 맞닿은 극이 같다면, 오른쪽은 다르니 2번은 돌려야 함
                    rotation(dir, (LinkedList<Integer>) gears[num]); // 2 번은 돌리고, 1번은 무시한다.
                    if (gears[num + 1].get(2) == gears[num + 2].get(6)) { // 3번과 4번의 맞닿은 극이 같다면
                        rotation(dir * -1, (LinkedList<Integer>) gears[num + 1]); // 3번만 돌리고 마무리
                    } else { // 맞닿은 극이 다르면
                        rotation(dir * -1, (LinkedList<Integer>) gears[num + 1]); // 3번을 돌리고
                        rotation(dir, (LinkedList<Integer>) gears[num + 2]); // 4번도 돌린다.
                    }
                } else if (!leftFlag && rightFlag) { // 오른쪽, 3, 4비교 맞닿은 극이 같다면, 왼쪽은 다르니 1번은 돌려야함
                    rotation(dir, (LinkedList<Integer>) gears[num]); // 2번 돌리고
                    rotation(dir * -1, (LinkedList<Integer>) gears[num - 1]); // 1번도 돌린다
                } else { // 둘 다 틀려! (둘 다 틀릴 경우)
                    rotation(dir * -1, (LinkedList<Integer>) gears[num - 1]); // 그러면 1은 일단 돌리고
                    // 3이랑 4랑 같은지 비교
                    if (gears[num + 1].get(2) == gears[num + 2].get(6)) { // 같냐? 그럼 2랑 3만 돌려
                        rotation(dir, (LinkedList<Integer>) gears[num]); // 2 돌려
                        rotation(dir * -1, (LinkedList<Integer>) gears[num + 1]); // 3 돌려
                    } else { // 틀리냐? 그럼 2, 3, 4 돌려
                        rotation(dir, (LinkedList<Integer>) gears[num]);
                        rotation(dir * -1, (LinkedList<Integer>) gears[num + 1]);
                        rotation(dir, (LinkedList<Integer>) gears[num + 2]);
                    }
                }
            } else if (num == 3) {
                boolean leftFlag = gears[num].get(6) == gears[num - 1].get(2); // 2, 3 비교
                boolean rightFlag = gears[num].get(2) == gears[num + 1].get(6); // 3, 4 비교
                if (leftFlag && rightFlag) {
                    rotation(dir, (LinkedList<Integer>) gears[num]);
                } else if (leftFlag && !rightFlag) { // 2, 3 만 같아! 오른쪽이 틀리니까 4도 돌려야 함
                    rotation(dir, (LinkedList<Integer>) gears[num]); // 3을 돌리고
                    rotation(dir * -1, (LinkedList<Integer>) gears[num + 1]); // 4도 돌리자
                } else if (!leftFlag && rightFlag) { // 3, 4 만 같아!, 왼쪽이 틀리니까 2도 돌려야 함
                    rotation(dir, (LinkedList<Integer>) gears[num]); // 일단 3은 무조건 돌고
                    if (gears[num - 1].get(6) == gears[num - 2].get(2)) { // 1, 2가 같냐?
                        rotation(dir * -1, (LinkedList<Integer>) gears[num - 1]); // 그럼 2만 돌리고
                    } else { // 틀리냐? 그럼 1, 2 돌려
                        rotation(dir * -1, (LinkedList<Integer>) gears[num - 1]); // 2번 돌리고
                        rotation(dir, (LinkedList<Integer>) gears[num - 2]); // 1번도 돌리자
                    }
                } else { // 둘 다 틀려!
                    rotation(dir * -1, (LinkedList<Integer>) gears[num + 1]); // 그러면 4는 일단 돌리고
                    // 2랑 1이랑 같은지 비교
                    if (gears[num - 1].get(6) == gears[num - 2].get(2)) { // 같냐? 그럼 2랑 3만 돌려
                        rotation(dir, (LinkedList<Integer>) gears[num]);
                        rotation(dir * -1, (LinkedList<Integer>) gears[num - 1]);
                    } else { // 틀리냐? 그럼 1, 2, 3 돌려
                        rotation(dir, (LinkedList<Integer>) gears[num]); // 3
                        rotation(dir * -1, (LinkedList<Integer>) gears[num - 1]); // 2
                        rotation(dir, (LinkedList<Integer>) gears[num - 2]); // 1
                    }
                }
            } else {
                // 1번이랑 반대로 하면 됨
                if (gears[num].get(6) == gears[num - 1].get(2)) {
                    rotation(dir, (LinkedList<Integer>) gears[num]);
                } else {
                    rotation(dir, (LinkedList<Integer>) gears[num]);
                    num -= 1; dir *= -1;
                    if (gears[num].get(6) == gears[num - 1].get(2)) {
                        rotation(dir, (LinkedList<Integer>) gears[num]);
                    } else {
                        rotation(dir, (LinkedList<Integer>) gears[num]);
                        num -= 1; dir *= -1;
                        if (gears[num].get(6) == gears[num - 1].get(2)) {
                            rotation(dir, (LinkedList<Integer>) gears[num]);
                        } else {
                            rotation(dir, (LinkedList<Integer>) gears[num]);
                            num -= 1; dir *= -1;
                            rotation(dir, (LinkedList<Integer>) gears[num]);
                        }
                    }
                }
            }
             // test(gears);
        }

        bw.write(score() + "");
        bw.flush();
    }

    private static void test(List<Integer>[] gears) {
        print = new char[3][15];
        print[0][0] = gears[1].get(7) == 1 ? 'S' : 'N';
        print[0][1] = gears[1].get(0) == 1 ? 'S' : 'N';
        print[0][2] = gears[1].get(1) == 1 ? 'S' : 'N';
        print[1][0] = gears[1].get(6) == 1 ? 'S' : 'N';
        print[1][1] = '+';
        print[1][2] = gears[1].get(2) == 1 ? 'S' : 'N';
        print[2][0] = gears[1].get(5) == 1 ? 'S' : 'N';
        print[2][1] = gears[1].get(4) == 1 ? 'S' : 'N';
        print[2][2] = gears[1].get(3) == 1 ? 'S' : 'N';

        print[0][3] = ' ';
        print[1][3] = ' ';
        print[2][3] = ' ';

        print[0][4] = gears[2].get(7) == 1 ? 'S' : 'N';
        print[0][5] = gears[2].get(0) == 1 ? 'S' : 'N';
        print[0][6] = gears[2].get(1) == 1 ? 'S' : 'N';
        print[1][4] = gears[2].get(6) == 1 ? 'S' : 'N';
        print[1][5] = '+';
        print[1][6] = gears[2].get(2) == 1 ? 'S' : 'N';
        print[2][4] = gears[2].get(5) == 1 ? 'S' : 'N';
        print[2][5] = gears[2].get(4) == 1 ? 'S' : 'N';
        print[2][6] = gears[2].get(3) == 1 ? 'S' : 'N';

        print[0][7] = ' ';
        print[1][7] = ' ';
        print[2][7] = ' ';

        print[0][8] = gears[3].get(7) == 1 ? 'S' : 'N';
        print[0][9] = gears[3].get(0) == 1 ? 'S' : 'N';
        print[0][10] = gears[3].get(1) == 1 ? 'S' : 'N';
        print[1][8] = gears[3].get(6) == 1 ? 'S' : 'N';
        print[1][9] = '+';
        print[1][10] = gears[3].get(2) == 1 ? 'S' : 'N';
        print[2][8] = gears[3].get(5) == 1 ? 'S' : 'N';
        print[2][9] = gears[3].get(4) == 1 ? 'S' : 'N';
        print[2][10] = gears[3].get(3) == 1 ? 'S' : 'N';

        print[0][11] = ' ';
        print[1][11] = ' ';
        print[2][11] = ' ';

        print[0][12] = gears[4].get(7) == 1 ? 'S' : 'N';
        print[0][13] = gears[4].get(0) == 1 ? 'S' : 'N';
        print[0][14] = gears[4].get(1) == 1 ? 'S' : 'N';
        print[1][12] = gears[4].get(6) == 1 ? 'S' : 'N';
        print[1][13] = '+';
        print[1][14] = gears[4].get(2) == 1 ? 'S' : 'N';
        print[2][12] = gears[4].get(5) == 1 ? 'S' : 'N';
        print[2][13] = gears[4].get(4) == 1 ? 'S' : 'N';
        print[2][14] = gears[4].get(3) == 1 ? 'S' : 'N';

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 15; j++) {
                sb.append(print[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    private static void init(BufferedReader br) throws IOException {
        N = 4;
        M = 8;
        gears = new LinkedList[N + 1];
        for (int i = 1; i <= N; i++) {
            gears[i] = new LinkedList<>();
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                gears[i].add(chars[j] - '0');
            }
        }
    }

    private static int score() {
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (gears[i].get(0) == 1) {
                ans += Math.pow(2, i - 1);
            }
        }
        return ans;
    }

    private static void rotation(int dir, LinkedList<Integer> gear) {
        if (dir == 1) right(gear);
        else left(gear);
    }

    private static void right(LinkedList<Integer> gear) {
        gear.addFirst(gear.removeLast());
    }

    private static void left(LinkedList<Integer> gear) {
        gear.addLast(gear.removeFirst());
    }

}