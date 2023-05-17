package test.sliding_window;

public class SlidingWindow2 {

    public int findMax(int arr[], int k, int l) {
        int max_sum = 0;
        for(int i=0; i<k ;i++) {
            max_sum += arr[i];
        }
        int window_sum = max_sum;
        for(int i=k; i<l; i++) {
            window_sum = window_sum - arr[i-k] + arr[i];
            max_sum = Math.max(max_sum, window_sum);
        }
        return max_sum;
    }


    public static void main(String args[]) {
        int arr[] = {5, 2, -1, 0, 3, 12};
        int k = 3;
        int l = arr.length;
        SlidingWindow2 window2 = new SlidingWindow2();
        System.out.println(window2.findMax(arr, k, l));
    }
}
