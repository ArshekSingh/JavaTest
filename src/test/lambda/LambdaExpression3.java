package test.lambda;

public class LambdaExpression3 {

    public static void main(String a[]) {

        FunctionalInterface2 obj = name -> {
            return "Hello " + name;
        };

            System.out.println(obj.name("Arsh"));
    }
}





