// Permutation: Client for RandomizedQueue to randomly print items

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
import com.javamex.classmexer.MemoryUtil;

public class Permutation {
	public static void main(String[] args) {
		RandomizedQueue<String> queue = new RandomizedQueue<String>();
		int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            queue.enqueue(item);
        }
        for (int i = 0; i < k; i++) {
        	StdOut.println(queue.dequeue());
        }
	}
}
