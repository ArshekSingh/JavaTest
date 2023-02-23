package test.object_class;

public class ObjectClass {


    static Object printObject(Object o){
        return o;
    }

    public static void main(String a[]){
        Integer i = 1;
        String s = "Asdfg";
        Character character='a';

        System.out.println(printObject(i));
        System.out.println(printObject(s));
        System.out.println(printObject(character));
    }
}
