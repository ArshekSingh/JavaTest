package test;

import java.util.Scanner;

public class PositiveFirstNegativeLast {

    static boolean checkArray(int a[]){
        int flag =0;
        for(int i=1;i<a.length;i++){
            for(int j=0;j<i;j++){
                if(a[i]>0 && a[j]>0){
                    flag = 1;
                }
                if(a[i]>0 && a[j]<0){
                    flag = 2;
                }
                if(a[i]<0 && a[j]>0){
                    flag =2;
                }
                if(a[i]<0 && a[j]<0){
                    flag =1;
                }
            }
        }
        if(flag == 1){
            return true;
        }
        return false;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for(int i=0;i<n;i++){
            a[i]= sc.nextInt();
        }

        System.out.println(checkArray(a));


        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}
