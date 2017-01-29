// TODO:
// 1. Repeated points
// 2. Array is null
// 3. Array elements are null
// 4. normal points (5)
// 5. fewer than 4 points (2)

import edu.princeton.cs.algs4.StdOut;

public class TestFastCollinearPoints {
	private TestFastCollinearPoints() { }

	public static void repeatedPointsTest() {
		Point[] pts = new Point[5];
    	pts[0] = new Point(0, 0);
    	pts[1] = new Point(1, 1);
    	pts[2] = new Point(3, 3);
    	pts[3] = new Point(2, 2);
    	pts[4] = new Point(3, 3);
    	try {
    		FastCollinearPoints bcPts = new FastCollinearPoints(pts);
    	} catch(IllegalArgumentException e) { }
	}

	public static void nullArrayTest() {
		Point[] pts = null;
		try {
    		FastCollinearPoints bcPts = new FastCollinearPoints(pts);
    	} catch(NullPointerException e) {StdOut.println("Null Array caught"); }

    	Point[] pts2 = new Point[2];
    	pts2[0] = new Point(0, 0);
    	pts2[1] = null;

    	try {
    		FastCollinearPoints bcPts2 = new FastCollinearPoints(pts2);
    	} catch(NullPointerException e) {StdOut.println("Null Array entry caught"); }

	}

	public static void normalPointsTest() {
		Point[] pts = new Point[4];
    	pts[0] = new Point(0, 0);
    	pts[1] = new Point(1, 1);
    	pts[2] = new Point(3, 3);
    	pts[3] = new Point(2, 2);

    	FastCollinearPoints bcPts = new FastCollinearPoints(pts);
    	for (LineSegment segment : bcPts.segments()) {
    		StdOut.println(segment.toString());
    	}
	   	assert bcPts.numberOfSegments() == 1;

	}

	public static void fewerPointsTest() {
		Point[] pts = new Point[2];
    	pts[0] = new Point(0, 0);
    	pts[1] = new Point(1, 1);

    	FastCollinearPoints bcPts = new FastCollinearPoints(pts);
    	assert bcPts.numberOfSegments() == 0;
	}

	public static void morePointsTest() {
		Point[] pts = new Point[7];
    	pts[0] = new Point(0, 0);
    	pts[1] = new Point(1, 1);
    	pts[2] = new Point(3, 3);
    	pts[3] = new Point(2, 2);
    	pts[4] = new Point(5, 5);
    	pts[6] = new Point(4, 4);
    	pts[5] = new Point(4, 4329);

    	FastCollinearPoints bcPts = new FastCollinearPoints(pts);
    	for (LineSegment segment : bcPts.segments()) {
    		StdOut.println(segment.toString());
    	}
	   	assert bcPts.numberOfSegments() == 1;
	}

	public static void main(String[] args) {
		StdOut.println("Testing Repeated points...");
		repeatedPointsTest();
		StdOut.println("Testing null Array...");
		nullArrayTest();
		StdOut.println("Testing normal points...");
		normalPointsTest();
		StdOut.println("Testing fewer than 4 points...");
		fewerPointsTest();
		StdOut.println("Testing more than 4 points...");
		morePointsTest();
		StdOut.println("All Tests Passed.");
	}
}