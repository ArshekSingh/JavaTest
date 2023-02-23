package test;

import java.util.Scanner;

public class FindSumOfElementsArray {

    static int printSum(int a[], int x){
        int sum =0;
        for(int i=a.length-1;i>=x;i--){
            sum = sum+a[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for(int i=0;i<n;i++){
            a[i]= sc.nextInt();
        }
        int x = sc.nextInt();
        int b = printSum(a,x);
        System.out.println(b);

        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }

}
