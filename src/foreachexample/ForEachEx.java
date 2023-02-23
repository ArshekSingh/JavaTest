package foreachexample;

import java.util.ArrayList;
import java.util.List;

public class ForEachEx {

//    public static void main(String args[]) {
//        List<String> games = new ArrayList<String>();
//        games.add("Football");
//        games.add("Badminton");
//        games.add("Hockey");
//        games.add("Cricket");
//        games.forEach(s -> System.out.println(s));
//    }

    public static void main(String a[]){
        List<String> games = new ArrayList<String>();
        games.add("Football");
        games.add("Badminton");
        games.add("Hockey");
        games.add("Cricket");
        for (String g: games) {

            System.out.println(g);

        }
    }


}
