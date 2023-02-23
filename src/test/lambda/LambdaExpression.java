package test.lambda;

import test.lambda.FunctionalInterface;

//Without lambda expression;
public class LambdaExpression {
    public static void main(String[] a){
        FunctionalInterface obj = new FunctionalInterface() {
            @Override
            public void display() {
                System.out.println("Hello");
            }
        };

        obj.display();
    }

}

