package test;

import java.util.Scanner;

public class GreaterThanMe {

    static int[] printArray(int a[]){
        int b[] = new int[a.length];
        for(int i=0;i<a.length;i++){
            int count = 0;
            for(int j=0;j<a.length;j++){

                if(a[i]<a[j] && i!=j){
                    count++;
                }
            }
            b[i]=count;
        }
        return b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i=0;i<n;i++){
            a[i]= sc.nextInt();
        }

        a = printArray(a);
        for(int i=0;i<n;i++){
            System.out.print(a[i]+" ");
        }

        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}
