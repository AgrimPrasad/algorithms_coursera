import edu.princeton.cs.algs4.StdOut;

public class TestInsertion {
	public static void main(String[] args) {
		String[] comparableStr = new String[]{"blah", "a", "sorting", "me"};
		Insertion.sort(comparableStr);
		Insertion.show(comparableStr);
	}
}