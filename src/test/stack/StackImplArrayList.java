package test.stack;

import java.util.ArrayList;
import java.util.List;

public class StackImplArrayList {

    static class Stack {
        static List<Integer> integers = new ArrayList<>();

        public boolean isEmpty() {
           return integers.size() == 0;
        }
        public void push(int a) {
          integers.add(a);
        }

        public int pop() {
            if(isEmpty()) {
                return -1;
            }
            int top = integers.get(integers.size()-1);
            integers.remove(integers.size()-1);
            return top;
        }

        public int peek() {
            if (isEmpty())
                return -1;
            int top = integers.get(integers.size()-1);
            return top;
        }
    }

    public static void main(String args[]) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        while (!stack.isEmpty()) {
            System.out.println(stack.peek());
            stack.pop();
        }

    }
}
