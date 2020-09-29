package BOJ;

import java.io.*;

public class B2292 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine().trim());
		bw.write(execute(N) + "");
		bw.flush();
	}

	public static int execute(int N) {
		
		if (N == 1) return 1;
		
		int index = 1;
		int start = 1;
		int end = 0;
		
		while (true) {
			
			end = start + (6 * index);
			
			if (end >= N) break;
			
			index++;
			start = end;
		}		

		return index + 1; 
	}

}
