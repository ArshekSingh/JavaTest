package test.arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListEx {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> b  = new ArrayList();
        try {
            while (sc.hasNextInt()) {
                int temp = sc.nextInt();
                if (temp == 0) continue;

                else if (temp % 2 == 0 && temp > 0) {
                    b.add(temp);
                } else if (temp % 2 != 0 && b.size() > 0 && temp > 0) {
                    int index = b.size() - 1;
                    b.remove(index);
                } else if (temp < 0) {
                    break;
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        for(int i= b.size() - 1; i>=0;i--){
            System.out.println(b.get(i));
        }




        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}
