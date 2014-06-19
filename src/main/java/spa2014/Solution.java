package spa2014;

import com.google.common.collect.ImmutableList;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoint;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface Solution {

    public Iterable<Point> parsePoints(Iterable<String> lines);

    public String htmlRows(Iterable<Point> points, String rowTemplate);

    public Iterable<Point> fit(Iterable<Point> points);

    public Iterable<Point> extrapolate(ImmutableList<Point> points, double xMax);

}
