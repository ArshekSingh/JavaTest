package test;
import java.util.Scanner;
public class SwitchCaseExp {

    static String printGrade(int n){
        switch(n/10+1){
            case 11:
            case 10:
              return "excellent";

            case 9:
            case 8:
                return "good";

            case 7:
            case 6:
                    return "fair";


            case 5:
            case 4:
                return "meets expectations";


            case 3:
                return "below par";

            default:
                return "failed";

        }
    }

    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(printGrade(n));
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }


}
