package lambdas;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryOperatorTest {
    @Test
    public void concatAsBinaryOperator() {
        BinaryOperator<String> concat = String::concat;
        concat = String :: concat;

        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        Optional<String> str = strings.stream()
                //.filter(s -> false)
                .reduce(concat);
        assertEquals("thisisalistofstrings", str.orElse(""));
        //System.out.println(str.orElse(""));
    }
}
