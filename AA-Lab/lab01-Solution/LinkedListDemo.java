import java.util.Scanner;


/**
 * Demonstration class for linked list.
 * Provides command parsing and calling appropriate methods for the linked list.
 * DO NOT CHANGE.
 * 
 *  @author jkcchan
 */
public class LinkedListDemo
{

    /** 
     * Print out help menu.
     */
    public static void printHelp()
    {
        System.out.println("Available commands:\n"
                    + "    add <value>\n"
                    + "    addByIndex <index> <value>\n"
                    + "    remove <value>\n"
                    + "    removeByIndex <index>\n"
                    + "    search <value>\n"
                    + "    min\n"
                    + "    max\n"
                    + "    print\n"
                    + "    reversePrint\n"
                    + "    quit|end\n"
                    );
    } 


    /** 
     * Get next command/operation from file.
     * 
     * @param list Reference to list which the commands will be executed on.
     */ 
    public static void processOperations(MyList list)
    {
        Scanner is = new Scanner(System.in);

        while( is.hasNext() ) {
            String command = is.next();

            if( command.compareTo("add") == 0 ) {
                if (is.hasNextInt()) {
                    int value = is.nextInt();
                    list.add(value);
                }
            }
            else if( command.compareTo("addByIndex") == 0 ) {
                if (is.hasNextInt()) {
                    int index = is.nextInt();
                    if (is.hasNextInt()) {
                    	int value = is.nextInt();
                    	try {
                    		list.add(index, value);
                    	} catch(IndexOutOfBoundsException e) {
                    		System.err.println(e.getMessage());
                    	}
                    }
                }
            }            
            else if( command.compareTo("remove") == 0  ) {
                if (is.hasNextInt()) {
                    int value = is.nextInt();
                    list.remove(value);
                }
            }
            else if( command.compareTo("removeByIndex") == 0  ) {
                if (is.hasNextInt()) {
                    int index = is.nextInt();
                    try {
                    	list.remove(index, false);
                    } catch(IndexOutOfBoundsException e) {
                		System.err.println(e.getMessage());
                	}
                }
            }            
            else if( command.compareTo("search") == 0  ) {
                if (is.hasNextInt()) {
                    int value = is.nextInt();
                    if (list.search(value)) {
                    	System.out.println("Key found!");
                    }
                    else {
                    	System.out.println("Key not found.");
                    }
                }
            }
            else if( command.compareTo("min") == 0  ) {
                int minKey = list.min();
                System.out.println("Min key = " + minKey);
            }
            else if( command.compareTo("max") == 0 ) {
                int maxKey = list.max();
                System.out.println("Max key = " + maxKey);        	
            }
            else if( command.compareTo("print") == 0  ) {
                list.print();
            }
            else if( command.compareTo("reversePrint") == 0  ) {
                list.reversePrint();
            }
            else if( command.compareTo("quit") == 0 || command.compareTo("end") == 0 ) {
                break;
            }
            else {
                System.err.println("Did not recognize command. Enter valid command.");
                printHelp();
            }
        }
    } // end of processOperations() 



    /**
     * Main method.
     */
    public static void main(String[] args) {
        
        if (args.length != 1) {
            printHelp();
            System.exit(1);
        }

        // determine which list to construct
        MyList list = null;
        if (args[0].compareTo("single") == 0) {
            list = new SimpleLinkedList();
        }
        else if (args[0].compareTo("double") == 0) {
            list = new DoubleLinkedList();
        }
        else {
            printHelp();
            System.exit(1);
        }

        // process the commands
        System.out.println("Enter command:");
        processOperations(list);
    } // end of main()
    
} // end of class LinkedListDemo