package test.streams;

import java.util.Arrays;

public class StreamForEachEx {

    public static void main(String args[]) {
        Employee[] empList = {
                new Employee(1, "aman", 2000.00),
                new Employee(2, "ajit", 900.00),
                new Employee(3,"vikram", 3000.00)
        };

        Arrays.stream(empList);
    }
}
