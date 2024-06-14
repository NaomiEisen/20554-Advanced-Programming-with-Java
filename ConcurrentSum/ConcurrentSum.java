/**
 * The ConcurrentSum class extends the Thread class and is responsible for 
 * performing concurrent summation operations on a shared array using a Manager instance.
 * Each thread created from this class repeatedly fetches pairs of numbers 
 * from the Manager's stock, adds them, and returns the sum to the stock.
 */
public class ConcurrentSum extends Thread {
	// Manager instance to manage the array and synchronization
	private Manager manager;
	
	/**
     * Constructor to initialize the ConcurrentSum thread with a Manager instance.
     *
     * @param manager the Manager instance that this thread will interact with
     */
	public ConcurrentSum(Manager manager) {
		this.manager = manager;
	}

    /**
     * The run method is the entry point for the thread. 
     * It repeatedly performs sum operations until no more pairs are available.
     */
	@Override
	public void run() {
		// Continue summing pairs until there are no more pairs
		while (sumOperation()) {};
	}

	/**
     * Performs the sum operation by retrieving two numbers from the Manager's stock,
     * adding them, and then returning the sum to the stock.
     *
     * @return true if the sum operation was performed successfully, false otherwise
     */
	private boolean sumOperation() {
		// Get two elements from Manager's stock
		int nums[] = manager.getTwoNum();

		
		if (nums != null) {
			// If there are 2 numbers in the stock, perform the sum operation
			int sum = nums[0] + nums[1];
			System.out.printf("%s adding the numbers %d + %d = %d\n", this.getName(), nums[0], nums[1], sum);
			manager.addSum(sum);
			return true;
		} else {
			// Return false if there are no enough elements in stock
			return false;
		}
	}
} // end of class ConcurrentSum

