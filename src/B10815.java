import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B10815 {

    static int N, M;
    static int[] arr1, arr2;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine().trim());
        arr1 = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) arr1[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr1);

        M = Integer.parseInt(br.readLine().trim());
        arr2 = new int[M];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < M; i++) arr2[i] = Integer.parseInt(st.nextToken());

        for (int next : arr2) sb.append(binarySearch(next)).append(" ");

        bw.write(sb.toString().trim());
        bw.flush();
    }

    private static int binarySearch(int next) {
        int left = 0;
        int right = N - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr1[mid] == next) return 1;
            else if (arr1[mid] > next) right = mid - 1;
            else left = mid + 1;
        }

        return 0;
    }

}
