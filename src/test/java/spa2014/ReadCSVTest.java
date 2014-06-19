package spa2014;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.junit.Rule;
import org.junit.Test;
import org.rococoa.okeydoke.junit.ApprovalsRule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReadCSVTest {

    private static final File DATA_FILE = EnvironmentTest.DATA_FILE;

    @Rule public ApprovalsRule approver = ApprovalsRule.fileSystemRule("src/test/java");

    private final Solution solution = Solutions.create();

    @Test
    public void readCSVToData() throws IOException {
        List<String> lines = Files.readLines(DATA_FILE, Charsets.UTF_8);
        Iterable<Point> points = solution.parsePoints(lines, 0, 1);
        approver.assertApproved(points);
    }

}
