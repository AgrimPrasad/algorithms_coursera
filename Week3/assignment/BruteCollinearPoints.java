import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	private final ArrayList<LineSegment> segments = new ArrayList<>();
	
	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		if (points == null) throw new NullPointerException();
		Arrays.sort(points);
		// 4 loops
		int n = points.length;
		for (int i = 0; i < n; i++) {
			if (points[i] == null) throw new NullPointerException();
			for (int j = i + 1; j < n; j++) {
				if (points[j] == null) throw new NullPointerException();
				else if (points[j] == points[i]) throw new IllegalArgumentException("Repeated points found.");
				for (int k = j + 1; k < n; k++) {
					if (points[k] == null) throw new NullPointerException();
					else if (points[k] == points[j]) throw new IllegalArgumentException("Repeated points found.");
					
					if (!areCollinear(points, i, j, k)) continue;
					else {
						for (int l = k + 1; l < n; l++) {
							if (points[l] == null) throw new NullPointerException();
							else if (points[l] == points[k]) throw new IllegalArgumentException("Repeated points found.");
							
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

    public static void main(String[] args) {
    	Point[] pts = new Point[4];
    	pts[0] = new Point(0, 0);
    	pts[1] = new Point(1, 1);
    	pts[3] = new Point(3, 3);
    	pts[2] = new Point(2, 2);
    	// pts[3] = new Point(3, 3);
    	BruteCollinearPoints bcPts = new BruteCollinearPoints(pts);
    	StdOut.println("Number of segments: " + bcPts.numberOfSegments());
    	assert bcPts.numberOfSegments() == 1;
    	for (LineSegment segment : bcPts.segments()) {
    		StdOut.println(segment.toString());
    	}
    }
}