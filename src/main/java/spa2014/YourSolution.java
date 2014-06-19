package spa2014;

import com.google.common.collect.ImmutableList;

import java.io.File;

public class YourSolution implements Solution {

    @Override
    public Iterable<Point> readPoints(File dataFile) {
        return ImmutableList.of();
    }

    @Override
    public String htmlRows(Iterable<Point> points, String rowTemplate) {
        return rowTemplate;
    }

    @Override
    public Iterable<Point> trendFor(Iterable<Point> points) {
        return ImmutableList.of();
    }
}
