import java.io.*;
import java.util.*;

public class B1931 {

    private static class Room implements Comparable<Room> {

        int start;
        int end;

        public Room(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "(" + start + ", " + end + ")";
        }

        @Override
        public int compareTo(Room o) {
            if (this.end == o.end) {
                return this.start - o.start;
            }
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine().trim());
        List<Room> roomList = new ArrayList<>();
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            roomList.add(new Room(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(roomList);
        // System.out.println(roomList);

        Room cur = roomList.get(0);
        int cnt = 1;
        // System.out.print(cur);
        for (int i = 1; i < roomList.size(); i++) {
            Room next = roomList.get(i);
            if (next.start >= cur.end) {
                cnt++;
                cur = next;
                // System.out.print(", " + cur);
            }
        }

        // System.out.println();
        bw.write(String.valueOf(cnt));
        bw.flush();
    }

}
