package test.two_pointers;


//Array must be in sorted manner (asc);
public class TwoPointersExp1 {

    public static void main(String[] args){
        int[] arr = {20, 30, 40, 50, 60, 70, 75, 100};
        int sum = 110;
        int pair = findPair(arr, sum);
        System.out.println(pair);
    }

    private static int findPair(int[] arr, int sum) {
    int i = 0;
    int j = arr.length-1;

    while(i<j){

        if(arr[i] + arr[j] == sum){
            return 1;
        }

        else if(arr[i] + arr[j] > sum){
            j--;
        }
        else {
            i++;
        }
    }
        return 0;
    }
}
