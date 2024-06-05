import java.util.Random;

public class Main {
	public static void main(String[] args) {
		int m;
		
		System.out.print("Please enter the size of the array (positive integer): ");
		int[] randomArray = generateRandomArray(ScanPositiveInt.scan());
		Manager manager = new Manager(randomArray);

		System.out.print("Please enter the number of threads (positive integer): ");
		m = ScanPositiveInt.scan();
		
		ConcurrentSum[] threadsArray = new ConcurrentSum[m];
		
		for (int i = 0; i < m ; i++) {
			threadsArray[i] = new ConcurrentSum(manager); 
		}
		
		for (int i = 0; i < m ; i++) {
			threadsArray[i].start(); 
		}
		
		System.out.println("Total: " + manager.getTotal());
	}


public static int[] generateRandomArray(int n) {
    Random random = new Random();
    int[] array = new int[n];

    for (int i = 0; i < n; i++) {
        array[i] = random.nextInt(101); // 101 because nextInt is exclusive of the top value
    }

    return array;
}
}