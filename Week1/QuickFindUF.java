import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {
 private int[] id;        // id[i] = component identifier of i
 private int count;        // number of components

 public QuickFindUF(int n) {        // initialize by assigning every element to its own component
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

 public void union(int p, int q) {    // add connection between p and q
  validate(p);
  validate(q);
  int pID = id[p];
  int qID = id[q];

  if (pID == qID) {
    return;
  }

  for (int i = 0; i < count; i++) {
   if (id[i] == pID) {
    id[i] = qID;
   }
  }
  count--;
 }

 public int find(int p) {      // component identifier for p (0 to n-1)
  validate(p);
  return id[p];
 }

 public boolean connected(int p, int q) {  // return true if p and q are in the same component
  validate(p);
  validate(q);
  return id[p] == id[q];
 }

 public int count() {
  return count;       // number of components
 }

 public static void main(String[] args) {
  int n = StdIn.readInt();
  QuickFindUF uf = new QuickFindUF(n);
  while(!StdIn.isEmpty()) {
      int p = StdIn.readInt();
      int q = StdIn.readInt();
      if (uf.connected(p, q)) continue;
      uf.union(p, q);
      StdOut.println(p + " " + q);
  }
  // TODO Correct bug for number of components
  StdOut.println("This system has " + uf.count() + " components.");
 }

}