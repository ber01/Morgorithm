package Expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class No1933 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (N % i == 0) sb.append(i).append(" ");
        }

        System.out.print(sb.toString().trim());
    }

}
