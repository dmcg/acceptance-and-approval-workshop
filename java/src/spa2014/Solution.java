package spa2014;

import java.io.File;
import java.io.IOException;

public interface Solution {

    Iterable<Point> readPoints(File dataFile) throws IOException;

    String htmlRows(Iterable<Point> points, String rowTemplate);

    Iterable<Point> trendFor(Iterable<Point> points);
}
