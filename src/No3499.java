
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No3499 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            String[] arr = new String[N];

            for (int i = 0; st.hasMoreTokens(); i++) {
                arr[i] = st.nextToken();
            }

            StringBuilder result = new StringBuilder();
            if (N % 2 == 0) {
                int index = N / 2;
                for (int i = 0; i < index; i++) {
                    result.append(arr[i]).append(" ").append(arr[i + index]).append(" ");
                }
            } else {
                int index = (N + 1) / 2;
                int i;
                for (i = 0; i < index - 1; i++) {
                    result.append(arr[i]).append(" ").append(arr[i + index]).append(" ");
                }
                result.append(arr[i]);
            }

            sb.append("#").append(t).append(" ").append(result.toString().trim()).append("\n");
        }
        System.out.print(sb.toString().trim());
    }
}