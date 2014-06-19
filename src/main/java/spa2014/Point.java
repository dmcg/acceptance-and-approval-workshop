package spa2014;

import java.util.Iterator;

public class Point {

    public final int year;
    public final double value;

    public Point(int year, double value) {
        this.year = year;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Point{" +
                "year=" + year +
                ", value=" + value +
                '}';
    }
}
