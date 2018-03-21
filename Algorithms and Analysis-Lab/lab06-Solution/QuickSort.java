
/**
 * Implementation of Quicksort, same as the lecture notes.
 * Use first element as pivot.
 * 
 * @author jefcha
 */
public class QuickSort extends SortAlgorithm
{
	
	/**
	 * Sort array.
	 * 
	 * @param array Array to be sorted.
	 */
    public void sort(int[] array) {
    	sort(array, 0, array.length-1);
    } // end of sort()
    
    
    /**
     * In-place sort, used leftIndex and rightIndex to specify the partition in question.
     * 
     * @param array Array to be sorted.
     * @param leftIndex The leftmost position to sort from (inclusive).
     * @param leftIndex The rightmost position to sort to (inclusive).
     */
    protected void sort(int[] array, int leftIndex, int rightIndex) {
	// IMPLEMENT ME
    } // end of sort()


    /**
     * Quicksort partition function.  Swap and divide into two partitions.
     * 
     * @param array Array to be sorted.
     * @param leftIndex The leftmost position to sort from (inclusive).
     * @param leftIndex The rightmost position to sort to (inclusive).
     *
     * @return Index of array where the pivot is placed into.
     */
    protected int partition(int[] array, int leftIndex, int rightIndex) {
	// IMPLEMENT ME
	
	// DUMMY return value
	return -1;
    } // end of partition()
    
    

} // end of class QuickSort
