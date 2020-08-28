package Expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class No1225_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = 10;
        int[] queue = new int[8];
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine().trim());

            for (int i = 0; st.hasMoreTokens(); i++) {
                queue[i] = Integer.parseInt(st.nextToken());
            }

            boolean isCreated = false;
            do {
                for (int i = 1; i <= 5; i++) {
                    int head = Math.max(queue[0] - i, 0);
                    IntStream.range(0, queue.length - 1).forEach(j -> queue[j] = queue[j + 1]);
                    queue[queue.length - 1] = head;
                    if (head == 0) {
                        isCreated = true;
                        break;
                    }
                }
            } while (!isCreated);

            StringBuilder result = new StringBuilder();
            for (int q : queue) {
                result.append(q).append(" ");
            }
            sb.append("#").append(N).append(" ").append(result.toString().trim()).append("\n");
        }
        System.out.print(sb.toString().trim());
    }

}
