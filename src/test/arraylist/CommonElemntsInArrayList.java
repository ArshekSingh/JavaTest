package test.arraylist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommonElemntsInArrayList {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<String> str1 = new ArrayList<>();
        while (n!=0){
            str1.add(sc.next());
            n--;
        }
//        str1.forEach(System.out::println);
        int m = sc.nextInt();
        List<String> str2 = new ArrayList<>();
        while (m!=0){
            str2.add(sc.next());
            m--;
        }

        for(String str : str1){
            for(String str3 : str2) {
                if (str.equals(str3)){
                    System.out.print(str + " ");
                }
            }
        }



        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }

}