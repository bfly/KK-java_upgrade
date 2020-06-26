package streams;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumEvens {
    private static final IntPredicate EVENS = n -> n % 2 == 0;
    private static final IntPredicate ODDS = n -> n % 2 != 0;
    private static final List<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5);

    @Test
    public void addEvenElements() {
        int sum = 0;
        for (int n : integers) {
            if (n % 2 == 0) {
                sum += n;
            }
        }
        assertEquals(12, sum);
    }

    @Test
    public void addEvenElementsUsingStreams() {
        int sum = integers.stream()
            .mapToInt(Integer::intValue)
            .filter(EVENS)
            .reduce(0, Integer::sum);
        assertEquals(12, sum);
    }

    @Test
    public void addOddElementsUsingStreams() {
        int sum = integers.stream()
            .mapToInt(Integer::intValue)
            .filter(ODDS)
            .reduce(0, Integer::sum);
        assertEquals(24, sum);
    }

}
