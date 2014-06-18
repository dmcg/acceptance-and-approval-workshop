package spa2014;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.junit.Rule;
import org.junit.Test;
import org.rococoa.okeydoke.ApproverFactories;
import org.rococoa.okeydoke.junit.ApprovalsRule;

import java.io.File;
import java.io.IOException;

import static spa2014.Solution.readPoints;

public class ReadCSVTest {

    private static final File dataFile = new File("datasets/air-quality-urban-background-ozone.csv");

    @Rule public ApprovalsRule approver = new ApprovalsRule(ApproverFactories.fileSystemApproverFactory(new File("java/src")));

    @Test
    public void readCSVToString() throws IOException {
        String content = Files.toString(dataFile, Charsets.UTF_8);
        approver.assertApproved(content);
    }

    @Test
    public void readCSVToData() throws IOException {
        Iterable<Point> points = readPoints(dataFile);
        approver.assertApproved(points);
    }

}
