package BOJ;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B1931_re {

    static class M implements Comparable<M> {
        int start, end;

        public M(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(M o) {
            if (this.end == o.end) {
                return Integer.compare(this.start, o.start);
            }
            return Integer.compare(this.end, o.end);

        }

        @Override
        public String toString() {
            return "(" + start + ", " + end + ")";
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<M> lst = new ArrayList<>();
        int N = Integer.parseInt(br.readLine().trim());
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            lst.add(new M(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(lst);

        int cnt = 1;
        M cur = lst.get(0);
        for (int i = 1; i < lst.size(); i++) {
            M next = lst.get(i);
            if (next.start >= cur.end) {
                cnt++;
                cur = next;
            }
        }

        bw.write(cnt + "");
        bw.flush();
    }

}
