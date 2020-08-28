package Expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No2043 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int P = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.print(Math.abs(P - K) + 1);
    }

}
