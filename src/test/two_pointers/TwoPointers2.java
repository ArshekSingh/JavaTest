package test.two_pointers;

public class TwoPointers2 {
    public static void main(String args[]) {
        int arr[] = new  int[]{7,9,15,19,21,32,42};
        int k = 30;
        System.out.println(findPair(arr, k));
    }

    public static String findPair(int[] arr, int k) {
        int i = 0;
        int j = arr.length-1;
        while(i<j) {
            int a = arr[i];
            int b = arr[j];
                if(a+b==k) {
                    return a + " " + b;
                }
                else if(a+b>k) {
                    j--;
                }
                else
                    i++;

        }
        return "-1";
    }
}
