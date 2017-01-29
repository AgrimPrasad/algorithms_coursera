import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.Stopwatch;

public class ThreeSum {
    //Do not instantiate
    private ThreeSum() { }

    // Print all triplets of integers which sum to 0
    public static void printAll(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        StdOut.println(a[i] + " " + a[j] + " " + a[k]);
                    }
                }
            }
        }
    }

    // Count and return all triplets of integers which sum to 0
    public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    

    public static void main(String[] args) {
        String filenames[] = {
                              // "data/1Mints.txt", 
                              // "data/32Kints.txt", 
                              // "data/16Kints.txt",
                              // "data/8Kints.txt", 
                              // "data/4Kints.txt", 
                              "data/2Kints.txt",
                              "data/1Kints.txt"
                             };
        
        for (String filename: filenames) {
            Stopwatch timer = new Stopwatch();
            In in = new In(filename);
            int[] n = in.readAllInts();
        
            int count = count(n);
            StdOut.println("elapsed time = " + timer.elapsedTime() + " seconds.");
            StdOut.println("In total, counted " + count + " triplets for " + filename);
        
            in.close();
        }            
    }
}
