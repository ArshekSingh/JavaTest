package test.string_builder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringNameMatcher {
    public static void main(String args[]) {
        Pattern p = Pattern.compile("ARSHEK KUMAR SINGH");//. represents single character
        Matcher m = p.matcher("ARSHEK SINGH");
        boolean b = m.matches();
        System.out.print(b);
    }
}
