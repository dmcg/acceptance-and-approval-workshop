package spa2014;

import java.io.File;
import java.io.IOException;

public interface Solution {

    Iterable<Point> parsePoints(Iterable<String> lines);

    String htmlRows(Iterable<Point> points, String rowTemplate);

    Iterable<Point> trendFor(Iterable<Point> points);
}
