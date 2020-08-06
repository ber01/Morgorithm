import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Test {

    private static int N, R;
    private static int[] input;
    private static int[] numbers;
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        N = new Scanner(System.in).nextInt();
        input = new int[N];
        IntStream.range(0, N).forEach(i -> input[i] = i);
        System.out.println(Arrays.toString(input));

        for (int i = 1; i <= N; i++) {
            R = i;
            numbers = new int[R];
            combination(0, 0);
        }

        System.out.println(sb.toString().trim());
    }

    public static void combination(int cnt, int start) { // cnt : 현재까지 뽑은 조합수의 개수, start : 조합에 시작점으로 시도할 인덱스
        if (cnt == R) {
            for (int i = 0; i < numbers.length; i++) {
                System.out.print(numbers[i] + " ");
            }
            System.out.println();
            return;
        }

        // 현 자리의 시작 위치 수부터 끝 위치 수까지 시도
        for (int i = start; i < N; i++) {
            numbers[cnt] = input[i];
            combination(cnt + 1, i + 1);
        }
    }
}
