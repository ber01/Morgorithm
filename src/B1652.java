
import java.io.*;

public class B1652 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine().trim());
        char[][] room = new char[N][N];

        for (int i = 0; i < N; i++) {
            char[] cArr = br.readLine().toCharArray();
            System.arraycopy(cArr, 0, room[i], 0, N);
        }

        // 연속적인 . 두 개가 발견되면 그 줄에 벽이 있는지 검사한다.
        int row = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (room[i][j] == '.' && room[i][j + 1] == '.') {
                    row++;
                    // 벽이 있는지 없는지 검사한다.
                    while (room[i][j] != 'X') {
                        j++;
                        if (j == N) break;
                    }
                }
            }
        }

        sb.append(row).append(" ");

        int col = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (room[j][i] == '.' && room[j + 1][i] == '.') {
                    col++;
                    while (room[j][i] != 'X') {
                        j++;
                        if (j == N) break;
                    }
                }
            }
        }

        sb.append(col);

        bw.write(sb.toString());
        bw.flush();
    }

}
