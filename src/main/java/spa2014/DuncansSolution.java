package spa2014;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoint;

import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Iterables.*;

@SuppressWarnings("UnusedDeclaration")
public class DuncansSolution implements Solution {

    public static final int DEGREE = 3;

    @Override public Iterable<Point> parsePoints(Iterable<String> lines, int xCol, int yCol) {
        Iterable<String> noHeader = skip(lines, 1);
        Iterable<Point> baseData = transform(noHeader, lineToPoint(xCol, yCol));
        return filter(baseData, positiveValues());
    }

    private Predicate<Point> positiveValues() {
        return new Predicate<Point>() {
            @Override
            public boolean apply(Point input) {
                return input.y > 0;
            }
        };
    }

    private Function<? super String, Point> lineToPoint(final int xCol, final int yCol) {
        return new Function<String, Point>() {
            @Override
            public Point apply(String s) {
                String[] parts = s.split(",");
                return new Point(Double.parseDouble(parts[xCol]), Double.parseDouble(parts[yCol]));
            }
        };
    }

    @Override public String htmlRows(Iterable<Point> points, String rowTemplate) {
        Iterable<String> tableRows = transform(points, tableRowFromPoint(rowTemplate));
        return Joiner.on('\n').join(tableRows);
    }

    @Override public Iterable<Point> fit(Iterable<Point> points) {
        List<Point> pointsList = ImmutableList.copyOf(points);
        PolynomialCurveFitter fitter = PolynomialCurveFitter.create(DEGREE);
        double[] coefficients = fitter.fit(weightedPointsFor(pointsList));
        return transform(pointsList, sameXPolyY(pointsList, coefficients));
    }

    @Override
    public Iterable<Point> extrapolate(ImmutableList<Point> points, double xMax) {
        List<Point> pointsList = ImmutableList.copyOf(points);
        PolynomialCurveFitter fitter = PolynomialCurveFitter.create(DEGREE);
        double[] coefficients = fitter.fit(weightedPointsFor(pointsList));

        Iterable<? extends Number> xs = ContiguousSet.create(Range.closed((int)points.get(0).x, (int)xMax), DiscreteDomain.integers());
        return transform(xs, polyY(xs, coefficients));

    }

    private Function<? super Number, ? extends Point> polyY(Iterable<? extends Number> xs, final double[] coefficients) {
        return new Function<Number, Point>() {
            @Override
            public Point apply(Number x) {
                return new Point(x.doubleValue(), poly(x.doubleValue(), coefficients));
            }
        };
    }

    private Function<? super Point, ? extends Point> sameXPolyY(List<Point> points, final double[] coefficients) {
        return new Function<Point, Point>() {
            @Override
            public Point apply(Point point) {
                return new Point(point.x, poly(point.x, coefficients));
            }
        };
    }

    private double poly(double x, double[] coefficients) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += Math.pow(x, i) * coefficients[i];
        }
        return result;
    }

    private Collection<WeightedObservedPoint> weightedPointsFor(Iterable<Point> points) {
        return ImmutableList.copyOf(transform(points, pointToWeightedObservedPoint()));
    }

    private Function<? super Point, WeightedObservedPoint> pointToWeightedObservedPoint() {
        return new Function<Point, WeightedObservedPoint>() {
            @Override
            public WeightedObservedPoint apply(Point point) {
                return new WeightedObservedPoint(1, point.x, point.y);
            }
        };
    }

    private static Function<? super Point, ? extends String> tableRowFromPoint(final String rowTemplate) {
        return new Function<Point, String>() {
            @Override
            public String apply(Point point) {
                return String.format(rowTemplate, point.x, point.y);
            }
        };
    }

}
