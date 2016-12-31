import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class TestPercolation {

 public static void main(String[] args) {
  int n = StdIn.readInt();
  Percolation uf = new Percolation(n);
  StdOut.println("Full Test: " + uf.isFull(1, 1) + " for n = (" + 1 + "," + 1 + ") after 0 sites opened");
  StdOut.println("Percolation Test: " + uf.percolates() + " after 0 sites opened");

  while(!StdIn.isEmpty()) {
      int i = StdIn.readInt();
      int j = StdIn.readInt();
      uf.open(i, j);
      StdOut.println("Open Test: " + uf.isOpen(i, j) + " for n = (" + i + "," + j + ")");
      StdOut.println("Full Test: " + uf.isFull(i, j) + " for n = (" + i + "," + j + ")");
      StdOut.println("Percolation Test: " + uf.percolates() + " after n = (" + i + "," + j + ")");
  }
  StdOut.println(uf.numberOfOpenSites() + " total sites open.");
 }

}