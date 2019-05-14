
package queueclass;

/**
 * class for a queue data structure
 * <p>
 * @author ggear
 */
public class SimpleQueueClass extends java.lang.Object
{
    /**
     * Provides constant for default capacity
     */
    private final int DEFAULT_CAPACITY = 10;
    /** 
     * Provides constant -999999 for access failure messaging
     */
    public static final int FAILED_ACCESS = -999999;
    /**
     * Stores current capacity of queue class
     */
    private int capacity;
    /**
     * Stores current size of queue class
     */
    private int size;
    /**
     * Stores queue head index 
     */
    private int headIndex;
    /**
     * Stores queue tail index
     */
    private int tailIndex;
    /**
     * Integer array stores queue data
     */
    private int[] queueData;
    /**
     * Default constructor
     */
    public SimpleQueueClass()
    {
        size = 0;
        capacity = DEFAULT_CAPACITY;
        queueData = new int[capacity];
        headIndex = 0;
        tailIndex = -1;
    }
    /**
     * Initialization constructor
     */
    public SimpleQueueClass(int capacitySetting)
    {
        size = 0;
        capacity = capacitySetting;
        queueData = new int[capacity];
        headIndex = 0;
        tailIndex = -1;
    }
    /**
     * Copy constructor
     * <p>
     * Note: queue is copied so that head index is at zero and tail index is
     * at size - 1; i.e., this resets the array to start at zero
     * <p>
     * @param copied - SimpleQueueClass object to be copied
     */
    public SimpleQueueClass(SimpleQueueClass copied)
    {
        headIndex = copied.headIndex;
        tailIndex = copied.tailIndex;
        size = copied.size;
        capacity = copied.capacity;
        queueData = new int[this.capacity];
        int index = 0;
        while( index < size ) 
        {
            queueData[index] = copied.queueData[index];
            index++;
        }
    }
    /**
     * Updates queue head index to wrap around array as needed
     * Note: Does not use if/else
     */
    private void updateHeadIndex()
    {
        headIndex = (headIndex + 1) % (capacity - 1);
    }
    /**
     * Updates queue tail index to wrap around array as needed
     * Note: Does not use if/else
     */
    private void updateTailIndex()
    { 
        tailIndex = (tailIndex + 1) % (capacity - 1);
    }
    /**
     * Reports queue empty state
     * Note: Does not use if/else
     * <p>
     * @return Boolean evidence of empty list
     */
    public boolean isEmpty()
    {
        return ( size == 0 );
    }
    /**
     * Checks for resize and resizes to twice the current capacity if needed
     * <p>
     * Note: Returns true if resize is necessary and is conducted;
     * returns false if no action is taken
     * <p>
     * @return success of operation
     */
    public boolean checkForReSize()
    {
        if( size == capacity )
        {
            int copySize = (tailIndex + 1) - headIndex;
            int[] copyArray = new int[3 * capacity];    
            this.clear();
            int index = 0;
            // adds all values into new array of 3x size
            while( index < copySize ) 
            {
                copyArray[index] = queueData[headIndex + index];
                index++;
            }
            queueData = copyArray;
        }
        return false;
    }
    /**
     * Checks for resize, then enqueues value
     * <p>
     * Note: Updates tail index, then appends value to array at tail index
     * <p>
     * @param newValue - Value to be enqueued 
     */
    public void enqueue(int newValue)
    {
        checkForReSize();
        updateTailIndex();
        queueData[tailIndex] = newValue;
        size++;
    }
    /**
     * Removes and returns value from front of queue
     * <p>
     * Note: Acquires data from head of queue, then updates head index
     */
    public int dequeue()
    {
        int removedValue = queueData[headIndex];
        updateHeadIndex();
        size--;
        int index = 0;   
        return removedValue;       
    }
    /**
     * Provides peek at front of queue
     * <p>
     * Provides peek at front of queue
     * @return Value if successful, FAILED_ACCESS if not
     */
    public int peekFront()
    {
        if( queueData == null )
        {
            return FAILED_ACCESS;
        }
        return queueData[headIndex];
    }
    /**
     * Clears the queue by setting the size to zero, the tail index to -1 
     * and the head index to zero
     */
    public void clear()
    {
        size = 0;
        headIndex = 0;
        tailIndex = -1;
    }
}
