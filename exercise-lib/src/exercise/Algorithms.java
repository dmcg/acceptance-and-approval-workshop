package exercise;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import exercise.internal.DataFitting;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

import static com.google.common.collect.Sets.newTreeSet;
import static exercise.Point.*;

public class Algorithms {

    public static Polynomial aardvark(List<Point> points) {
        return lineBetweenRandomElements(points, 1);
    }

    public static Polynomial baboon(List<Point> points) {
        TreeSet<Point> sortedPoints = newTreeSet(by(X));
        sortedPoints.addAll(points);
        return Polynomial.lineBetween(sortedPoints.first(), sortedPoints.last());
    }

    public static Polynomial coatimundi(List<Point> points) {
        TreeSet<Point> sortedPoints = newTreeSet(by(Y));
        sortedPoints.addAll(points);
        return Polynomial.lineBetween(sortedPoints.first(), sortedPoints.last());
    }

    public static Polynomial donkey(List<Point> points) {
        return DataFitting.linearFit(points);
    }

    public static Polynomial elephant(List<Point> points) {
        return lineBetweenRandomElements(points, -1);
    }

    public static Polynomial foxbat(List<Point> points) {
        return DataFitting.polynomialFit(points, 2);
    }

    public static Polynomial gibbon(List<Point> points) {
        return DataFitting.polynomialFit(points, 3);
    }

    private static Map<String, Function<List<Point>, Polynomial>> algorithmsByName = Maps.newHashMap();

    static {
        algorithmsByName.put("aardvark", new Function<List<Point>, Polynomial>() {
            @Override
            public Polynomial apply(List<Point> data) {
                return aardvark(data);
            }
        });
        algorithmsByName.put("baboon", new Function<List<Point>, Polynomial>() {
            @Override
            public Polynomial apply(List<Point> data) {
                return baboon(data);
            }
        });
        algorithmsByName.put("coatimundi", new Function<List<Point>, Polynomial>() {
            @Override
            public Polynomial apply(List<Point> data) {
                return coatimundi(data);
            }
        });
        algorithmsByName.put("donkey", new Function<List<Point>, Polynomial>() {
            @Override
            public Polynomial apply(List<Point> data) {
                return donkey(data);
            }
        });
        algorithmsByName.put("elephant", new Function<List<Point>, Polynomial>() {
            @Override
            public Polynomial apply(List<Point> data) {
                return elephant(data);
            }
        });
        algorithmsByName.put("foxbat", new Function<List<Point>, Polynomial>() {
            @Override
            public Polynomial apply(List<Point> data) {
                return foxbat(data);
            }
        });
        algorithmsByName.put("gibbon", new Function<List<Point>, Polynomial>() {
            @Override
            public Polynomial apply(List<Point> data) {
                return gibbon(data);
            }
        });
    }

    public static Function<List<Point>, Polynomial> byName(String name) {
        if (algorithmsByName.containsKey(name)) {
            return algorithmsByName.get(name);
        } else {
            throw new IllegalArgumentException("invalid algorithm name: " + name);
        }
    }

    private static Polynomial lineBetweenRandomElements(List<Point> points, long seed) {
        Random rng = new Random(seed);
        return Polynomial.lineBetween(randomElement(points, rng), randomElement(points, rng));
    }

    private static Point randomElement(List<Point> points, Random rng) {
        return points.get(rng.nextInt(points.size()));
    }
}