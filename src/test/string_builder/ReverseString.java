package test.string_builder;

public class ReverseString {
    public static void main(String args[]) {
        String str = "arshek";
        char[] chars = str.toCharArray();
        int length = str.length()-1;
        for(int i=0; i<str.length()/2; i++) {
            char temp = chars[i];
            chars[i] = chars[length];
            chars[length] = temp;
            length--;
        }

        for(int i=0; i< chars.length; i++) {
            System.out.print(chars[i] + " ");
        }
    }
}
