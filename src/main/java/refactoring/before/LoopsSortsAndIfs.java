package refactoring.before;

import java.util.*;
import java.util.stream.Collectors;

public class LoopsSortsAndIfs {
    public static void main(String[] args) {

        List<String> evensList = Arrays.stream("this is an array of strings".split(" "))
            .filter(s -> s.length() % 2 == 0)
            .sorted(Comparator.comparingInt(String::length))
            .collect(Collectors.toList());

        String[] strings = "this is an array of strings".split(" ");

        List<String> evenLengths = new ArrayList<>();
        for (String s : strings) {
            if (s.length() % 2 == 0) {
                evenLengths.add(s);
            }
        }

        Collections.sort(evenLengths, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });

//        for (String s : evenLengths) {
//            System.out.println(s);
//        }

        System.out.println(evenLengths.equals(evensList));
    }
}
