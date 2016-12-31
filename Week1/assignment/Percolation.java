import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
 private int edgeLength;          // User specified number of sites per edge of grid
 private boolean[][] openSt;      // 2-D array for open Status of sites
 private int topVirtual;          // Virtual site connected to all elements in the first row (e.g. 0)
 private int botVirtual;          // Virtual site connected to all elements in the last row (e.g. n*n - 1)
 private WeightedQuickUnionUF uf; // union-find to check for percolation
 private WeightedQuickUnionUF uf1; // union-find to check whether full
 private int numOpen = 0;         // number of open sites
 
 public Percolation(int n) {        // initialize by assigning every element to its own component
  if (n <= 0) {
    throw new IllegalArgumentException("Specified edge length " + n + " must be > 0");
  }

  if (n > (Math.sqrt(Integer.MAX_VALUE / 4))) {
    throw new IllegalArgumentException("Specified edge length " + n + " must not be so big that it can crash this program!");
  }

  int gridSize = n*n;
  uf = new WeightedQuickUnionUF(gridSize);
  uf1 = new WeightedQuickUnionUF(gridSize);
  edgeLength = n;
  topVirtual = 0;
  botVirtual = gridSize - 1;
  openSt = new boolean[n][n]; // default false, block all initially

  // top row
  for (int i = 0; i < edgeLength; i++) {
    uf.union(topVirtual, i);
    uf1.union(topVirtual, i);
  }

  // bottom row
  for (int i = gridSize - edgeLength; i < gridSize; i++) {
    uf.union(botVirtual, i);
  }
 }

 // open site (row, col) if it is not open already
 public void open(int row, int col) {
  row = convertIdx(row);
  col = convertIdx(col);
  validateGridIdx(row);
  validateGridIdx(col);

  if (openSt[row][col]) {
    return;
  }

  openSt[row][col] = true;
  numOpen++;

  int site1d = convert2dto1d(row, col);
  boolean leftOpen = false, topOpen = false, rightOpen = false, bottomOpen = false;

  for (int i = 0; i < 4; i++) {
    try {
      switch (i) {
        case 0: 
            if (openSt[row][col - 1]) {
              int left1d = convert2dto1d(row, col - 1);
              uf.union(left1d, site1d);
              uf1.union(left1d, site1d);
            }
            break;
        case 1:
            if (openSt[row - 1][col]) {
              int top1d = convert2dto1d(row - 1, col);
              uf.union(top1d, site1d);
              uf1.union(top1d, site1d);
            }
            break;               
        case 2:
            if (openSt[row][col + 1]) {
              int right1d = convert2dto1d(row, col + 1);
              uf.union(right1d, site1d);
              uf1.union(right1d, site1d);
            }
            break;
        case 3:
            if (openSt[row + 1][col]) {
              int bottom1d = convert2dto1d(row + 1, col);
              uf.union(bottom1d, site1d);
              uf1.union(bottom1d, site1d);
            }
            break;
        default: break;
      }
    }
    catch (IndexOutOfBoundsException e) { // indices not in 2d array
      continue;
    }
  }
 }

 // is site (row, col) open?
 public boolean isOpen(int row, int col) {
  row = convertIdx(row);
  col = convertIdx(col);
  validateGridIdx(row);
  validateGridIdx(col);

  return openSt[row][col];
 }

 // is site (row, col) full?
 public boolean isFull(int row, int col) {
  row = convertIdx(row);
  col = convertIdx(col);
  validateGridIdx(row);
  validateGridIdx(col);

  return openSt[row][col] && uf1.connected(convert2dto1d(row, col), topVirtual);
 }

 // number of open sites
 public int numberOfOpenSites() {
  return numOpen;
 }

 // does the system percolate?
 public boolean percolates() {
  if (edgeLength == 1) {
    return openSt[0][0] && uf.connected(topVirtual, botVirtual);
  }
  else {
    return uf.connected(topVirtual, botVirtual);
  }
 }

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

 // sample test data is 1-indexed rather than 0-indexed. Account for this
 private int convertIdx(int idx) {
  return idx - 1;
 }

 // make sure that p is a valid index for 2-D grid
 private void validateGridIdx(int p) {
  int n = edgeLength;
  if (p < 0 || p >= n) {
    throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n-1));
  }
 }

 public static void main(String[] args) {
  int n = 4;
  Percolation perc = new Percolation(n);

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      perc.open(i, j);
      StdOut.println("Open Test: " + perc.isOpen(i, j) + " for n = (" + i + "," + j + ")");
      StdOut.println("Full Test: " + perc.isFull(i, j) + " for n = (" + i + "," + j + ")");
      StdOut.println("Percolation Test: " + perc.percolates() + " after n = (" + i + "," + j + ")");
    }
  }
  StdOut.println(perc.numberOfOpenSites() + " total sites open.");

  Percolation percMin = new Percolation(n);
  percMin.open(1, 1);
  percMin.open(2, 2);
  percMin.open(3, 2);
  StdOut.println("Percolation Test before last row site opened: " + percMin.percolates());
  percMin.open(4, 2);
  StdOut.println("Percolation Test after last row site opened: " + percMin.percolates());
  percMin.open(2, 1);
  StdOut.println("Percolation Test after connection from top to bottom established: " + percMin.percolates());

  try {
    percMin.open(-1, 0);
  }
  catch (IndexOutOfBoundsException e) {
    StdOut.println("Error Caught! " + e.getMessage());
  }
  try {
    percMin.isOpen(-1, 0);
  }
  catch (IndexOutOfBoundsException e) {
    StdOut.println("Error Caught! " + e.getMessage());
  }
  try {
    Percolation percNeg = new Percolation(-1);
  }
  catch (IllegalArgumentException e) {
    StdOut.println("Error Caught! " + e.getMessage());
  }
  try {
    Percolation percBig = new Percolation(2397*5555);
  }
  catch (IllegalArgumentException e) {
    StdOut.println("Error Caught! " + e.getMessage());
  }
 }

}