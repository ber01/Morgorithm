package Expert;

import java.io.*;
import java.util.Stack;

// 숫자는 놓고, 연산자는 스택에 넣고 싶은데 우선순위 높은 애만 깔아 뭉갠다. 근데 연산자를 push 하려는데 empty 가 아니면 pop 하고 푸쉬한다.
// 후위 표기식 만들고
// 숫자 -> 푸쉬, 연산자 -> 2 가지를 팝, 결과를 푸쉬 (반복)
public class No1223 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            int len = Integer.parseInt(br.readLine().trim());
            String str = br.readLine().trim();

            StringBuilder temp = new StringBuilder();
            Stack<Character> op = new Stack<>();
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch == '+') {
                    if (!op.isEmpty()) {
                        temp.append(op.pop());
                    }
                    op.push(ch);
                } else if (ch == '*') {
                    if (!op.isEmpty()) {
                        if (op.peek() != '+') {
                            temp.append(op.pop());
                        }
                    }
                    op.push(ch);
                } else {
                    temp.append(ch);
                }
            }

            while (!op.isEmpty()) {
                temp.append(op.pop());
            }

            str = temp.toString().trim();
            Stack<Integer> num = new Stack<>();
            for (int i = 0; i < len; i++) {
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