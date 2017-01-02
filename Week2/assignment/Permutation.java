// Permutation: Client for Deque to randomly print items

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
import com.javamex.classmexer.MemoryUtil;

public class Permutation {
	public static void main(String[] args) {
		Deque<String> queue = new Deque<String>();
		int k = Integer.parseInt(args[0]);;
		int randomBinary = 0;
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            randomBinary = StdRandom.uniform(0, 2); // 0 or 1
            if (randomBinary == 0) {
            	queue.addFirst(item);
            }
            else {
            	queue.addLast(item);
            }
        }
        for (int i = 0; i < k; i++) {
        	randomBinary = StdRandom.uniform(0, 2); // 0 or 1
        	if (randomBinary == 0) {
            	StdOut.println(queue.removeFirst());
            }
            else {
            	StdOut.println(queue.removeLast());
            }
        }
	}
}
