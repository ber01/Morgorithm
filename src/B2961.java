import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class B2961 {

    private static class Food {
        int sour;
        int bitter;

        public Food(int sour, int bitter) {
            this.sour = sour;
            this.bitter = bitter;
        }

        @Override
        public String toString() {
            return "(" + sour + ", " + bitter + ")";
        }
    }

    private static int N;
    private static int R;
    private static int diff = Integer.MAX_VALUE;
    private static int[] input;
    private static int[] numbers;
    private static Food[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine().trim());
        arr = new Food[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            arr[i] = new Food(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        input = new int[N];
        IntStream.range(0, N).forEach(i -> input[i] = i);
        for (int i = 1; i <= N; i++) {
            R = i;
            numbers = new int[R];
            combination(0, 0);
        }

        bw.write(String.valueOf(diff));
        bw.flush();
    }

    public static void combination(int cnt, int start) {
        if (cnt == R) {
            int sour = 1;
            int bitter = 0;
            for (int number : numbers) {
                sour *= arr[number].sour;
                bitter += arr[number].bitter;
            }
            // System.out.println(R + " : " + sour + " " + bitter);
            diff = Math.min(diff, Math.abs(sour - bitter));
            return;
        }

        for (int i = start; i < N; i++) {
            numbers[cnt] = input[i];
            combination(cnt + 1, i + 1);
        }
    }

}
