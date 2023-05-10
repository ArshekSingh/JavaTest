package test.sorting;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MergeSort2 {
    public static void mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            // Divide the array into two halves and recursively call mergeSort
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            // Merge the two sorted halves
            merge(arr, start, mid, end);
        }
    }

    public static void merge(int[] arr, int start, int mid, int end) {
        int leftSize = mid - start + 1;
        int rightSize = end - mid;
        int[] leftArr = new int[leftSize];
        int[] rightArr = new int[rightSize];
        // Copy the left half of the array into a temporary array
        for (int i = 0; i < leftSize; i++) {
            leftArr[i] = arr[start + i];
        }
        // Copy the right half of the array into a temporary array
        for (int i = 0; i < rightSize; i++) {
            rightArr[i] = arr[mid + i + 1];
        }
        int i = 0, j = 0, k = start;
        // Merge the two arrays by comparing the elements
        while (i < leftSize && j < rightSize) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        // Copy the remaining elements from the left array, if any
        while (i < leftSize) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        // Copy the remaining elements from the right array, if any
        while (j < rightSize) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static void main(String args[]) {
        int[] arr = new int[] {4,5,2,1,7,8,3,6};
        mergeSort(arr, 0, 7);
//       for(int i : arr) {
//           System.out.println(i);
//       }
        List<Integer> collect = Arrays.stream(arr).boxed().toList();
       System.out.println(collect);
    }
}
