package test.arrays;

public class FindMissingNumber {
    public static void main(String args[]) {
        int[] arr = new int[]{5, 3, 2, 1, 3, 8, 7, 0, 9};
        for (int i = 0; i <= 10; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length; j++) {
                if (i == arr[j]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println(i);
            }
        }
    }
}
