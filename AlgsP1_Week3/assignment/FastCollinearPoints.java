import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
	Point[] pointsNatural;
	private final ArrayList<LineSegment> segments = new ArrayList<>();
	
	// finds all line segments containing 4 points
	public FastCollinearPoints(Point[] pointsArg) {
		if (pointsArg == null) throw new NullPointerException();
		int n = pointsArg.length;
		pointsNatural = new Point[n];
		System.arraycopy(pointsArg, 0, pointsNatural, 0, n);
		Arrays.sort(pointsNatural);	// Merge Sort by natural order firstly to get first and last pts in order

		// Debug. Delete later
		StdOut.println("Ordering after natural order sort");
		for (Point point : pointsNatural) {
			StdOut.println(point.toString());
		}
		// Debug end. Delete later

		for (int i = 0; i < n; i++) {
			if (pointsNatural[i] == null) throw new NullPointerException();
			Point[] points = new Point[n - i];
			int m = points.length;
			System.arraycopy(pointsNatural, i, points, 0, m);
			Arrays.sort(points, points[i].slopeOrder());	// Stable Merge sort by slope order
			// Debug. Delete later
			StdOut.println("Ordering after slope order sort");
			for (Point point : points) {
				StdOut.println(point.toString());
			}
			// Debug end. Delete later

			int j = 1;
			int lastCollinearPt = j + 1;
			// boolean collinearPtFound = false;
			while (j <= m - 2) {
				if (points[0] == null || points[j] == null || points[j + 1] == null) {
					throw new NullPointerException();
				}
				
				if (areEqual(points, 0, j) || areEqual(points, j, j + 1)) {
					throw new IllegalArgumentException("Repeated points found.");
				}
				
				if (areCollinear(points, 0, j, j + 1)) {
					// Debug. Delete later
					StdOut.println("Collinear points found:" + points[i] + ", " + points[j] + ", " + points[j + 1]);
					// Debug end. Delete later
					// collinearPtFound = true;
					lastCollinearPt = j + 1;
					// j++;
					while (lastCollinearPt <= m - 2 && areCollinear(points, 0, lastCollinearPt, lastCollinearPt + 1)) lastCollinearPt++;
					break;
				}
				// else if (lastCollinearPt == i + 1) j++; // no collinear pts. found
				// else break;
				j++;
			}
			if (lastCollinearPt > j + 1) { // 3 (or more) collinear pts. found
				segments.add(new LineSegment(points[i], points[lastCollinearPt]));
				// i = lastCollinearPt + 1;
			}
			// else i++;
		}
	}
    
    // the number of line segments
    public int numberOfSegments() { return segments.size(); }

    // the line segments
    public LineSegment[] segments() {
    	return segments.toArray(new LineSegment[segments.size()]);
    }

    // 4 points are collinear?
    private boolean areCollinear(Point[] points, int i, int j, int k) {
    	return points[i].slopeOrder().compare(points[j], points[k]) == 0;
    }

    // 2 points are equal?
    private boolean areEqual(Point[] points, int i, int j) {
    	return points[i].compareTo(points[j]) == 0;
    }

    public static void main(String[] args) {
    	// read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
    }
}