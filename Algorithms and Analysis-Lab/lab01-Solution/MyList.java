
/**
 * Interface for the two lists implementation we will develop in this lab.  
 * Note, as we do not seek to implement all the functionality in the built-in
 * List interface, we have develop our own interface.
 * 
 * @author jkcchan
 */
public interface MyList
{
	
    /**
     * Add a new value to the start of the list.
     * 
     * @param newValue Value to add to list.
     */
    public abstract void add(int newValue);
    
    
    /**
     * Add value (and corresponding node) at position 'index'.  Indices start at 0.
     * 
     * @param index Position in list to add new value to.
     * @param newValue Value to add to list.
     * 
     * @throws IndexOutOfBoundsException In index are out of bounds.
     */
    public abstract void add(int index, int newValue) throws IndexOutOfBoundsException;
    
    
    /**
     * Returns the value stored in node at position 'index' of list.
     *  
     * @param index Position in list to get new value for.
     * @return Value of element at specified position in list.
     * 
     * @throws IndexOutOfBoundsException In index are out of bounds.
     */
    public abstract int get(int index) throws IndexOutOfBoundsException;
    
    
    /**
     * Returns the value stored in node at position 'index' of list.
     * 
     * @param value Value to search for.
     * @return True if value is in list, otherwise false.
     */
    public abstract boolean search(int value);
    
    
    /**
     * Delete given value from list (delete first instance found).
     *   
     * @param value Value to remove.
     * @return True if deletion was successful, otherwise false.
     */
    public abstract boolean remove(int value); 
    
    
    /**
     * Delete value (and corresponding node) at position 'index'.  Indices start at 0.
     * 
     * @param index Position in list to get new value for.
     * @param dummy Dummy variable, serves no use apart from distinguishing overloaded methods.
     * @return Value of node that was deleted.
     */
    public abstract int remove(int index, boolean dummy) throws IndexOutOfBoundsException;
    
    
    /**
     * Returns the minimum value in the list.
     * 
     * @return Minimum value in the list.
     * 
     * @throws IndexOutOfBoundsException In index are out of bounds.
     */
    public abstract int min() throws IndexOutOfBoundsException;
    
    
    /**
     * Returns the maximum value in the list.
     * 
     * @return Maximum value in the list.
     * 
     * @throws IndexOutOfBoundsException In index are out of bounds.
     */
    public abstract int max() throws IndexOutOfBoundsException;
    
    
    /**
     * Print the list in head to tail.
     */
    public abstract void print();
    
    
    /**
     * Print the list from tail to head.
     */
    public abstract void reversePrint();
    
} // end of interface MyList