package test;

import java.util.Scanner;

public class PrintLeftRightRangeArray {

    static int[] updateQuery(int a[], int left, int right, int x){
        for(int i=a[left];i<=a[right];++i){
            a[i]=x;
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for(int i=0;i<n;i++){
            a[i]=sc.nextInt();
        }
        int left = sc.nextInt();
        int right = sc.nextInt();
        int x = sc.nextInt();
        a = updateQuery(a, left, right, x);
        for(int i=0;i<n;i++){
            System.out.print(a[i]+" ");
        }

        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

    }
}
