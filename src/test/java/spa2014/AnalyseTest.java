package spa2014;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.rococoa.okeydoke.junit.ApprovalsRule;

import java.io.File;
import java.io.IOException;

public class AnalyseTest {

    private static final File DATA_FILE = EnvironmentTest.DATA_FILE;
    private static final File templateFile = new File("src/test/resources/air-quality.html");

    private static final String rowTemplate = "<tr><td>%s</td><td>%s</td></tr>";

    @Rule public ApprovalsRule approver = HtmlApprovalsRule.forSourceDir("src/test/java");

    private final Solution solution = Solutions.create();

    @Ignore("Until you've implemented")
    @Test
    public void readCSVToHTML() throws IOException {
        Iterable<Point> points = readPoints(DATA_FILE);
        String htmlRows = solution.htmlRows(points, rowTemplate);
        ImmutableMap<String, String> templateVars = ImmutableMap.of(
                "${_resourcedir}", "../../resources",
                "${samples}", htmlRows);
        String html = Templating.substitute(Files.toString(templateFile, Charsets.UTF_8), templateVars);
        approver.assertApproved(html);
    }

    @Ignore("Until you've implemented")
    @Test
    public void trend() throws IOException {
        ImmutableList<Point> points = ImmutableList.copyOf(readPoints(DATA_FILE));
        Iterable<Point> trend = solution.trendFor(points);
        ImmutableMap<String, String> templateVars = ImmutableMap.of(
                "${_resourcedir}", "../../../templates",
                "${samples}", solution.htmlRows(points, rowTemplate),
                "${trend_points", solution.htmlRows(trend, rowTemplate));
        String html = Templating.substitute(Files.toString(templateFile, Charsets.UTF_8), templateVars);
        approver.assertApproved(html);
    }

    private Iterable<Point> readPoints(File file) throws IOException {
        return solution.parsePoints(Files.readLines(file, Charsets.UTF_8));
    }


}
