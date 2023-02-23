package test.stack;

import java.util.Stack;

public class StackPushAtBottom {

    public static void pushAtBottom(int data, Stack<Integer> s) {

        if(s.empty()) {
            s.push(data);
            return;
        }
        int top = s.pop();
        pushAtBottom(data, s);
        s.push(top);
    }


    public static void main(String args[]) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        pushAtBottom(4, stack);
        while (!stack.empty()) {
            System.out.println(stack.peek());
            stack.pop();
        }
    }
}
