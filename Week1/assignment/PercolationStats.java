import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
  // perform trials independent experiments on an n-by-n grid
  public PercolationStats(int n, int trials) {

  }

  // sample mean of percolation threshold
  public double mean() {
    return 0;
  }
  // sample standard deviation of percolation threshold
  public double stddev() {
    return 0;
  }
  // low  endpoint of 95% confidence interval
  public double confidenceLo() {
    return 0;
  }
  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return 0;
  }

  public static void main(String[] args) {
    if (args.length > 2) {
      throw new IllegalArgumentException("Number of arguments (" + args.length + ") is greater than 2.");
    }
    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);
    PercolationStats ps = new PercolationStats(n, trials);

    StdOut.println("mean                    = " + ps.mean());
    StdOut.println("stddev                  = " + ps.stddev());
    StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
  }
  
}