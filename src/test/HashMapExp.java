package test;

import java.util.*;
import java.util.logging.Logger;


public class HashMapExp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //Taking inputNumber from the user

        System.out.println("Enter the number :");
        String str = sc.nextLine();
        int inputNumber = Integer.parseInt(str);

        //Creating a HashMap object with digits as keys and their frequency as values

        HashMap<Integer, Integer> digitCountMap = new HashMap<Integer, Integer>();

        while (inputNumber != 0)
        {
            //Get the lastDigit of inputNumber

            int lastDigit = inputNumber % 10;

            if(digitCountMap.containsKey(lastDigit))
            {
                //If lastDigit is already present in the map, incrementing its frequency by 1

                digitCountMap.put(lastDigit, digitCountMap.get(lastDigit)+1);
            }
            else
            {
                //If lastDigit is not present in the map, inserting lastDigit into map with 1 as its value

                digitCountMap.put(lastDigit, 1);
            }

            //Removing the lastDigit from inputNumber

            inputNumber = inputNumber / 10;
        }

        //Printing digits and their frequency

        System.out.println("===================");

        System.out.println("Digits : Frequency");

        System.out.println("===================");

        Set<Integer> keys = digitCountMap.keySet();

        List<Integer> listOfKeys = new ArrayList<>(keys);

        Collection<Integer> values = digitCountMap.values();

        List<Integer> listOfValues = new ArrayList<>(values);

        List<Integer> sortedArray = new ArrayList<>();

        try {
            for (Integer key : listOfKeys) {
                for (int i = 0; i < key; i++) {
                    sortedArray.add(listOfValues.get(key));
                }
            }
        }
        catch (Exception exception){
            System.out.println("Error");
            Logger.getLogger(exception.getMessage());
        }

        Collections.sort(sortedArray);
        System.out.println(sortedArray);

//        System.out.println(listOfKeys);
//
//        System.out.println(listOfValues);







//        for (Integer key : keys)
//        {
//            System.out.println("   "+key+"   :   "+digitCountMap.get(key));
//        }
//        keys.forEach(key -> {
//            int temp = key;
//            for(int i=0; i<temp; i++){
//                System.out.print(digitCountMap.get(key));
//            }
//        });

        sc.close();
    }
}
