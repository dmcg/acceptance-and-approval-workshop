package spa2014;

import org.junit.Rule;
import org.junit.Test;
import org.rococoa.okeydoke.junit.ApprovalsRule;

import java.io.File;
import java.io.IOException;

public class ReadCSVTest {

    private static final File DATA_FILE = EnvironmentTest.DATA_FILE;

    @Rule public ApprovalsRule approver = ApprovalsRule.fileSystemRule("java/test");

    private final Solution solution = Solutions.create();

    @Test
    public void readCSVToData() throws IOException {
        Iterable<Point> points = solution.readPoints(DATA_FILE);
        approver.assertApproved(points);
    }

}
