package spa2014;

import org.junit.Rule;
import org.junit.Test;
import org.rococoa.okeydoke.junit.ApprovalsRule;

import java.io.File;
import java.io.IOException;

import static spa2014.Solution.readPoints;

public class ReadCSVTest {

    private static final File DATA_FILE = EnvironmentTest.DATA_FILE;

    @Rule public ApprovalsRule approver = ApprovalsRule.fileSystemRule("java/src");

    @Test
    public void readCSVToData() throws IOException {
        Iterable<Point> points = readPoints(DATA_FILE);
        approver.assertApproved(points);
    }

}
