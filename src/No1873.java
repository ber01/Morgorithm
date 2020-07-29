
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No1873 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            char[][] field = new char[H][W];

            int x = 0;
            int y = 0;
            int dx = 0;
            int dy = 0;
            for (int i = 0; i < H; i++) {
                char[] chars = br.readLine().trim().toCharArray();
                for (int j = 0; j < W; j++) {
                    field[i][j] = chars[j];
                    if (field[i][j] == '^') {
                        x = i;
                        y = j;
                        dx = -1;
                        dy = 0;
                    } else if (field[i][j] == 'v') {
                        x = i;
                        y = j;
                        dx = 1;
                        dy = 0;
                    } else if (field[i][j] == '<') {
                        x = i;
                        y = j;
                        dx = 0;
                        dy = -1;
                    } else if (field[i][j] == '>') {
                        x = i;
                        y = j;
                        dx = 0;
                        dy = 1;
                    }
                }
            }

            int N = Integer.parseInt(br.readLine().trim());
            char[] action = br.readLine().toCharArray();

            field[x][y] = '.';
            for (int i = 0; i < N; i++) {
                switch (action[i]) {
                    case 'U' : {
                        dx = -1;
                        dy = 0;
                        int tempX = x + dx;
                        int tempY = y + dy;
                        if (tempX < 0 || tempX >= H || tempY < 0 || tempY >= W) break;
                        if (field[tempX][tempY] == '.') {
                            x = tempX;
                            y = tempY;
                        }
                    } break;

                    case 'D' : {
                        dx = 1;
                        dy = 0;
                        int tempX = x + dx;
                        int tempY = y + dy;
                        if (tempX < 0 || tempX >= H || tempY < 0 || tempY >= W) break;
                        if (field[tempX][tempY] == '.') {
                            x = tempX;
                            y = tempY;
                        }
                    } break;

                    case 'L' : {
                        dx = 0;
                        dy = -1;
                        int tempX = x + dx;
                        int tempY = y + dy;
                        if (tempX < 0 || tempX >= H || tempY < 0 || tempY >= W) break;
                        if (field[tempX][tempY] == '.') {
                            x = tempX;
                            y = tempY;
                        }
                    } break;

                    case 'R' : {
                        dx = 0;
                        dy = 1;
                        int tempX = x + dx;
                        int tempY = y + dy;
                        if (tempX < 0 || tempX >= H || tempY < 0 || tempY >= W) break;
                        if (field[tempX][tempY] == '.') {
                            x = tempX;
                            y = tempY;
                        }
                    } break;

                    case 'S' : {
                        int tempX = x + dx;
                        int tempY = y + dy;
                        while (true) {
                            if (tempX < 0 || tempX >= H || tempY < 0 || tempY >= W || field[tempX][tempY] == '#') break;
                            if (field[tempX][tempY] == '*') {
                                field[tempX][tempY] = '.';
                                break;
                            } else {
                                tempX += dx;
                                tempY += dy;
                            }
                        }
                    } break;
                }
            }

            if (dx == -1) {
                field[x][y] = '^';
            } else if (dx == 1) {
                field[x][y] = 'v';
            } else if (dy == -1) {
                field[x][y] = '<';
            } else if (dy == 1) {
                field[x][y] = '>';
            }

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    result.append(field[i][j]);
                }
                result.append("\n");
            }

            sb.append("#").append(t).append(" ").append(result);
        }

        System.out.print(sb.toString().trim());
    }

}