package test;

import java.util.Scanner;

public class CountNumberOfElemnt {
    static void printGreater(int a[]){
        int count=0;
        for(int i=0;i<a.length-1;i++){

                if(a[i+1]>a[i]){
                    count++;
                }

        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for(int i=0;i<n;i++){
            a[i]= sc.nextInt();
        }

        printGreater(a);

        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}
