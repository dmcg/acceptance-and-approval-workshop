package spa2014;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoint;

import java.util.Collection;
import java.util.List;

public class YourSolution implements Solution {

    private static final int YEAR_COL = 0;
    private static final int DATA_COL = (Integer) null; // TODO

    @Override
    public Iterable<Point> parsePoints(Iterable<String> lines) {
        // TODO
        return ImmutableList.of();
    }

    @Override
    public String htmlRows(Iterable<Point> points, String rowTemplate) {
        // TODO
        return rowTemplate;
    }

    @Override
    public Iterable<Point> fit(Iterable<Point> points) {
        List<Point> pointsList = ImmutableList.copyOf(points);
        PolynomialCurveFitter fitter = PolynomialCurveFitter.create(1);
        double[] coefficients = fitter.fit(weightedPointsFor(pointsList));
        return Iterables.transform(pointsList, sameXPolyY(pointsList, coefficients));
    }

    private double poly(double x, double[] coefficients) {
        // TODO
        return 0;
    }

    @Override
    public Iterable<Point> extrapolate(ImmutableList<Point> points, double xMax) {
        // TODO
        return ImmutableList.of();
    }

    private Collection<WeightedObservedPoint> weightedPointsFor(Iterable<Point> points) {
        return ImmutableList.copyOf(Iterables.transform(points, pointToWeightedObservedPoint()));
    }

    private Function<? super Point, WeightedObservedPoint> pointToWeightedObservedPoint() {
        return new Function<Point, WeightedObservedPoint>() {
            @Override
            public WeightedObservedPoint apply(Point point) {
                return new WeightedObservedPoint(1, point.x, point.y);
            }
        };
    }

    private Function<? super Point,? extends Point> sameXPolyY(Iterable<Point> points, final double[] coefficients) {
        return new Function<Point, Point>() {
            @Override
            public Point apply(Point point) {
                return new Point(point.x, poly(point.x, coefficients));
            }
        };
    }
}
