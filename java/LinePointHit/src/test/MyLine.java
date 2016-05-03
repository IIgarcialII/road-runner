package test;

/**
 * Created by Alejandro Garcia on 9/2/2014.
 */
public class MyLine {

    public float slope;
    public float intercept;

    // To create a line y = ax + b, a = slope and b = intercept
    // a = (x2 - x1) / (y2 - y1)
    // b
    // (y2 - y1) = a(x2 - x1)
    public MyLine(final Point point, final Point otherPoint) {
        this.slope = (point.x - otherPoint.x) / (point.y - otherPoint.y);
        //we use one of the points to compute the intercept
        //y - y1 = a(x - x1) => b =  a*x1 + y1
        intercept =  - (slope * point.x) + point.y;
    }

    public boolean isInLine(final Point point) {
        return slope * point.x + intercept == point.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyLine myLine = (MyLine) o;

        if (Float.compare(myLine.intercept, intercept) != 0) return false;
        if (Float.compare(myLine.slope, slope) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (slope != +0.0f ? Float.floatToIntBits(slope) : 0);
        result = 31 * result + (intercept != +0.0f ? Float.floatToIntBits(intercept) : 0);
        return result;
    }
}
