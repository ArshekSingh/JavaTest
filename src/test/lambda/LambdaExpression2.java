package test.lambda;

import test.lambda.FunctionalInterface;


//with lambda expression;
public class LambdaExpression2 {
    public static void main(String a[]) {
        FunctionalInterface obj = ()->{
            System.out.println("Hello Arshek");
        };

        obj.display();
    }
}
