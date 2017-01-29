import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class BruteCollinearPoints {
	Point[] points;
	private final ArrayList<LineSegment> segments = new ArrayList<>();
	
	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] pointsArg) {
		if (pointsArg == null) throw new NullPointerException();
		int n = pointsArg.length;
		points = new Point[n];
		System.arraycopy(pointsArg, 0, points, 0, n);
		Arrays.sort(points);
		// 4 loops
		for (int i = 0; i < n; i++) {
			if (points[i] == null) throw new NullPointerException();
			for (int j = i + 1; j < n; j++) {
				if (points[j] == null) throw new NullPointerException();
				else if (areEqual(points, j, i)) throw new IllegalArgumentException("Repeated points found.");
				for (int k = j + 1; k < n; k++) {
					if (points[k] == null) throw new NullPointerException();
					else if (areEqual(points, k, j)) throw new IllegalArgumentException("Repeated points found.");
					
					if (!areCollinear(points, i, j, k)) continue;
					else {
						for (int l = k + 1; l < n; l++) {
							if (points[l] == null) throw new NullPointerException();
							else if (areEqual(points, l, k)) throw new IllegalArgumentException("Repeated points found.");
							
							if (areCollinear(points, i, j, l)) {
								segments.add(new LineSegment(points[i], points[l]));
							}
						}
					}
				}
			}
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
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
    }
}