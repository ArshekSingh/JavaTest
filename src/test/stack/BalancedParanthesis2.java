package test.stack;

import java.util.Stack;

public class BalancedParanthesis2 {

    public int solve(String A) {
        Stack stack = new Stack();
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == '(') {
                stack.push(A.charAt(i));
                continue;
            }

            if (stack.isEmpty())
                return 0;

            char ch;
//            switch (A.charAt(i)) {
//                case ')':
//                    ch = (char) stack.pop();
//                    if (ch != '(')
//                        return 0;
//                    break;
//            }
            if(A.charAt(i) == ')'){
                ch = (char) stack.pop();
            }

        }

        if (stack.isEmpty())
            return 1;
        else
            return 0;
    }

    public static void main(String args[]){
        String A = "(()((((()(";
        BalancedParanthesis2 obj = new BalancedParanthesis2();
        int ans = obj.solve(A);
        System.out.println(ans);
    }
}
