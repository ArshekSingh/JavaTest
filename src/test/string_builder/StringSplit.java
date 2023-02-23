package test.string_builder;

import java.util.Arrays;
import java.util.List;

public class StringSplit {

    public static void main(String args[]) {
        String str = "arsheks@gmail.com, arshekkumar@gmail.com, arsheksingh1597@gmail.com";
        String[] split = str.split(",");
        List<String> newStr = Arrays.asList(str.split(","));
    }
}
