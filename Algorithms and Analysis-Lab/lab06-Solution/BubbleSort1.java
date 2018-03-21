import java.util.ArrayList;

/**
 * Standard, no early termination, bubble sort.
 * 
 * @author jefcha
 */
public class BubbleSort1 extends SortAlgorithm
{
    /**
     * Sorts the input array.
     * 
     * @param array Array to be sorted.
     */
    public void sort(int[] array) {
        
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                // check if we need to swap
                if (array[j] > array[j+1]) {
                    Integer temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        
    } // end of sort()
    
} // end of class BubbleSort1
