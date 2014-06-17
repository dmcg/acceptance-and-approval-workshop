package spa2014;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.rococoa.okeydoke.junit.ApprovalsRule;

import java.io.File;
import java.io.IOException;

public class ReadCSVTest {

    @Rule public ApprovalsRule approver = ApprovalsRule.fileSystemRule("java/src");

    private static final File dataFile = new File("datasets/air-quality-urban-background-ozone.csv");

    @Test
    public void readCSVToString() throws IOException {
        String content = Files.toString(dataFile, Charsets.UTF_8);
        approver.assertApproved(content);
    }

    @Ignore("To be implemented")
    @Test
    public void readCSVToData() throws IOException {
    }


    @Ignore("To be implemented")
    @Test
    public void readCSVToHTML() throws IOException {
    }
}
