//package au.edu.rmit.csit.aa;

import java.util.Scanner;
import java.util.Stack;


/**
 * Singly linked list.
 * 
 * @author jkcchan
 */
public class SimpleLinkedList implements MyList
{
	/** Reference to head node. */
    protected Node mHead;

    /** Length of list. */
    protected int mLength;


    public SimpleLinkedList() {
        mHead = null; 
        mLength = 0;
    } // end of SimpleList()


    /**
     * Add a new value to the start of the list.
     * 
     * @param newValue Value to add to list.
     */
    public void add(int newValue) {
        Node newNode = new Node(newValue);
             
        // If head is empty, then list is empty and head reference need to be initialised.
        if (mHead == null) {
            mHead = newNode;
        }
        // otherwise, add node to the head of list.
        else {
            newNode.setNext(mHead);
            mHead = newNode;
            
        }
        
        mLength++;
    } // end of add()


    /**
     * Add value (and corresponding node) at position 'index'.  Indices start at 0.
     * 
     * @param index Position in list to add new value to.
     * @param newValue Value to add to list.
     * 
     * @throws IndexOutOfBoundsException In index are out of bounds.
     */
    public void add(int index, int newValue) throws IndexOutOfBoundsException {
        if (index >= mLength || index < 0) {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }

        Node newNode = new Node(newValue); 

        if (mHead == null) {			
            mHead = newNode;
        }
        // list is not empty
        else {
            Node currNode = mHead;
            for (int i = 0; i < index-1; ++i) {
                currNode = currNode.getNext();
            }

            newNode.setNext(currNode.getNext());
            currNode.setNext(newNode);            
        }

        mLength += 1;
    } // end of add()


    /**
     * Returns the value stored in node at position 'index' of list.
     *  
     * @param index Position in list to get new value for.
     * @return Value of element at specified position in list.
     * 
     * @throws IndexOutOfBoundsException In index are out of bounds.
     */
    public int get(int index) throws IndexOutOfBoundsException {
        if (index >= mLength || index < 0) {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }

        Node currNode = mHead;
        for (int i = 0; i < index; ++i) {
            currNode = currNode.getNext();
        }

        return currNode.getValue();
    } // end of get()
    
    
    /**
     * Returns the value stored in node at position 'index' of list.
     * 
     * @param value Value to search for.
     * @return True if value is in list, otherwise false.
     */
    public boolean search(int value) {
        Node currNode = mHead;
        for (int i = 0; i < mLength; ++i) {
        	if (currNode.getValue() == value) {
        		return true;
        	}
            currNode = currNode.getNext();
        }

        return false;
    } // end of search()
    


    /**
     * Delete given value from list (delete first instance found).
     *   
     * @param value Value to remove.
     * @return True if deletion was successful, otherwise false.
     */
    public boolean remove(int value) {
        // YOUR IMPLEMENTATION
    	if (mLength == 0) {
    		return false;
    	}
    	
    	
        Node currNode = mHead;
        Node prevNode = null;

        // check if value is head node
        if (currNode.getValue() == value) {
            mHead = currNode.getNext();
            mLength--;
            return true;
        }

        prevNode = currNode;
        currNode = currNode.getNext();

        while (currNode != null) {
            if (currNode.getValue() == value) {
                prevNode.setNext(currNode.getNext());
                currNode = null;
                mLength--;
                return true;
            }
            prevNode = currNode;
            currNode = currNode.getNext();
        }		


        return false;
    } // end of delete()


    /**
     * Delete value (and corresponding node) at position 'index'.  Indices start at 0.
     * 
     * @param index Position in list to get new value for.
     * @param dummy Dummy variable, serves no use apart from distinguishing overloaded methods.
     * @return Value of node that was deleted.
     */
    public int remove(int index, boolean dummy) throws IndexOutOfBoundsException {
        // YOUR IMPLEMENTATION
        if (index >= mLength || index < 0) {
            throw new IndexOutOfBoundsException("Supplied index is invalid.");
        }

        Node currNode = mHead;
        Node prevNode = null;
        
        int value;
        // deleting head
        if (index == 0) {
            value = currNode.getValue();
            mHead = currNode.getNext();
        }
        else {
            for (int i = 0; i < index; ++i) {
                prevNode = currNode;
                currNode = currNode.getNext();
            }

            value = currNode.getValue();
            prevNode.setNext(currNode.getNext());
            currNode = null;
        }

        mLength--;
        
        return value;
    } // end of delete()
	
	
    /**
     * Returns the minimum value in the list.
     * 
     * @return Minimum value in the list.
     * 
     * @throws IndexOutOfBoundsException In index are out of bounds.
     */
    public int min() throws IllegalStateException {
        if (mLength == 0) {
            throw new IllegalStateException("Min is not defined for an empty list.");
        }
        
        // traverse list, looking for the minimum valued element
        int minValue = mHead.getValue();
        Node currNode = mHead.getNext();
        
        while (currNode != null) {
            if (currNode.getValue() < minValue) {
                minValue = currNode.getValue();
            }
            currNode = currNode.getNext();
        }
        
        return minValue;
    } // end of min()
    
    
    /**
     * Returns the maximum value in the list.
     * 
     * @return Maximum value in the list.
     * 
     * @throws IndexOutOfBoundsException In index are out of bounds.
     */
    public int max() throws IndexOutOfBoundsException {
        if (mLength == 0) {
            throw new IllegalStateException("Min is not defined for an empty list.");
        }
        
        // traverse list, looking for the minimum valued element
        int maxValue = mHead.getValue();
        Node currNode = mHead.getNext();
        
        while (currNode != null) {
            if (currNode.getValue() > maxValue) {
                maxValue = currNode.getValue();
            }
            currNode = currNode.getNext();
        }
        
        return maxValue;
    } // end of max()
    

    
    /**
     * Print the list in head to tail.
     */
    public void print() {
        System.out.println(toString());
    } // end of print()
    
    
    
    /**
     * Print the list from tail to head.
     */
    public void reversePrint() {
        // use a stack
        Stack<Integer> stack = new Stack<Integer>();
        Node currNode = mHead;
        while (currNode != null) {
            stack.add(currNode.getValue());
            currNode = currNode.getNext();
        }        
        

        while (!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
        
        System.out.println("");
    } // end of reversePrint()
        
	
    /**
     * @return String representation of the list.
     */
    public String toString() {
        Node currNode = mHead;

        StringBuffer str = new StringBuffer();

        while (currNode != null) {
            str.append(currNode.getValue() + " ");
            currNode = currNode.getNext();
        }

        return str.toString();
    } // end of getString();
	
	
	
    /**
     * Node type, inner private class.
     */
    private class Node
    {
        /** Stored value of node. */
        protected int mValue;
        /** Reference to next node. */
        protected Node mNext;

        public Node(int value) {
            mValue = value;
            mNext = null;
        }

        public int getValue() {
            return mValue;
        }


        public Node getNext() {
            return mNext;
        }


        public void setValue(int value) {
            mValue = value;
        }


        public void setNext(Node next) {
            mNext = next;
        }
    } // end of inner class Node
		
} // end of class SimpleList


