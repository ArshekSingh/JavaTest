package test.hashmap;

import java.util.*;

public class FrequencyOfNumber {

    public static void main(String[] args) {
        int[] arr = {1,2,3,1,2,4,3,3};
        List<Integer> numbers = new ArrayList<>();
        for (int j : arr) {
            numbers.add(j);
        }
        Set<Integer> set = new HashSet<>(numbers);
        HashMap<Integer, Integer> map = new HashMap<>();
        for(Integer num : set) {
            int count = 0;
            for(Integer arrNum : numbers) {
                if(num == arrNum) {
                    count++;
                }
            }
            map.put(num, count);
        }
        for(Map.Entry<Integer, Integer> keyValue : map.entrySet()) {
            System.out.println("number " + keyValue.getKey() + " " + "value " + keyValue.getValue());
        }
    }
}
