package test.arrays;

import java.util.Arrays;
import java.util.Scanner;

public class FrequencyArray {

    static int[] findFreq(int a[]){
        int freq[] = new int[a.length];

        int visited = -1;
        for(int i=0; i<a.length; i++) {
            int count = 1;
            for(int j=i+1; j<a.length; j++){
                if(a[i] == a[j]){
                    count++;
                    freq[j] = visited;
                }

            }
            if(freq[i] != visited)
            freq[i] = count;
        }

        return freq;
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int a[] = new int[str.length()];
        for(int i=0; i<str.length(); i++){
            a[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }

        int x[] = findFreq(a);

        for(int i=0; i<x.length; i++){
            if(x[i] != -1){
                System.out.println(x[i]);
            }
        }

    //    System.out.println(Arrays.toString(a));
//        for(int i = 0;i<a.length; i++){
//            System.out.println(a[i]);
//        }
    }
}
