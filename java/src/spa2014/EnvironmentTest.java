package spa2014;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class EnvironmentTest {

    private static final File dataFile = new File("datasets/air-quality-urban-background-ozone.csv");

    @Test
    public void runningFromRightDir() {
        assertTrue(dataFile.isFile());
    }
}
