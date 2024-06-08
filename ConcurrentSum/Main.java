import java.util.Random;
/**
 * Main class - contains the entry point of the program, which initializes the system,
 * generates a random array, creates and starts concurrent summation threads, and displays the total sum.
 */
public class Main {
	final static int NUM_RANGE = 100; // Range for generating random numbers
	public static void main(String[] args) {
	
		// Prompt user to enter the size of the array
		System.out.print("Please enter the size of the array (positive integer): ");
		int[] randomArray = generateRandomArray(ScanPositiveInt.scan());
		
		// Prompt user to enter the number of threads
		System.out.print("Please enter the number of threads (positive integer): ");
		int m = ScanPositiveInt.scan();
				
		// Create a Manager instance to manage the array
		Manager manager = new Manager(randomArray);

		// Initialize an array to hold the concurrent summation threads
		ConcurrentSum[] threadsArray = new ConcurrentSum[m];
		
		// Create the threads
		for (int i = 0; i < m ; i++) {
			threadsArray[i] = new ConcurrentSum(manager); 
		}
		
		// Start the threads
		for (int i = 0; i < m ; i++) {
			threadsArray[i].start(); 
		}
		
		// Wait for all threads to complete and print the total sum
		/* FOR ME : MAYBE CHANGE TO JOIN */
		System.out.println("Total: " + manager.getTotal());
	}

	/**
	 * Generates an array of random integers of specified size.
	 *
	 * @param n the size of the array to be generated
	 * @return an array of random integers
	 */
	public static int[] generateRandomArray(int n) {
		Random random = new Random();
		int[] array = new int[n];

		// Fill the array with random integers within the specified range
		for (int i = 0; i < n; i++) {
			// 101 because nextInt is exclusive of the top value
			array[i] = random.nextInt(NUM_RANGE + 1); 
		}

		// Return the generated array
		return array;
	}
} // end of class Main