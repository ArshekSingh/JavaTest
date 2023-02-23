package test.recurssion;

public class Factorial {

    public static int fact(int n) {
        if(n == 1) {
            return 1;
        }
        return n * fact(n - 1);

    }

    public static void main(String args[]) {
        int n = 5;
        int fact = fact(5);
        System.out.println(fact);

    }
}
