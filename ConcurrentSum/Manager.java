import java.util.ArrayList;

public class Manager {
	private ArrayList<Integer> stock = new ArrayList<Integer>();
	private int done;

	public Manager(int[] stock) {
		for (int i = 0; i < stock.length; i++) {
			this.stock.add(stock[i]);
		}
		done = stock.length - 1;
	}

	public synchronized int[] getTwoNum() {
		while (done != 0 && stock.size() < 2) {
            try { // maybe handle the case when done is never 0
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }

        return stock.size() >= 2 ? 
        		new int[]{stock.remove(0), stock.remove(0)} : null;
    }

	public synchronized void addSum(int sum) {
		stock.add(sum);
		done--;
		notifyAll();
	}

	public synchronized int getTotal() {
		while (done != 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return stock.isEmpty() ? 0 : stock.get(0);
	}

}
