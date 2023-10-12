package test.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String args[]) {
        int arr[] = new int[] {7,2,3,4,5};
        int target = 9;
        int[] ints = twoSum(arr, target);
        for(int i: ints) {
            System.out.println(i);
        }

    }
    public static int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<arr.length;i++) {
            int complement = target - arr[i];
            if(map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }
            map.put(arr[i], i);
        }
        return new int[] {};
    }
}
