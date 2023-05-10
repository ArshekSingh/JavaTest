package test.arraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringConcat {

    public static void main(String args[]) {
        List<String> str1 = Arrays.asList("1", "2", "3");
        List<String> str2 = Arrays.asList("4", "5");

        List<String> str3 = new ArrayList<>();
        str3.addAll(str1);
        str3.addAll(str2);

        System.out.println(str3);

        String join = String.join(",", str1);
        System.out.println(join);
    }
}
