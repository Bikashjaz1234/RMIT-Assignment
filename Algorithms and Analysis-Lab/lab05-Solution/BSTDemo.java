import java.io.DataInputStream;
import java.util.Scanner;


/**
 * Process and demonstrate BST.
 * 
 * @author jkccha
 */
public class BSTDemo
{
	/** Operation constants. */
	public static final int INVALID = -1;
	public static final int INSERT = 1;
	public static final int DELETE = 2;
	public static final int SEARCH = 3;
	public static final int MIN = 4;
	public static final int MAX = 5;
	public static final int HEIGHT = 6;
	public static final int INORDER = 7;
	public static final int PREORDER = 8;
	public static final int POSTORDER = 9;
	public static final int ASCII_PRINT = 10;
	public static final int QUIT = 11;
	
	
	/** 
	 * Print out help menu.
	 */
	public static void printHelp()
	{
	    System.err.println("Available commands:\n"
	    		+ "    insert <number>\n"
	    		+ "    min\n"
	    		+ "    max\n"
	    		+ "    height\n"
	    		+ "    inorder\n"
	    		+ "    preorder\n"
	    		+ "    postorder\n"
	    		+ "    print_ascii\n"
	    		+ "    quit|end\n"
	    		);
	} 
	
	
	/** 
	 * Get next command/operation from file.
	 */ 
	public static void processOperations(BST tree)
	{
	    Scanner is = new Scanner(System.in);
	    int op = INVALID;
		
	    while( is.hasNext() ) {
	        String command = is.next();
	        
            if( command.compareTo("insert") == 0 ) {
            	if (is.hasNextInt()) {
            		int key = is.nextInt();
            		op = INSERT;
            		tree.insert(key);
            	}
            }
            else if( command.compareTo("min") == 0  ) {
            	op = MIN;
            	int minKey = tree.min();
            	if (minKey != BST.EMPTY_TREE) {
            		System.out.println("Min key = " + minKey);
            	}
            	else {
            		System.out.println("Tree is empty.");
            	}
            }
            else if( command.compareTo("max") == 0 ) {
            	op = MAX;
            	int maxKey = tree.max();
            	if (maxKey != BST.EMPTY_TREE) {
            		System.out.println("Max key = " + maxKey);
            	}
            	else {
            		System.out.println("Tree is empty.");
            	}            	
            }
            else if( command.compareTo("height") == 0  ) {
            	op = HEIGHT;
            	int height = tree.height();
            	System.out.println("Height = " + height);
            }
            else if( command.compareTo("inorder") == 0  ) {
            	op = INORDER;
            	tree.inorder();
            }
            else if( command.compareTo("preorder") == 0  ) {
            	op = PREORDER;
            	tree.preorder();
            }
            else if( command.compareTo("postorder") == 0  ) {
            	op = POSTORDER;
            	tree.postorder();
            }
            else if( command.compareTo("print_ascii") == 0  ) {
            	op = ASCII_PRINT;
                tree.asciiPrint();
            }
            else if( command.compareTo("quit") == 0 || command.compareTo("end") == 0 ) {
            	op = QUIT;
            	break;
            }
	        else
	        {
	        	
	        	System.err.println("Did not recognize command " + command + ". Enter valid command.");
                printHelp();
                op = INVALID;
	        }
	    }
	} /* end of processOperations() */	
	
	
	
	public static void main(String[] args) {
		BST tree = new BST();
		
		processOperations(tree);
	}
}