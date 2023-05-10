package test.string_builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveElements2 {

    public static void main(String args[]) {
        String[] arr11 = {"1", "2", "3", "4", "261","5", "6"};
        String[] arr21 = {"1", "6"};
        String [] arr31  = {"3"};
        List<String> arr1 = Arrays.asList(arr11);
        List<String> arr2 = Arrays.asList(arr21);

        ArrayList<String> arr1Diff = new ArrayList<String>();

        for (String i : arr1) {
            boolean found = false;
            for (String j : arr2) {
                if (i.equals(j)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                arr1Diff.add(i);
            }
        }

        System.out.println("Elements in arr1 that are not in arr2:");
        for (String i : arr1Diff) {
            System.out.println(i);
        }


    }
}
