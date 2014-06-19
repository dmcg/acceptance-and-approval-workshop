package spa2014;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

@SuppressWarnings("UnusedDeclaration")
public class DuncansSolution implements Solution {

    public Iterable<Point> parsePoints(Iterable<String> lines) {
        return Iterables.transform(Iterables.skip(lines, 1), lineToPoint());
    }

    private Function<? super String, Point> lineToPoint() {
        return new Function<String, Point>() {
            @Override
            public Point apply(String s) {
                String[] parts = s.split(",");
                return new Point(Integer.parseInt(parts[0]), Double.parseDouble(parts[1]));
            }
        };
    }

    public String htmlRows(Iterable<Point> points, String rowTemplate) {
        Iterable<String> tableRows = Iterables.transform(points, tableRowFromPoint(rowTemplate));
        return Joiner.on('\n').join(tableRows);
    }

    public Iterable<Point> trendFor(Iterable<Point> points) {
        return ImmutableList.of();
    }

    private static Function<? super Point, ? extends String> tableRowFromPoint(final String rowTemplate) {
        return new Function<Point, String>() {
            @Override
            public String apply(Point point) {
                return String.format(rowTemplate, point.year, point.value);
            }
        };
    }

}
