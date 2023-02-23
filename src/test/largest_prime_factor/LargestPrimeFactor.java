package test.largest_prime_factor;

public class LargestPrimeFactor {

    static int primeFactor(int number) {
        for (int i = number - 1; i > 1; i--) {
            if (number % i == 0) {
                boolean flag = true;
                for (int j = 2; j < i; j++) {
                    if (i % j == 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag)
                    return i;
            }
        }
        return number;
    }

    public static void main(String a[]) {
            int number = 37;
        System.out.println(primeFactor(number));
    }
}
