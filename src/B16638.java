import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class B16638 {

    static String exp;
    static int N, R, MAX;
    static int[] num;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine().trim());
        exp = br.readLine().trim();

        num = new int[N / 2];
        int index = 0;
        for (int i = 0; i < N; i++) {
            char e = exp.charAt(i);
            if (e == '+' || e == '*' || e == '-') {
                num[index++] = i;
            }
        }

        index = index % 2 == 0 ? index / 2 : (index + 1) / 2;

        MAX = calc(exp);
        for (int i = 1; i <= index; i++) {
            R = i;
            result = new int[R];
            backtrack(0, 0);
        }

        bw.write(String.valueOf(MAX));
        bw.flush();
    }

    // nCr 조합 생성
    private static void backtrack(int idx, int cnt) {
        if (cnt == R) {

            for (int i = 0; i < result.length - 1; i++) {
                if (result[i] + 2 == result[i + 1]) return;
            }

            List<Character> lst = new ArrayList<>();
            for (char ch : exp.toCharArray()) lst.add(ch);
            createExpression(lst);

            StringBuilder sb = new StringBuilder();
            for (char ch : lst) sb.append(ch);
            String str = sb.toString().trim();
            MAX = Math.max(MAX, calc(str));

            return;
        }

        for (int i = idx; i < num.length; i++) {
            result[cnt] = num[i];
            backtrack(i + 1, cnt + 1);
        }
    }

    // 괄호가 있는 수식 생성
    private static void createExpression(List<Character> lst) {
        int add = 0;
        int index;
        for (int num : result) {
            index = Math.max(num - 1, 0);
            if (result.length != 1) index += add;
            lst.add(index, '(');
            index = num + 3;
            if (result.length != 1) index += add;
            lst.add(index, ')');
            add += 2;
        }
    }

    private static int calc(String str) {
        StringBuilder temp = new StringBuilder();
        Stack<Character> op = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '+') {
                while (!op.isEmpty()) {
                    int c = op.peek();
                    if (c == '(') break;
                    temp.append(op.pop());
                }
                op.push(ch);
            } else if (ch == '-') {
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
            if (ch == '*' || ch == '+' || ch == '-') {
                int n1 = num.pop();
                int n2 = num.pop();
                if (ch == '*') {
                    num.push(n1 * n2);
                } else if (ch == '-') {
                    num.push(n2 - n1);
                } else {
                    num.push(n1 + n2);
                }
            } else {
                num.push(ch - '0');
            }
        }

        return num.pop();
    }

}
