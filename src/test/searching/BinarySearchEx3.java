package test.searching;

public class BinarySearchEx3 {

    public static int findNum(int[] arr, int l, int r, int f) throws Exception{
        try {
            if(l<r) {
                int i = r/2;
                if(arr[i]==f) {
                    return i;
                }
                if(arr[i]<f) {
                   return findNum(arr, i+1, r, f);
                }
                else {
                    return findNum(arr, 0, i, f);
                }
            }

        }
        catch (Exception e) {
            throw new Exception("exception occurred due to " + e.getMessage());
        }
        return -1;
    }

    public static void main (String args[]) throws Exception{
        int[] arr = new int[]{2,3,4,5,6,7,8,9,10};
        int n = arr.length-1;
        int a = findNum(arr, 0, n, 9);
        System.out.print(a);
    }
}
