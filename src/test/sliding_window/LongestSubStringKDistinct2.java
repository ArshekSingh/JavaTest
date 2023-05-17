package test.sliding_window;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStringKDistinct2 {

    public static void main(String args[]) {
        String a = "aabbccccddeeeeeaa";
        HashMap<Integer, String> map = new HashMap<>();
        Integer k = 2;
        String temp = "";

        for (int i = 0; i < a.length(); i++) {
            if (temp.isEmpty()) {
                temp = temp.concat(String.valueOf(a.charAt(i)));
                map.put(i, String.valueOf(a.charAt(i)));
            } else {
                if (temp.toCharArray()[temp.length() - 1] == a.charAt(i)) {
                    Integer index = getKeyFromValue(map, temp);
                    temp = temp.concat(String.valueOf(a.charAt(i)));
                    map.put(index, temp);
                } else {
                    temp = "";
                    temp = temp.concat(String.valueOf(a.charAt(i)));
                    map.put(map.size(), String.valueOf(a.charAt(i)));
                }
            }
        }

        System.out.println(map);

    }

    public static Integer getKeyFromValue(HashMap<Integer, String> testMap, String value) {
        Integer key = 0;
        for (Map.Entry<Integer, String> entry : testMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                key = entry.getKey();
            }
        }
        return key;
    }

//    public static void main(String args[]) {
//        HashMap<Integer, String> map = new HashMap<>();
//        map.put(1, "Arshek");
//        map.put(2, "Arun");
//        map.put(3, "Shubham");
//
//        int size = map.size();
//        System.out.println(size);
//        for(Map.Entry<Integer, String> m : map.entrySet()) {
//            System.out.println(m.getValue());
//        }
//    }
}
