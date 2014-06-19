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
    private static final int YEAR_COL = 2;
    private static final int DATA_COL = 3;
    private static final File templateFile = new File("src/test/resources/air-quality.html");

    private static final String rowTemplate = "<tr><td>%s</td><td>%s</td></tr>";

    @Rule public ApprovalsRule approver = HtmlApprovalsRule.forSourceDir("src/test/java");

    private final Solution solution = Solutions.create();

    @Ignore("Until you've implemented")
    @Test
    public void readCSVToHTML() throws IOException {
        Iterable<Point> points = readPoints(DATA_FILE, YEAR_COL, DATA_COL);
        String htmlRows = solution.htmlRows(points, rowTemplate);
        ImmutableMap<String, String> templateVars = ImmutableMap.of(
                "${_resourcedir}", "../../resources",
                "${history}", htmlRows);
        String html = Templating.substitute(Files.toString(templateFile, Charsets.UTF_8), templateVars);
        approver.assertApproved(html);
    }

    @Ignore("Until you've implemented")
    @Test
    public void fit() throws IOException {
        ImmutableList<Point> points = ImmutableList.copyOf(readPoints(DATA_FILE, YEAR_COL, DATA_COL));
        Iterable<Point> fit = solution.fit(points);
        ImmutableMap<String, String> templateVars = ImmutableMap.of(
                "${_resourcedir}", "../../resources",
                "${history}", solution.htmlRows(points, rowTemplate),
                "${projection}", solution.htmlRows(fit, rowTemplate));
        String html = Templating.substitute(Files.toString(templateFile, Charsets.UTF_8), templateVars);
        approver.assertApproved(html);
    }

    @Ignore("Until you've implemented")
    @Test
    public void extrapolate() throws IOException {
        ImmutableList<Point> points = ImmutableList.copyOf(readPoints(DATA_FILE, YEAR_COL, DATA_COL));
        Iterable<Point> fit = solution.extrapolate(points, 2050);
        ImmutableMap<String, String> templateVars = ImmutableMap.of(
                "${_resourcedir}", "../../resources",
                "${history}", solution.htmlRows(points, rowTemplate),
                "${projection}", solution.htmlRows(fit, rowTemplate));
        String html = Templating.substitute(Files.toString(templateFile, Charsets.UTF_8), templateVars);
        approver.assertApproved(html);
    }

    private Iterable<Point> readPoints(File file, int xCol, int yCol) throws IOException {
        return solution.parsePoints(Files.readLines(file, Charsets.UTF_8), xCol, yCol);
    }


}
