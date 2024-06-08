import java.util.Scanner;
/**
 * Utility class for scanning positive integers from user input.
 */
public class ScanPositiveInt {
	private static Scanner scanner = new Scanner(System.in);
	
	/**
     * Scans the user input for a positive integer.
     * Keeps looping until a valid positive integer is provided.
     *
     * @return the positive integer entered by the user
     */
	public static int scan() {
		int n;
		// Loop until the user provides a valid input
		while (true) {
			if (scanner.hasNextInt()) {
				n = scanner.nextInt();
				if (n > 0) {
					break; // Valid input, exit the loop
				} else {
					System.out.println("Input is not positive. Try again.\nPlease enter a positive integer: ");
				}
			} else {
				System.out.println("Input is not an integer. Try again.\nPlease enter a positive integer: ");
				scanner.next(); // Consume the invalid input
			}
		}
		return n;
	}
} // end of class ScanPositiveInt
