import edu.princeton.cs.algs4.StdOut;

public class TestSelection {
	public static void main(String[] args) {
		String[] comparableStr = new String[]{"blah", "a", "sorting", "me"};
		Selection.sort(comparableStr);
		Selection.show(comparableStr);
	}
}