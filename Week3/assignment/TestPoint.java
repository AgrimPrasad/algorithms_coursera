import edu.princeton.cs.algs4.StdOut;

public class TestPoint {
	private TestPoint() { }

	public static void slopeToTest() {
		Point pt = new Point(-5, -8);
		assert Double.NEGATIVE_INFINITY == pt.slopeTo(new Point(-5, -8));
		assert Double.POSITIVE_INFINITY == pt.slopeTo(new Point(-5, 8));
		assert +0.0 == pt.slopeTo(new Point(5, -8));
		assert 1.6 == pt.slopeTo(new Point(5, 8));
		assert -0.0004883706733410659 == pt.slopeTo(new Point(-32767, 8));
		assert -0.2 == pt.slopeTo(new Point(5, -10));
	}
	
	public static void compareToTest() {
		Point pt = new Point(2, 3);
		assert 0 == pt.compareTo(new Point(2, 3));
		assert -1 == pt.compareTo(new Point(3, 3));
		assert -1 == pt.compareTo(new Point(2, 4));
		assert -1 == pt.compareTo(new Point(0, 32767));
		assert 1 == pt.compareTo(new Point(4, -5));
		assert 1 == pt.compareTo(new Point(-4, -5));
		assert -1 == pt.compareTo(new Point(-32767, 5));
	}

	public static void slopeOrderTest() {
		Point pt = new Point(0, 0);
		Point pt1 = new Point(1, 1);
		Point pt2 = new Point(2, 2);
		assert pt.slopeOrder().compare(pt1, pt2) == 0;

		Point pt3 = new Point(1, -1);
		assert pt.slopeOrder().compare(pt1, pt3) == 1;

		Point pt4 = new Point(2, 3);
		assert pt.slopeOrder().compare(pt1, pt4) == -1;
	}
	
	public static void main(String[] args) {
		StdOut.println("Testing Point.compareTo()...");
		compareToTest();
		StdOut.println("Testing Point.slopeTo()...");
		slopeToTest();
		StdOut.println("Testing Point.slopeOrderTest()...");
		slopeOrderTest();
		StdOut.println("All tests passed.");
	}
}