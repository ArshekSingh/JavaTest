package test.searching;

public class BinarySearch {

    public static int search(int i, int last, int k, int[] arr) {
        int mid = i + (last - i)/2;

        if(k == arr[mid])
            return mid;

        if(k > arr[mid]) {
            i = mid + 1;
           return search(i, last, k, arr);
        }
        if(k < arr[mid]) {
            last = mid - 1;
          return search(i, last, k, arr);
        }
        return 0;
    }

    public static void main(String args[]) {

        int arr[] = {2,4,6,8,10,12};
        int k = 10;
        int i = 0;
        int last = arr.length-1;
        int index = search(i, last, k, arr);
        System.out.println("index of element is " + index);
    }

}
