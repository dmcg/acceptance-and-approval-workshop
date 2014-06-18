package spa2014;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import org.junit.Rule;
import org.junit.Test;
import org.rococoa.okeydoke.junit.ApprovalsRule;

import java.io.File;
import java.io.IOException;

import static spa2014.Solution.htmlRows;
import static spa2014.Solution.readPoints;

public class SummariseTest {

    private static final File dataFile = new File("datasets/air-quality-urban-background-ozone.csv");
    private static final File templateFile = new File("templates/air-quality.html");

    private static final String rowTemplate = "<tr><td>%s</td><td>%s</td></tr>";

    @Rule public ApprovalsRule approver = HtmlApprovalsRule.forSourceDir("java/src");

    @Test
    public void readCSVToHTML() throws IOException {
        Iterable<Point> points = readPoints(dataFile);
        String htmlRows = htmlRows(points, rowTemplate);
        ImmutableMap<String, String> templateVars = ImmutableMap.of(
                "${_resourcedir}", "../../../templates",
                "${samples}", htmlRows);
        String html = Templating.substitute(Files.toString(templateFile, Charsets.UTF_8), templateVars);
        approver.assertApproved(html);
    }



}
