package test.sorting;

import java.util.Scanner;

public class InsertionSort {

    static int[] sortArray(int a[]){

        for(int i=1;i <a.length;i++){
            int temp = a[i];
            int j=i-1;
            for(;j>=0;j--){
                if(a[j]>temp){
                    a[j+1] = a[j];
                }
                else{
                    break;
                }
            }
            a[j+1] = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        for(int i=0;i<n;i++){
            a[i] = sc.nextInt();
        }

        int x[] = sortArray(a);
        for(int i=0;i<n;i++){
            System.out.print(a[i]+" ");
        }


        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}
