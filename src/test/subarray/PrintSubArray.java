package test.subarray;

import java.util.Scanner;

public class PrintSubArray {

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for(int i=0;i<n;i++){
            a[i] = sc.nextInt();
        }
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                int sum =0;
                for(int k=i;k<=j;k++) {
                    System.out.print(a[k] + " ");
                    sum = sum +a[k];
                }
                if(sum<0){
                    count++;
                }
                System.out.println();
            }
        }
        System.out.print(count);
    }
}
