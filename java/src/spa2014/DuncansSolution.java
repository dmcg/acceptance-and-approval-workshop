package spa2014;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DuncansSolution {

    public static ImmutableList<Point> readPoints(File dataFile) throws IOException {
        List<String> lines = Files.readLines(dataFile, Charsets.UTF_8);
        return ImmutableList.copyOf(Iterables.transform(Iterables.skip(lines, 1), lineToPoint()));
    }

    private static Function<? super String, Point> lineToPoint() {
        return new Function<String, Point>() {
            @Override
            public Point apply(String s) {
                String[] parts = s.split(",");
                return new Point(Integer.parseInt(parts[0]), Double.parseDouble(parts[1]));
            }
        };
    }

    public static Function<? super Point, ? extends String> tableRowFromPoint(final String rowTemplate) {
        return new Function<Point, String>() {
            @Override
            public String apply(Point point) {
                return String.format(rowTemplate, point.year, point.value);
            }
        };
    }

}
