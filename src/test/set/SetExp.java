package test.set;

import java.util.HashSet;
import java.util.Set;

public class SetExp {
    public static void main(String args[]) {
        Set<String> str1 = new HashSet<>(Set.of("A", "B", "C", "C2", "G", "R"));
        Set<String> str2 = new HashSet<>(Set.of( "X"));
        boolean flag =false;
        for(String str : str1) {
            if(str2.contains(str)) {
                flag = true;
                break;
            }
        }
        if(!flag) {
            System.out.println("not present");
        }
        else {
            System.out.println("present");
        }
    }



}
