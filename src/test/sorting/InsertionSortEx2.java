package test.sorting;
//self done
public class InsertionSortEx2 {
    public void insertionSort(int arr[]) {
        for(int i=1; i< arr.length; i++) {
            int key = arr[i];
            int j = i-1;
            while(j>=0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    public void printArray(int arr[]) {
        for(int a : arr) {
            System.out.println(a);
        }
    }

    public static void main(String args[]) {
        int arr[] = {12, 11, 13, 5, 6};
            InsertionSortEx2 sortEx2 = new InsertionSortEx2();
            sortEx2.insertionSort(arr);
            sortEx2.printArray(arr);
    }
}
