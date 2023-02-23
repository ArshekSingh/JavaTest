package test;

public class LeetCodeReverseDigits {
        public static int reverse(int x) {
            if(x>= Integer.MIN_VALUE && x<= Integer.MAX_VALUE)
            {
                int reverse = 0;
                while(x!=0){
                    int reminder = x%10;
                    reverse = reverse*10+reminder;
                    x=x/10;
                }
                return reverse;
            }
        else{
                return 0;
            }
        }

        public static void main(String a[]){
            int x=1534236469;
            System.out.println(reverse(x));


        }
    }
