import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF {
 private int[] id;        // id[i] = component identifier of i
 private int count;        // number of components

 public QuickUnionUF(int n) {        // initialize by assigning every element to its own component
  count = n;
  id = new int[n];

  for (int i = 0; i < n; i++) {
   id[i] = i;        
  }
 }

 //validate that p is a valid index for the component id array
 private void validate(int p) {
  int n = id.length;
  if (p < 0 || p >= n) {
    throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n-1));
  }
 }

 private int root(int p) {
  while (id[p] != p) {
    p = id[p];
    root(p);
  }

  return p;
 }

 public void union(int p, int q) {    // add connection between p and q
  validate(p);
  validate(q);

  if (connected(p, q)) {
    StdOut.println(p + " and " + q + " have the same root!");
    return;
  }

  id[root(p)] = q;
  count--;
  StdOut.println("This system now has " + count + " components.");

 }

 public int find(int p) {      // component identifier for p (0 to n-1)
  validate(p);
  return id[p];
 }

 public boolean connected(int p, int q) {  // return true if p and q are in the same component
  validate(p);
  validate(q);
  return root(p) == root(q);
 }

 public int count() {
  return count;       // number of components
 }

 public static void main(String[] args) {
  int n = StdIn.readInt();
  QuickUnionUF uf = new QuickUnionUF(n);
  while(!StdIn.isEmpty()) {
      int p = StdIn.readInt();
      int q = StdIn.readInt();
      if (uf.connected(p, q)) {
        continue;
      }
      uf.union(p, q);
      StdOut.println(p + " " + q);
  }
  StdOut.println("This system finally has " + uf.count() + " components.");
 }

}