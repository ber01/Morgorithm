
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No1208 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= 10; t++) {

            int dumpCnt = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int[] arr = new int[100];
            for (int i = 0; st.hasMoreTokens(); i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            while (dumpCnt-- > 0) {
                int[] index = index(arr);
                int minIndex = index[0];
                int maxIndex = index[1];

                int result = arr[index[1]] - arr[index[0]];
                if (result == 0 || result == 1) break;

                arr[minIndex]++;
                arr[maxIndex]--;
            }

            int[] index = index(arr);
            int result = arr[index[1]] - arr[index[0]];
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }

        System.out.print(sb.toString().trim());
    }

    private static int[] index(int[] arr) {

        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (min >= arr[i]) {
                min = arr[i];
            }
            if (arr[i] >= max) {
                max = arr[i];
            }
        }

        int minIndex = 0;
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (min == arr[i]) {
                minIndex = i;
            }
            if (max == arr[i]) {
                maxIndex = i;
            }
        }

        return new int[]{minIndex, maxIndex};
    }

}