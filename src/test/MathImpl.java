package test;

import java.time.LocalDate;

public class MathImpl extends Math{

    public int add(int a, int b){
        int c= a+b;
        return c;
    }



    @Override
    public int sub(int a, int b) {
        int c= a-b;
        return c;
    }


    public static void main(String[] args) {
        MathImpl obj = new MathImpl();
        System.out.println(obj.add(3,5));
        System.out.println(obj.sub(3,5));
    }

    LocalDate d = LocalDate.of(2022, 12, 15);
    LocalDate e = d;
}
