package lambdas;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionalInterfacesTest {

    @SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
    @Test
    public void implementConsumerUsingAnonInnerClass() {
        Consumer<String> consumer = new Consumer<>() {
            @Override
            public void accept( String s ) {
                System.out.println(s);
            }
        };
        consumer.accept("Hello, World!");
    }

    @Test
    public void implementConsumerUsingLambda() {
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("Hello, World!");
    }

    @Test
    public void implementConsumerUsingMethodReference() {
        Consumer<String> consumer = System.out :: println;
        consumer.accept("Hello, World!");
    }

    @Test
    public void implementSupplierUsingAnonInnerClass() {
        Supplier<String> supplier = new Supplier<>() {
            @Override
            public String get() {
                return "Hello";
            }
        };
        assertEquals("Hello", supplier.get());
    }

    @Test
    public void implementSupplierUsingLambda() {
        Supplier<String> supplier = () -> "Hello";
        assertEquals("Hello", supplier.get());
    }

    @Test
    public void implementSupplierUsingMethodReference() {
        // Create a Supplier<Double> that calls Math.random()
        Supplier<Double> supplier = Math :: random;
        assertTrue(supplier.get() >= 0.0);
        assertTrue(supplier.get() <= 1.0);

        // Create a DoubleSupplier that does the same
        DoubleSupplier doubleSupplier = Math:: random;
        assertTrue(doubleSupplier.getAsDouble() >= 0.0);
        assertTrue(doubleSupplier.getAsDouble() <= 1.0);
    }

    @Test
    public void constructorReference() {
        List<String> stringList = Arrays.asList("a", "b", "b", "c", "d", "d");
        assertEquals(6, stringList.size());

        // Add the strings to a Set
        Set<String> strings = new HashSet<>(stringList);
        assertEquals(4, strings.size());
        assertEquals(HashSet.class, strings.getClass());

        // Add the strings to a TreeSet
        TreeSet<String> sortedStrings = new TreeSet<>(strings);
        assertEquals(4, sortedStrings.size());
        assertEquals(TreeSet.class, sortedStrings.getClass());
        assertEquals("a", sortedStrings.first());
    }

    @Test
    public void filterWithPredicate() throws Exception {
        IntStream.of(3, 1, 4, 1, 5, 9)
            .filter(n -> n % 2 == 0)  // accept even nums only
            .forEach(n -> assertEquals(0, n % 2));
    }
}
