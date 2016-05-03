package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class LinePointMaxHit {

    private List<Point> setOfPoints;

    public LinePointMaxHit(final List<Point> setOfPoints) {
        this.setOfPoints = setOfPoints;
    }

    /**
     *
     * @return
     */
    public MyLine getMaximunNumberOfPointHitsPerLine() {
        final Map<MyLine, Integer> lineCache = new HashMap<MyLine, Integer>();
        // iterate through all points
        MyLine line = null;
        int maxHits = 0;
        int lineHits = 0;
        MyLine result = null;
        for(int index = 0; index < setOfPoints.size(); ++index) {
            //make a point pair
            for(int nextIndex = index + 1; nextIndex < setOfPoints.size(); ++nextIndex) {
                line = new MyLine(setOfPoints.get(index), setOfPoints.get(nextIndex));
                if(lineCache.get(line) == null) {
                    lineHits = 1;
                    lineCache.put(line,lineHits);
                }else {
                    lineHits = lineCache.get(line).intValue() + 1;
                    lineCache.put(line,lineHits);
                }
                if(lineHits > maxHits && (result == null || (!result.equals(line) && result.hashCode() != line.hashCode()))) {
                    result = line;
                    maxHits = lineHits;
                }
            }
        }
        return result;
    }

    public static void main(final String[] args) {
        Point[] points = {new Point(1,1), new Point(2,2), new Point(3,3)};
        LinePointMaxHit linePointMaxHit = new LinePointMaxHit(Arrays.asList(points));

        final MyLine result = linePointMaxHit.getMaximunNumberOfPointHitsPerLine();
        System.out.println(result.slope);
    }
}
