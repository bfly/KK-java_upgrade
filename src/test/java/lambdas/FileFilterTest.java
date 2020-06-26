package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final String root = "src/main/java";

    @Test
    public void listFilesNoArgs() {
        File f = new File(root);
        File[] files = f.listFiles();
        assertEquals(20, Objects.requireNonNull(files).length);
    }

    @Test
    public void listJavaFiles_anonInnerClass() {
        File f = new File(root);
        File[] files = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".java");
            }
        });

        assertEquals(8, Objects.requireNonNull(files).length);
    }

    @Test
    public void listJavaFiles_lambda() {
        File f = new File(root);
        FileFilter filter = pathname -> pathname.getName().endsWith(".java");
        File[] files = f.listFiles(filter);

        assertEquals(8, Objects.requireNonNull(files).length);
    }
}
