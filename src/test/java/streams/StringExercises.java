package streams;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings({"Java8ListSort", "ComparatorCombinators", "Convert2MethodRef", "Duplicates"})
public class StringExercises {
    private final List<String> strings = Arrays.asList("this", "is", "a",
            "list", "of", "strings");

    @Test
    public void stringLengthSort_InnerClass() {     // Java 5, 6, 7
        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        System.out.println(strings);
    }

    @Test
    public void stringLengthSort_lambda() {
        // Use lambda for the Comparator (reverse sort)
        Comparator<String> comparatorReversed =
            (s1, s2) -> s2.length() - s1.length();
        Collections.sort(strings, comparatorReversed);
        assertEquals("[strings, this, list, is, of, a]", strings.toString());

        // Use the "sorted" method on Stream
        String[] sortedStrings = strings.stream()
            .sorted(comparatorReversed)
            .toArray(String[]::new);
        assertEquals("[strings, this, list, is, of, a]", Arrays.toString(sortedStrings));
    }

    private static int compareStrings(String s1, String s2) {
        return s1.length() - s2.length();
    }

    @Test  // Use a lambda that calls 'compareStrings' directly
    public void stringLengthSort_methodCall() {
        String[] stringsByLength = strings.stream()
            .sorted((s1, s2) -> compareStrings(s1, s2))
            .toArray(String[]::new);
        assertEquals("[a, is, of, this, list, strings]", Arrays.toString(stringsByLength));
    }

    @Test  // Use a method ref to 'compareStrings'
    public void stringLengthSort_methodRef() {
        String[] sortedStrings = strings.stream()
            .sorted(StringExercises::compareStrings)
            .toArray(String[]::new);
        assertEquals("[a, is, of, this, list, strings]", Arrays.toString(sortedStrings));
    }

    @Test  // Use Comparator.comparingInt
    public void stringLengthSort_comparingInt() {
        String[] sortedStrings = strings.stream()
            .sorted(Comparator.comparingInt(String::length)
                .thenComparing(Comparator.naturalOrder()))
            .toArray(String[] :: new);
        assertEquals("[a, is, of, list, this, strings]", Arrays.toString(sortedStrings));
    }

    @Test
    public void demoCollectors() {
        // Get only strings of even length
        List<String> evenLength = strings.stream()
            .filter(s -> s.length() % 2 == 0)
        // Add them to a LinkedList
            .collect(Collectors.toCollection(LinkedList :: new));
        assertEquals("[this, is, list, of]", evenLength.toString(), "1");

        // Add the strings to a map of string to length
        Map<String, Integer> map = strings.stream()
            .collect(Collectors.toMap(Function.identity(), String::length));
        assertEquals("{a=1, strings=7, of=2, this=4, is=2, list=4}", map.toString(), "2");

        // Filter out nulls, then print even-length strings
        List<String> stringsWithNulls =
            Arrays.asList(null,"this",null,"is",null,"a",null,"list",null,"of", "strings");
        String[] evens2 = stringsWithNulls.stream()
            .filter(Objects::nonNull)
            .filter(s -> s.length() % 2 == 0)
            .toArray(String[] :: new);
        assertEquals("[this, is, list, of]", Arrays.toString(evens2), "3");


        // Combine the two predicates and use the result to print non-null, even-length strings
        Predicate<String> nonNull = Objects::nonNull;
        Predicate<String> evens = s -> s.length() % 2 == 0;
        Predicate<String> nonNullAndEvens = nonNull.and(evens);

        String[] evenStrings = stringsWithNulls.stream()
            .filter(nonNullAndEvens)
            .toArray(String[] :: new);
        assertEquals("[this, is, list, of]", Arrays.toString(evenStrings), "4");
    }

}
