package spa2014;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class EnvironmentTest {

    public static final File DATA_FILE = new File("datasets/atmospheric-co2-1992.csv");

    @Test
    public void runningFromRightDir() {
        assertTrue(DATA_FILE.isFile());
    }
}
