package test.string_builder;

public class LineSeparator {

    public static void main(String args[]) {
        String longString = "This is a long string that spans" + System.lineSeparator()
                + "multiple lines for better readability." + System.lineSeparator()
                + "Each line is separated by a newline character.";
        System.out.println(longString);
        StringBuilder str = new StringBuilder("This is a long string that spans" + System.lineSeparator()
                + "multiple lines for better readability." + System.lineSeparator()
                + "Each line is separated by a newline character.");
        System.out.println(str);
    }
}
