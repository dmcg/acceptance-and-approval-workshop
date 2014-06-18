package spa2014;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
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

    private static final File DATA_FILE = EnvironmentTest.DATA_FILE;
    private static final File templateFile = new File("templates/air-quality.html");

    private static final String rowTemplate = "<tr><td>%s</td><td>%s</td></tr>";

    @Rule public ApprovalsRule approver = HtmlApprovalsRule.forSourceDir("java/src");

    @Test
    public void readCSVToHTML() throws IOException {
        Iterable<Point> points = readPoints(DATA_FILE);
        String htmlRows = htmlRows(points, rowTemplate);
        ImmutableMap<String, String> templateVars = ImmutableMap.of(
                "${_resourcedir}", "../../../templates",
                "${samples}", htmlRows);
        String html = Templating.substitute(Files.toString(templateFile, Charsets.UTF_8), templateVars);
        approver.assertApproved(html);
    }

    @Test
    public void trend() throws IOException {
        ImmutableList<Point> points = ImmutableList.copyOf(readPoints(DATA_FILE));
        Iterable<Point> trend = Solution.trendFor(points);
        ImmutableMap<String, String> templateVars = ImmutableMap.of(
                "${_resourcedir}", "../../../templates",
                "${samples}", htmlRows(points, rowTemplate),
                "${trend_points", htmlRows(trend, rowTemplate));
        String html = Templating.substitute(Files.toString(templateFile, Charsets.UTF_8), templateVars);
        approver.assertApproved(html);
    }


}
