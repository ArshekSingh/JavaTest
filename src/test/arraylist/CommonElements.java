package test.arraylist;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommonElements {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<String> str1 = new ArrayList();
        for(int i=0;i<=n+1;i++){
            str1.add(sc.nextLine());
        }

        List<String> str2 = new ArrayList<>();

        int m = sc.nextInt();
        for(int i=0;i<=m+1;i++){
            str2.add(sc.nextLine());
        }

        for(int i=0;i<=n+1;i++){
            for(int j=0;j<=m+1;j++){
                if(str1.get(i).equals(str2.get(j))){
                    System.out.print(str1.get(i)+" ");
                }
            }
        }




        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }

}
