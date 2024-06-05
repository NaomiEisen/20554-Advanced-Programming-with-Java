import java.util.Scanner;

public class ScanPositiveInt {
	private static Scanner scanner = new Scanner(System.in);
	
	public static int scan() {
		int n;
		// Loop until the user provides a valid input
		while (true) {
			if (scanner.hasNextInt()) {
				n = scanner.nextInt();
				if (n > 0) {
					break; // Valid input, exit the loop
				} else {
					System.out.println("Please enter a positive integer: ");
				}
			} else {
				System.out.println("Invalid input. Please enter a positive integer: ");
				scanner.next(); // Consume the invalid input
			}
		}
		//scanner.close();
		return n;
	}

}
