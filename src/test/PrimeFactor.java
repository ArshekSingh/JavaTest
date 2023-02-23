package test;

import java.util.Scanner;

public class PrimeFactor {

    static void primeOrNot(int fact){
        for(int i =2;i<=fact/2;i++){
            if(fact%i!=0){
                System.out.println(i);
            }
        }

    }
    static void factorsOfNum(int fact, int num){
        if(num%fact==0){
            //System.out.println(fact);
            primeOrNot(fact);
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i=1;i<=n;i++){
            factorsOfNum(i, n);

        }


        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}
