import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No1225 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = 10;
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine().trim());

            Queue<Integer> queue = new LinkedList<>();
            while (st.hasMoreTokens()) {
                queue.offer(Integer.parseInt(st.nextToken()));
            }

            boolean isCreated = false;
            do {
                for (int i = 1; i <= 5; i++) {
                    if (!queue.isEmpty()) {
                        int head = Math.max(queue.poll() - i, 0);
                        queue.offer(head);
                        if (head == 0) {
                            isCreated = true;
                            break;
                        }
                    }
                }
            } while (!isCreated);

            StringBuilder result = new StringBuilder();
            while (!queue.isEmpty()) {
                result.append(queue.poll()).append(" ");
            }

            sb.append("#").append(N).append(" ").append(result.toString().trim()).append("\n");
        }
        System.out.print(sb.toString().trim());
    }

}
