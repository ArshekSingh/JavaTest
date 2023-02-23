package test;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ArmstrongNumber {

    static boolean checkArmstrong(int n){
        int a=n;
        int reverse=0;
        int cube=0;
        while(n!=0){
            int reminder = n%10;
            cube = cube+reminder*reminder*reminder;
            reverse = reverse *10 + reminder;

            n=n/10;
        }
        if(a==cube)
            return true;
        else
            return false;
    }


    public static void main(String[] args) {


        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(checkArmstrong(n));


        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}
