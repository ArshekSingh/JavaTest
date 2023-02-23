package test;

import java.util.Scanner;

public class MultipleInputs {

//    public static void main(String[] args) {
//        Scanner scn = new Scanner(System.in);
//        int num = scn.nextInt();
//        for(int i = 1; i <= num; i++) {
//            int nums = scn.nextInt();
//            String ans = isPrime(nums);
//            System.out.println(ans);
//        }
//
//    }
//    public static String isPrime(int num) {
//
//        for(int i = 2; i <= num / 2; i ++) {
//            if(num % i == 0) return "not prime";
//        }
//        return "prime";
//    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count=0;

        for(int i =1;i<=n;i++){
            int input = sc.nextInt();
            if(input==1){
                System.out.println("not prime");
            }
            for(int j=1;j<=input/2;j++) {

                if (input % j == 0) {
                    count++;
                }

            }
            if(count>1){
                System.out.println("not prime");
            }
            else{
                System.out.println("prime");
            }

        }

        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }

}
