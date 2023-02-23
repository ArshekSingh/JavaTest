package test;
import java.util.*;
public class RandomNumberRange {

    public static void main(String args[]){

        int upper = 200;
        int lower = 100;
        int r = (int) (java.lang.Math.random() * (upper - lower)) + lower;
        System.out.println(r);
    }

}
