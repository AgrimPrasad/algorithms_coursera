import edu.princeton.cs.algs4.StdOut;

public class TestPoint {
	private TestPoint() { }
	
	public static void compareToTest() {
		Point pt = new Point(2, 3);
		assert 0 == pt.compareTo(new Point(2, 3));
		assert -1 == pt.compareTo(new Point(3, 3));
		assert -1 == pt.compareTo(new Point(0, 32767));
		assert 1 == pt.compareTo(new Point(4, -5));
	}
	
	public static void main(String[] args) {
		StdOut.println("Testing Point.compareTo()...");
		compareToTest();
		StdOut.println("All tests passed.");
	}
}