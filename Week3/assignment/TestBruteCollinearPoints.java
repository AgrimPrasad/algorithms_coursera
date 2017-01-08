// TODO:
// 1. Repeated points
// 2. Array is null
// 3. Array elements are null
// 4. normal points (5)
// 5. fewer than 4 points (2)

import edu.princeton.cs.algs4.StdOut;

public class TestBruteCollinearPoints {
	private TestBruteCollinearPoints() { }

	public static void repeatedPointsTest() {
		Point[] pts = new Point[5];
    	pts[0] = new Point(0, 0);
    	pts[1] = new Point(1, 1);
    	pts[2] = new Point(3, 3);
    	pts[3] = new Point(2, 2);
    	pts[4] = new Point(3, 3);
    	try {
    		BruteCollinearPoints bcPts = new BruteCollinearPoints(pts);
    	} catch(IllegalArgumentException e) { }
	}

	public static void nullArrayTest() {
		Point[] pts = null;
		try {
    		BruteCollinearPoints bcPts = new BruteCollinearPoints(pts);
    	} catch(NullPointerException e) { }

    	Point[] pts2 = new Point[2];
    	pts2[0] = new Point(0, 0);
    	pts2[1] = null;

    	try {
    		BruteCollinearPoints bcPts2 = new BruteCollinearPoints(pts2);
    	} catch(NullPointerException e) { }

	}

	public static void normalPointsTest() {
		Point[] pts = new Point[4];
    	pts[0] = new Point(0, 0);
    	pts[1] = new Point(1, 1);
    	pts[2] = new Point(3, 3);
    	pts[3] = new Point(2, 2);

    	BruteCollinearPoints bcPts = new BruteCollinearPoints(pts);
	   	assert bcPts.numberOfSegments() == 1;
    	for (LineSegment segment : bcPts.segments()) {
    		StdOut.println(segment.toString());
    	}

	}

	public static void fewerPointsTest() {
		Point[] pts = new Point[2];
    	pts[0] = new Point(0, 0);
    	pts[1] = new Point(1, 1);

    	BruteCollinearPoints bcPts = new BruteCollinearPoints(pts);
    	assert bcPts.numberOfSegments() == 0;
	}

	public static void main(String[] args) {
		StdOut.println("Testing Repeated points...");
		repeatedPointsTest();
		StdOut.println("Testing null Array...");
		nullArrayTest();
		StdOut.println("Testing normal points...");
		normalPointsTest();
		StdOut.println("Testing fewer than normal points...");
		fewerPointsTest();
		StdOut.println("All Tests Passed.");
	}
}