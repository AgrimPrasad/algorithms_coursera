import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.Stopwatch;

public class SocialNetworkConnectivity { 
       // put your code here
        WeightedQuickUnionUF uf;

    public SocialNetworkConnectivity(int n) {        
        if (n <= 0 || n > (Integer.MAX_VALUE)) {
            throw new IllegalArgumentException(n + " arg is out of bounds");
        }
        // put your code here
        uf = new WeightedQuickUnionUF(n);

    }
    
    public void formFriendship(int i, int j) {
        // put your code here
        uf.union(i, j);
    }
    
    public boolean allConnected() {
        return uf.count() == 1;
    }
    

    public static void main(String[] args) {
        String filenames[] = {"data/interview/socialnetworkconnectivity/input_1000_450000_250000.txt", 
                              "data/interview/socialnetworkconnectivity/input_2000_1800000_750000.txt", 
                              "data/interview/socialnetworkconnectivity/input_4000_7200000_2250000.txt"
                             };
        
        for (String filename: filenames) {
            Stopwatch timer = new Stopwatch();
            
            In in = new In(filename);

            int n = in.readInt();
       
            SocialNetworkConnectivity snc = new SocialNetworkConnectivity(n);
        
            int line = 0;
            while (!in.isEmpty()) {            
                int timestamp = in.readInt();
                int i = in.readInt();
                int j = in.readInt();        
                line++;
            
                snc.formFriendship(i, j);
                if (snc.allConnected()) {
                    StdOut.println("file: " + filename 
                                 + "\ttimestamp: " + timestamp 
                                 + "  line: " + line 
                                 + "  (elapsed time: " + timer.elapsedTime() + ")");
                    break;
                }
            }
        
            in.close();
        }            
    }
}
