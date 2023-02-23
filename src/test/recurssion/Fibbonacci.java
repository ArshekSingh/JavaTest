package test.recurssion;

public class Fibbonacci {

    public static void fibbo(int num) {
        int n1=0;
        int n2=1;
        System.out.println(n1);
        System.out.println(n2);
        for(int i=0; i<num-2; i++) {
            int m = n2 + n1;
            System.out.println(m);
            n1=n2;
            n2=m;
        }
    }

         public static void main(String args[]) {
         int n = 10;
         fibbo(10);
         }

}
