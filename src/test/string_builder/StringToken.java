package test.string_builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringToken {
    public static void main(String args[]) {

        String file = "ARSHEK KUMAR SINGH";
        String name = "ARSHEK SINGH KUMAR";

       boolean value =  matchName(name, file);
       System.out.print(value);
    }

    public static boolean matchName(String name, String file) {


        List<String> fileNameTokens = Arrays.asList(file.toLowerCase().split(" "));
        int fileNameTokenSize = fileNameTokens.size();
        List<String> customerNameTokens = new ArrayList<>(
                Arrays.asList(name.toLowerCase().split(" ")));
        int customerNameTokenSize = customerNameTokens.size();
        if (fileNameTokenSize > customerNameTokenSize || fileNameTokenSize < customerNameTokenSize)
            return Boolean.FALSE;
        else {
            customerNameTokens.removeAll(fileNameTokens);
            int sizeAfterRemovingTokens = customerNameTokens.size();

            if (sizeAfterRemovingTokens == 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }

    }
}
