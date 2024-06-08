import java.util.ArrayList;
/**
 * Manager class - manages the synchronization of the ConcurrentSum threads.
 * It maintains a stock of the specified integers, and provides methods to 
 * perform summation operations in a synchronized manner. 
 */
public class Manager {
	// stock to hold the specified array elements
	private ArrayList<Integer> stock = new ArrayList<Integer>(); 
	
	// Counter to determine the completion of the process
	private int done;

	/**
     * Constructor to initialize the stock array list and set the done counter.
     * Copy's the given array content to the stock array list.
     *
     * @param stock an array of integers representing the initial stock
     */
	public Manager(int[] stock) {
		// Copy inputed array's content 
		for (int i = 0; i < stock.length; i++) {
			this.stock.add(stock[i]);
		}
		// Set the done counter
		done = stock.length - 1;
		
		// Print initial array
		System.out.print("Initial array: ");
		printArrayList();
	}


    /**
     * Synchronized method to get two numbers from the stock.
     * Waits if the stock has fewer than two elements or done is not zero.
     *
     * @return an array of two integers removed from the stock, or null if not 
     * enough elements
     */
	public synchronized int[] getTwoNum() {
		while (done != 0 && stock.size() < 2) {
            try {
                wait(); // Wait for more elements to be added to the stock
            } catch (InterruptedException e) {
            	 // Restore the interrupted status and print interruption
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

		// Return two integers or null if not enough elements
        return stock.size() >= 2 ? 
        		new int[]{stock.remove(0), stock.remove(0)} : null;
    }

	/**
     * Synchronized method to add the sum of two numbers to the stock.
     * Notifies all waiting threads after adding the sum.
     *
     * @param sum the sum to be added to the stock
     */
	public synchronized void addSum(int sum) {
		// Add sum to stock
		stock.add(sum);
		
		// Print status
        System.out.print("Array after summation by " + Thread.currentThread().getName() 
        		+ ": ");
        printArrayList();
		
		done--; // Update counter
		notifyAll(); // Notify all waiting threads
	}

	 /**
     * Synchronized method to get the total sum from the stock.
     * Waits until the done counter reaches zero.
     * The total sum is the single element left in the stock after all 
     * the summation operations are completed.
     *
     * @return the total sum from the stock, or 0 if the stock is empty
     */
	public synchronized int getTotal() {
		while (done != 0) {
			try {
				wait(); // Wait until the done counter reaches zero
			} catch (InterruptedException e) {
				// Restore the interrupted status and print interruption 
                Thread.currentThread().interrupt();
				e.printStackTrace();
			}
		}

		// Return the total sum of the stock, or 0 if the stock is empty
		return stock.isEmpty() ? 0 : stock.get(0);
	}

	/**
	 * Prints the contents of the array list.
	 */
	public void printArrayList() {
		System.out.print("[");
		// Print all the array elements
		for (int i = 0; i < this.stock.size(); i++) {
			System.out.print(this.stock.get(i));
			// If this is not the last element, separate it with a comma
			if (i < this.stock.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}
} // end of class Manager
