import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
	private final ArrayList<LineSegment> segments = new ArrayList<>();
	
	// finds all line segments containing 4 points
	public FastCollinearPoints(Point[] points) {
		if (points == null) throw new NullPointerException();

		int n = points.length;
		Arrays.sort(points);	// Merge Sort by natural order firstly to get first and last pts in order
		for (int i = 0; i < n;) {
			Arrays.sort(points, points[i].slopeOrder());	// Stable Merge sort by slope order
			int j = i;
			int lastCollinearPt = i;
			while (j <= n - 3) {
				if (points[j] == null || points[j + 1] == null || points[j + 2] == null) {
					throw new NullPointerException();
				}
				
				if (areEqual(points, j, j + 1) || areEqual(points, j + 1, j + 2)) {
					throw new IllegalArgumentException("Repeated points found.");
				}
				
				if (areCollinear(points, j, j + 1, j + 2)) lastCollinearPt = j + 2;
				else break;

				j++;
			}
			if (lastCollinearPt != i) {
				segments.add(new LineSegment(points[i], points[lastCollinearPt]));
				i = lastCollinearPt + 1;
			}
			else i++;
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