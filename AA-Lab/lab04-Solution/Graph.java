import java.io.*;
import java.util.*;
import java.lang.reflect.Array;


/**
 *  The <tt>Graph</tt> class represents an undirected graph of vertices
 *  named 0 through <em>V</em> - 1.
 *  It supports the following two primary operations: add an edge to the graph,
 *  iterate over all of the vertices adjacent to a vertex. It also provides
 *  methods for returning the number of vertices <em>V</em> and the number
 *  of edges <em>E</em>. Parallel edges and self-loops are permitted.
 *  <p>
 *  This implementation uses an adjacency-lists representation, which
 *  is a vertex-indexed array of {@link Bag} objects.
 *  All operations take constant time (in the worst case) except
 *  iterating over the vertices adjacent to a given vertex, which takes
 *  time proportional to the number of such vertices.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/41graph">Section 4.1</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Edited by Jeffrey Chan, 02/2016
 */
public class Graph {

	/** Number of vertices (nodes) in the graph. */
    private int mVertNum;
    /** Number of edges in the graph. */
    private int mEdgeNum;
    /* Adjacency list representation for graph. */
    private ArrayList<Set<Integer> > mAdj;

    
    /**
     * Initializes an empty graph with <tt>V</tt> vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  vertNum number of vertices
     * @throws IllegalArgumentException if <tt>V</tt> < 0
     */
    public Graph(int vertNum) throws IllegalArgumentException {
        if (vertNum < 0) {
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        }

        generateAdjList(vertNum);
    } // end of Graph()


    private void generateAdjList(int vertNum) {
        mVertNum = vertNum;
        mEdgeNum = 0;

        // We need to suppress warning.  We can't directly create
        // TreeSet<Integer>.
        // @SuppressWarnings("unchecked")
        mAdj = new ArrayList<Set<Integer> >(vertNum);
        for (int v = 0; v < vertNum; v++) {
            // for specific implementation, we use TreeSet to implement our
            // set semantics for adjacency lists
            mAdj.add(new TreeSet<Integer>());
        }
    } // end of generateAdjList()


    /**
     * Initializes a new graph that is a deep copy of <tt>G</tt>.
     *
     * @param  G the graph to copy
     */
    public Graph(Graph G) {
        this(G.vertNum());
        this.mEdgeNum = G.edgeNum();
        for (int v = 0; v < G.vertNum(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> stack = new Stack<Integer>();
            for (int w : G.neighbours(v)) {
                stack.push(w);
            }
            for (int w : stack) {
                mAdj.get(v).add(w);
            }
        }
    } // end of Graph()


    public Graph(InputStream ins) {
        Scanner input = new Scanner(ins);

        // read in number of vertices
        if (input.hasNextInt()) {
            generateAdjList(input.nextInt());

            while(input.hasNextInt()) {
                int s = input.nextInt();
                if (input.hasNextInt()) {
                    int t = input.nextInt();
                    addEdge(s, t);
                }
                else {
                    throw new IllegalArgumentException("Misformed edge.");
                }
            }
        }
        else {
            throw new IllegalArgumentException("Number of vertices must be specified.");
        }
    } // end of Graph()


    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int vertNum() {
        return mVertNum;
    } // end of vertNum()

    
    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int edgeNum() {
        return mEdgeNum;
    } // end of edgeNum()

    
    /**
     * @throw an IndexOutOfBoundsException unless 0 <= v < V
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= mVertNum)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (mVertNum-1));
    } // end of validateVertex()

    
    /**
     * Adds the undirected edge v-w to this graph.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IndexOutOfBoundsException unless both 0 <= v < V and 0 <= w < V
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        mAdj.get(v).add(w);
        mAdj.get(w).add(v);
        mEdgeNum++;
    } // end of addEdge()


    /**
     * Returns the vertices adjacent to vertex <tt>v</tt>.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex <tt>v</tt>, as an iterable
     * @throws IndexOutOfBoundsException unless 0 <= v < V
     */
    public Iterable<Integer> neighbours(int v) {
        validateVertex(v);
        return mAdj.get(v);
    } // end of neighbours()

    
    /**
     * Returns the degree of vertex <tt>v</tt>.
     *
     * @param  v the vertex
     * @return the degree of vertex <tt>v</tt>
     * @throws IndexOutOfBoundsException unless 0 <= v < V
     */
    public int degree(int v) {
        validateVertex(v);
        return mAdj.get(v).size();
    } // end of degree()


    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(mVertNum + " vertices, " + mEdgeNum + " edges " + "\n");
        for (int v = 0; v < mVertNum; v++) {
            s.append(v + ": ");
            for (int w : mAdj.get(v)) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    } // end of toString()


    /**
     * Unit tests the <tt>Graph</tt> data type.
     */
    public static void main(String[] args) {
            Scanner in = new Scanner(System.in);

            if (in.hasNextInt()) {
                int vertNum = in.nextInt();
                Graph g = new Graph(vertNum);

                while(in.hasNextInt()) {
                    int v = in.nextInt();
                    if (in.hasNextInt()) {
                        int w = in.nextInt();
                        g.addEdge(v, w);
                    }
                    else {
                        throw new IllegalArgumentException("Misformed edge.");
                    }
                }
                System.out.println(g);
            }

    } // end of main()

} // end of class Graph
