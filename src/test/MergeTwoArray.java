package test;

import java.util.Scanner;

public class MergeTwoArray {
    static void mergeTwoArray(int[] a, int b[], int n){

        int c[]= new int[n];
        for(int i=0;i<n; i=i+2){
            c[i]= a[i];
        }
        for(int i=1;i<n;i=i+2){
            c[i]= b[i];
        }

        for(int i=0;i<n;i++){
            System.out.print(c[i]+" ");
        }
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        int[] a= new int[n];

        int[] b = new int[n];
        for(int i=0;i<n;i++){
            a[i]= sc.nextInt();
        }

        for(int i=0;i<n;i++){
            b[i]= sc.nextInt();
        }

        mergeTwoArray(a,b,n);



        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }

}
