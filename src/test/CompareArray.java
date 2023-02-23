package test;



    import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

        public class CompareArray {

        static boolean compareArray(int[] a, int n, int[] b, int m){
//            if(n==m){
//                int count=0;
//                for(int i=0;i<n;i++){
//                    if(a[i]==b[i]){
//                        count++;
//                    }
//                    else{
//                        return false;
//                    }
//                }
//                if(count==n){
//                    return true;
//                }
//            }
//            return false;

            if(Arrays.equals(a, b)){
                return true;
            }
            return false;

        }

        public static void main(String[] args) {

            Scanner sc= new Scanner(System.in);
            int n = sc.nextInt();
            int a[]= new int[n];
            for(int i=0;i<n;i++){
                a[i]= sc.nextInt();
            }
            int m = sc.nextInt();
            int b[]= new int[m];
            for(int i=0;i<m;i++){
                b[i]= sc.nextInt();
            }

            System.out.println(compareArray(a,n,b,m));
            /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        }
    }
