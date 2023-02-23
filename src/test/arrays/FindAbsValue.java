package test.arrays;

import java.util.Scanner;

public class FindAbsValue {

    static void printSame(int a[], int b[]){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<b.length;j++){
                if(Math.abs(a[i])==b[j]){
                    System.out.print(a[i]+" ");
                    break;

                }
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for(int i=0;i<n;i++){
            a[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int b[] = new int[m];
        for(int i=0;i<m;i++){
            b[i] = sc.nextInt();
        }

        printSame(a,b);


        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}
