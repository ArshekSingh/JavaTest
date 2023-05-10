package test.string_builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveSAmeElements {

    public static void main(String args[]) {
        String str1[] = new String[]{"1", "2", "3", "4", "261","5", "6"};
        String str2[] = new String[]{"1", "3", "6"};

//        String str[] = new String[str1.length];
        List<String> str = new ArrayList<>();
        boolean found;
        for(int i=0; i< str1.length; i++) {
            found = false;
            for(int j=0; j< str2.length; j++) {
                if(str1[i] == str2[j]) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                str.add(str1[i]);
            }
        }

        System.out.println(str);
    }
}
