import java.util.concurrent.locks.Lock;

public class ConcurrentSum extends Thread {
	private Manager manager;

	public ConcurrentSum(Manager manager) {
		this.manager = manager;
	}

	@Override
	public void run() {
		// do {} while (sumOperation());
		 while (true) {
	            if (!sumOperation()) {
	                break; // Exit loop if no more pairs are available
	            }
	        }
	}

	private boolean sumOperation() {
		int arr[] = manager.getTwoNum();

		if (arr != null) {
			manager.addSum(arr[0] + arr[1]);
			return true;
		} else {
			return false;
		}
	}

}

/*lock.lock();
try {
	arr = manager.getTwoNum();
} finally {
	lock.unlock();
}*/
