import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private double mean = 0;
  private double stddev = 0;
  private double confidenceLo = 0;
  private double confidenceHi = 0;
  private double[] pThresholds;

  // perform trials independent experiments on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException("Specified edge length " + n + " and trials " + trials + " must be > 0");
    }

    if (trials > (Integer.MAX_VALUE / 4)) {
      throw new IllegalArgumentException("Specified trials " + trials + " must not be so big that it can crash this program!");
    }

    double gridSize = n*n;
    pThresholds = new double[trials];

    for (int i = 0; i < trials; i++) {
      Percolation uf = new Percolation(n);
      while (!uf.percolates()) {
        int leftRand = StdRandom.uniform(1, n + 1); // random integer uniformly in [a, b)
        int rightRand = StdRandom.uniform(1, n + 1);
        uf.open(leftRand, rightRand);
      }
      pThresholds[i] = (uf.numberOfOpenSites() / gridSize);
    }

    mean = StdStats.mean(pThresholds);
    stddev = StdStats.stddev(pThresholds);

    double confidenceFactor = (1.96*stddev)/Math.sqrt(trials);
    confidenceLo = mean - confidenceFactor;
    confidenceHi = mean + confidenceFactor;
  }

  // sample mean of percolation threshold
  public double mean() {
    return mean;
  }
  // sample standard deviation of percolation threshold
  public double stddev() {
    return stddev;
  }
  // low  endpoint of 95% confidence interval
  public double confidenceLo() {
    return confidenceLo;
  }
  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return confidenceHi;
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