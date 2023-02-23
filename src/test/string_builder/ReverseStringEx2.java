package test.string_builder;

public class ReverseStringEx2 {

    public static void main(String args[]) {
        StringBuilder str = new StringBuilder("arshek");

        for(int i=0; i<str.length()/2; i++) {
            int firstIndex = i;
            int lastIndex = str.length()-1-i;
            char firstChar = str.charAt(firstIndex);
            char lastChar = str.charAt(lastIndex);
            str.setCharAt(firstIndex, lastChar);
            str.setCharAt(lastIndex, firstChar);
        }

        System.out.print(str);
    }
}
