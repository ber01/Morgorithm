package Expert;

import java.io.*;
import java.util.Stack;

public class No1224 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            int len = Integer.parseInt(br.readLine().trim());
            String str = br.readLine().trim();

            StringBuilder temp = new StringBuilder();
            Stack<Character> op = new Stack<>();
            for (int i = 0; i < len; i++) {
                char ch = str.charAt(i);
                if (ch == '+') {
                    while (!op.isEmpty()) {
                        int c = op.peek();
                        if (c == '(') break;
                        temp.append(op.pop());
                    }
                    op.push(ch);
                } else if (ch == '*') {
                    op.push(ch);
                } else if (ch == '(') {
                    op.push(ch);
                } else if (ch == ')') {
                    while (!op.isEmpty()) {
                        char c = op.pop();
                        if (c != '(') {
                            temp.append(c);
                        }
                        if (c == '(') break;
                    }
                } else {
                    temp.append(ch);
                }
            }

            while (!op.isEmpty()) {
                temp.append(op.pop());
            }

            str = temp.toString().trim();
            Stack<Integer> num = new Stack<>();
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch == '*' || ch == '+') {
                    int n1 = num.pop();
                    int n2 = num.pop();
                    if (ch == '*') {
                        num.push(n1 * n2);
                    } else {
                        num.push(n1 + n2);
                    }
                } else {
                    num.push(ch - '0');
                }
            }

            sb.append("#").append(t).append(" ").append(num.pop()).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
    }
}