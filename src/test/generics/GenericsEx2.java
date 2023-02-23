package test.generics;

public class GenericsEx2 {

    static <A> void printArray(A[] input){
        for(A a: input){
            System.out.print(a+" ");
        }
        System.out.println();

    }

    static <T> void printVariable(T inp){
        System.out.println(inp);

    }

    public static void main(String a[]){
        Integer intArray[] = {1,2,3,4,5};
        Double doubleArray[] = {1.1, 2.2, 3.3};
        Character character[] = {'a', 'b', 'c'};

        Integer x = 1;
        Double y = 1.1;
        Character z = 'a';
        printVariable(x);
        printVariable(y);
        printVariable(z);


        printArray(intArray);
        printArray(doubleArray);
        printArray(character);

    }
}
