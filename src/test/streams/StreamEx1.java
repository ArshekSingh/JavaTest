package test.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamEx1 {

    public static void main(String a[]){

        List<Integer> number = Arrays.asList(2,3,4,5,8);
        List<Integer> result = number.stream().map(o -> o*o).collect(Collectors.toList());

        System.out.println(result);

        List<String> strings = Arrays.asList("America", "Antartica", "Zimbabay", "Russia");
        List<String> list = strings.stream().filter(s -> s.startsWith("R")).collect(Collectors.toList());
        System.out.println(list);

        List<String> sorted = strings.stream().sorted().collect(Collectors.toList());
        System.out.println(sorted);

        number.stream().map(x->x*x).forEach(y->System.out.println(y));

        int even =
                number.stream().filter(x->x%2==0).reduce(0,(ans,i)-> ans+i);
            System.out.println(even);

    }
}
