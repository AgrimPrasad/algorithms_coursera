import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
 private int edgeLength;   // User specified number of sites per edge of grid
 private int[] id;         // id[i] = component identifier of i
 private int[] sz;         // size of trees, only stores sizes for roots
 private int count;        // number of components
 private boolean[][] openSt;   // 2-D array for open Status of sites

 private int numOpen = 0;            // number of open sites
 private int topVirtual = -1;       // Virtual site connected to all elements in the first row
 private int botVirtual = -2;       // Virtual site connected to all elements in the last row
 // private int invalidSite = -3;      // Needed when searching for an adjacent site

 public Percolation(int n) {        // initialize by assigning every element to its own component
  if (n <=0) {
        throw new IllegalArgumentException("Specified grid size " + n + " must be > 0");
  }

  int edgeLength = n;
  int gridSize = n*n;
  count = gridSize;
  id = new int[gridSize];
  sz = new int[gridSize];
  openSt = new boolean[n][n];

  int idIdx = 0;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      openSt[i][j] = false;     // block all initially

      if (i == 0) {             // top row
        id[idIdx] = topVirtual;
      }
      else if (i == n - 1) {    // bottom row
        id[idIdx] = botVirtual;
      }
      else {
        id[idIdx] = idIdx;  
      }
      idIdx++;
    }
  }
 }

 // open site (row, col) if it is not open already
 public void open(int row, int col) {
  if (isOpen(row, col)) {
    return;
  }

  openSt[row][col] = true;

  int site1d = convert2dto1d(row, col);
  boolean leftOpen = false, topOpen = false, rightOpen = false, bottomOpen = false;

  for (int i = 0; i < 4; i++) {
    try {
      switch (i) {
        case 0: 
            if (isOpen(row, col - 1)) {
              int left1d = convert2dto1d(row, col - 1);
              union(left1d, site1d);
            }
            break;
        case 1:
            if (isOpen(row - 1, col)) {
              int top1d = convert2dto1d(row - 1, col);
              union(top1d, site1d);
            }
            break;               
        case 2:
            if (isOpen(row, col + 1)) {
              int right1d = convert2dto1d(row, col + 1);
              union(right1d, site1d);
            }
            break;
        case 3:
            if (isOpen(row + 1, col)) {
              int bottom1d = convert2dto1d(row + 1, col);
              union(bottom1d, site1d);
            }
            break;
        default: break;
      }
    }
    catch (IndexOutOfBoundsException e) { //indices not in 2d array
      continue;
    }
  }


 }

 // is site (row, col) open?
 public boolean isOpen(int row, int col) {
  return openSt[row][col] == true;
 }

 // is site (row, col) full?
 public boolean isFull(int row, int col) {
  return false;
 }

 // number of open sites
 public int numberOfOpenSites() {
  return 0;
 }

 // does the system percolate?
 public boolean percolates() {
  return false;
 }

 // get left, top, right, bottom sites
 // public int[] getAdjacentSites(int p) {
 //  int left = p - 1;
 //  if ()
 // }

 // 2-D coordinates to 1-D coordinates
 private int convert2dto1d(int x, int y) {
  return (x*edgeLength) + y;
 }

 // 1-D coordinates to 2-D coordinates
 private int[] convert1dto2d(int x) {
  int row = x / edgeLength;
  int col = x % edgeLength;

  return new int[]{row, col};
 }

 // validate that p is a valid index for the component id array
 private void validate(int p) {
  int n = id.length;
  if (p < 0 || p >= n) {
    throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n-1));
  }
 }

 private int root(int p) {
  while (id[p] != p) {
    id[p] = id[id[p]]; //Point each node to its grandparent to halve tree length
    p = id[p];
  }

  return p;
 }

 private void union(int p, int q) {    // add connection between p and q
  validate(p);
  validate(q);

  int pRoot = root(p);
  int qRoot = root(q);

  if (pRoot == qRoot) {
    StdOut.println(p + " and " + q + " have the same root!");
    return;
  }

  if (sz[pRoot] < sz[qRoot]) {
    id[pRoot] = qRoot;
    sz[qRoot] += sz[pRoot];
  } else {
    id[qRoot] = pRoot;
    sz[pRoot] += sz[qRoot];
  }

  count--;
  StdOut.println("This system now has " + count + " components.");

 }

 private int find(int p) {      // component identifier for p (0 to n-1)
  validate(p);
  return id[p];
 }

 private boolean connected(int p, int q) {  // return true if p and q are in the same component
  validate(p);
  validate(q);
  return root(p) == root(q);
 }

 private int count() {
  return count;       // number of components
 }

 public static void main(String[] args) {
  int n = 4;
  Percolation uf = new Percolation(n);

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      uf.open(i, j);
      StdOut.println("Open Test: " + uf.isOpen(i, j) + " for n = (" + i + "," + j + ")");
    }
  }
 }

}