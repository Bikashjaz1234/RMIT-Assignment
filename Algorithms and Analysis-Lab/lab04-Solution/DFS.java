import java.util.*;
import java.io.*;

/**
 * Compute depth first search traversal on a graph.
 * 
 *  @author jefcha
 */
public class DFS 
{
    /** Name of the class. */
    private static final String progName = "DFS";       

    
    /** 
     * Depth first search traversal of input graph g from seed vertex s.
     * 
     * @param g Input graph.
     * @param s Seed vertex to start traversal from.
     * 
     * @returns ArrayList of vertices, stored in the order they were visited in 
     *      DFS traversal.
     */
    public static ArrayList<Integer> traverse(Graph g, int s) {
        boolean[] marked = new boolean[g.vertNum()];
    	ArrayList<Integer> traversalOrder = new ArrayList<Integer>();
    	
    	for (int i = 0; i < marked.length; i++) {
    		marked[i] = false;
    	}        
        
        
        marked[s] = true;
        traversalOrder.add(s);
        
        for (int w : g.neighbours(s)) {
            if (!marked[w]) {
                dfs(g, w, marked, traversalOrder);
            }
        }
        
        return traversalOrder;
    } // end of traverse()

    
    /**
     * Recursive DFS method, that implements DFS visitation semantics.
     * 
     * @param g Input graph.
     * @param v Current vertex visited.
     * @param marked Array of booleans, that indicate whether a vertex has been
     *      visited yet.
     * @param traversalOrder ArrayList of vertices, stored in the order they were visited so far in the
     *      DFS traversal.
     */
    protected static void dfs(Graph g, int v, boolean[] marked, ArrayList<Integer> traversalOrder) {
        marked[v] = true;
        traversalOrder.add(v);
        for (int w : g.neighbours(v)) {
            if (!marked[w]) {
                dfs(g, w, marked, traversalOrder);
            }
        }    	
    } // end of dfs()

    
    /**
     * Print out usage messge and exits from program.
     */    
    protected static void usage() {
    	System.err.println(DFS.progName + ": <input graph file> <starting vertex>");
    	System.exit(1);
    }
    
    
    /**
     * Perform DFS traversal for input graph.
     */
    public static void main(String[] args) {
    	if (args.length != 2) {
    		usage();
    	}
    	
    	try {
            // input graph
            InputStream in = new FileInputStream(args[0]);
            Graph g = new Graph(in);
        
            // seed vertex
            int s = Integer.parseInt(args[1]);
    		
            // perform DFS
            ArrayList<Integer> traversalOrder = DFS.traverse(g, s);

            // print out the traversal order of the BFS
            Iterator<Integer> it = traversalOrder.iterator();
            while (it.hasNext()) {
    		System.out.print(it.next() + " ");
            }
            
            System.out.println("");
    	} 
    	catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
    	}
    } // end of main()

} // end of class DFS