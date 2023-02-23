package test.sorting;

public class BubbleSort {

    public static void main(String args[]) {
        int arr[] = new int[]{3,1,2,7,5};

        for(int i=0; i< arr.length; i++) {
            for(int j=0; j< arr.length-1-i; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        printArray(arr);
    }

    private static void printArray(int[] arr) {
        for(int a : arr) {
            System.out.print(a + " ");
        }
    }
}
