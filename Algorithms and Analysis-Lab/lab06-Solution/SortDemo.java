import java.io.*;
import java.util.*;

/**
 * Demonstrate the different sorting algorithms.
 * 
 * @author jefcha
 */
public class SortDemo
{
    protected static final String progName = "SortDemo";

    /** Different modes that program can run. */
    public enum Mode {
      	BUB,
      	QUICK,
      	COCKTAIL,
      	MERGE
    }

    
    /**
     * Print usage information.
     * 
     * @param progName Program name.
     */
    protected static void printUsage(String progName) {
      	System.err.println("USAGE: " + progName + " [sort method] [input file]");
      	System.err.println("  sort methods [bubble, quick, merge, cocktail]");
      	System.err.println("EXAMPLE: " + progName + " quick random.txt");
    } // end of printUsage()


    /**
     * Main method.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            // not enough arguments
            if (args.length != 2) {
                printUsage(progName);
                System.exit(1);
            }
            
            // sorting algorithm to be used
            String algorithmUsed = args[0];
            // input file to be sorted
            String fileName = args[1];
            
            
            File inFile = new File(fileName);
            Scanner in = new Scanner(inFile);

            // buffer to read in data
            ArrayList<Integer> buffer = new ArrayList<Integer>();

            while (in.hasNextInt()) {
                buffer.add(new Integer(in.nextInt()));
            }

            // create array of int
            int[] array = new int[buffer.size()];
            // copy buffer to array
            Iterator bit = buffer.iterator();
            int j = 0;
            while (bit.hasNext()) {
                array[j] = (Integer) bit.next();
                j++;
            }
            
            
            // buffer memory no longer needed
            buffer = null;

            // figure out with sorting algorithm we are using
            SortAlgorithm sortAlgor = null;
            switch(algorithmUsed) {
                case "bubble":
                    sortAlgor = new BubbleSort1();
                    // sort_bubble(dataset->data,dataset->items);
                    break;
                case "cocktail":
                    sortAlgor = new CocktailSort();
                    // sort_cocktail(dataset->data,dataset->items);
                    break;
                case "merge":
                    sortAlgor = new MergeSort();
                    // sort_merge(dataset->data,dataset->items);
                    break;
                case "quick":
                    sortAlgor = new QuickSort();
                    // sort_quick(dataset->data,dataset->items);
                    break;
                default:
                    System.err.println("Error: " + algorithmUsed + "is invalid.");
                    array = null;
                    printUsage(progName);
                    System.exit(1);
            }

            long startTime = System.nanoTime();
            
            // do the sorting
            sortAlgor.sort(array);
            
            long endTime = System.nanoTime();
            
            

            // print out sorted array
            for (int i = 0; i < array.length; i++) {
            	System.out.println(array[i]);
            }
            
            double timeElapsed = (endTime - startTime) / Math.pow(10, 9);
            System.out.println("Time elapsed (secs): " + timeElapsed);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            printUsage(progName);
        }

    } // end of main()
    
} // end of class SortDemo



///**
// * Inner class to handle the parameters for the different sorting algorithms.
// */
//class Parameters {
//    public String mInputFile;
//    public SortDemo.Mode mMode;
//    public boolean mVerbose;
//
//    public Parameters() {
//        // set of default values
//        mInputFile = null;
//        mMode = SortDemo.Mode.BUB;
//        mVerbose = false;
//    }
//
//    /**
//     * Constructor that parses command line arguments.
//     */
//    public Parameters(String[] args, String progName) 
//    	throws Exception
//    {
//        // initialise all values to defaults
//        this();
//
//        if (args.length <= 1) {
//        	throw new Exception("Invalid number of args");
//      	}
//
//        String options = "i:m:v";
//        Getopt g = new Getopt(progName, args, options);
//
//        int opt ;
//
//      	while ((opt = g.getopt()) != -1) {
//            switch (opt) {
//            		case 'i':
//                    mInputFile = new String(g.getOptarg());
//                    break;
//            		case 'm':
//                        if (g.getOptarg().compareTo("bubble") == 0) {
//                      		mMode = SortDemo.Mode.BUB;
//                        } else if (g.getOptarg().compareTo("qsort") == 0) {
//                      		mMode = SortDemo.Mode.QSORT;
//                        } else if (g.getOptarg().compareTo("cocktail") == 0) {
//                      		mMode = SortDemo.Mode.COCKTAIL;
//                        } else if (g.getOptarg().compareTo("merge") == 0) {
//                      		mMode = SortDemo.Mode.MERGE;
//                        } else if (g.getOptarg().compareTo("quick") == 0) {
//                      		mMode = SortDemo.Mode.QUICK;
//                        } else {
//                      		System.err.println("ERROR: mode <" + g.getOptarg() +
//                                "> unknown!");
//                            // TODO: should throw exception
//                      		System.exit(1);
//                		}
//            			break;
//            		case 'v':
//                		mVerbose = true;
//                		break;
//            		default:
//                        // TODO: throw exception
//              	// 		print_usage(ars[0]);
//              	// 		exit(EXIT_FAILURE);
//        		}
//      	}
//
//
//    }
//} // end of inner class Parameters

