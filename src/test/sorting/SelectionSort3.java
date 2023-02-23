package test.sorting;

import java.util.Arrays;

public class SelectionSort3 {

    public static void main(String args[]){
        int a[] = {5,4,3,8,1,11,0};

        for(int i=0;i<a.length;i++){
            int minIndex = i;
            for(int j=i+1;j<a.length;j++){
                if(a[j]<a[minIndex]){
                    minIndex = j;
                }
            }
            int smallNumber = a[minIndex];
            a[minIndex] = a[i];
            a[i] = smallNumber;
        }

        System.out.println(Arrays.toString(a));
    }
}
