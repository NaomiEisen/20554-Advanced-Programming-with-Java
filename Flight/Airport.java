import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Airport class - represents an airport with a name and a number of runways.
 * The class provides methods for managing the assignment of runways for flight departures 
 * and landings.
 */
public class Airport {
	private String name;   // Airport's name
	private int numRunway; // Number of runways in the airport
	
	// Queue to store available runways to store available runways
	private Queue<Integer> availableRunways = new LinkedList<>(); 
	
	// Lock object for synchronization of methods
	private final Lock lock = new ReentrantLock(); 
	
	// Condition object for runway availability
	private final Condition runwayAvailable = lock.newCondition();

	// Queue to hold waiting flights for runway assignment
	private final Queue<Condition> waitingQueue = new LinkedList<>();

    /**
     * Constructs a new Airport with the specified name and number of runways.
     * Initializes the list of available runways, numbering them consecutively
     * starting from 1.
     *
     * @param name the name of the airport
     * @param numRunway the number of runways at the airport
     */
    public Airport(String name, int numRunway) {
    	// Set values
        setName(name);
        setNumRunway(numRunway);
        
        // Fill the availableRunways queue
        for (int i = 1; i <= numRunway; i++) {
            this.availableRunways.add(i);
        }
    }

    /**
     * Sets the name of the airport.
     * If the name is null or empty, sets 'default' as the airport name.
     *
     * @param name the new name of the airport
     */
    private void setName(String name) {
    	// Validate name
        if (name == null || name.trim().isEmpty()) {
        	// If name value is illegal - set default name value
            this.name = "default";
        }
        
        // Set value
        this.name = name;
    }
    
    /**
     * Gets the name of the airport.
     *
     * @return the name of the airport
     */
    public String getName() {
        return name;
    }
    

    /**
     * Sets the number of runways at the airport.
     * If the specified number is negative, sets the number of runways to 1.
     *
     * @param numRunway the new number of runways
     */
    private void setNumRunway(int numRunway) {
    	// Validate number of runway
        if (numRunway <= 0) {
        	// If numRunway value is illegal - set default value
        	this.numRunway = 1;
        }
        // Set value
        this.numRunway = numRunway;
    }
    
    /**
     * Gets the number of runways at the airport.
     *
     * @return the number of runways
     */
    public int getNumRunway() {
        return numRunway;
    }
    
    /**
     * Utility method to manage runway allocation for both landing and departing.
     * This method ensures that the first flight to request a runway will be the first to receive one.
     *
     * @param flightNum the flight number requesting the runway
     * @return the number of the assigned runway, or -1 if no runways are available
     */
    private int allocateRunway(int flightNum) {
        lock.lock();
        try {
            // Create a new condition for the current thread
            Condition myCondition = lock.newCondition();
            // Add the condition to the waiting queue
            waitingQueue.add(myCondition);

            // Wait until a runway becomes available and it's the thread's turn
            while (availableRunways.isEmpty() || waitingQueue.peek() != myCondition) {
                try {
                    // Wait for a signal indicating a runway is available
                    myCondition.await();
                } catch (InterruptedException e) {
                    // Restore the interrupted status and remove the condition from the queue
                    Thread.currentThread().interrupt();
                    waitingQueue.remove(myCondition);
                    return -1;
                }
            }

            // Remove the current thread's condition from the waiting queue
            waitingQueue.poll();
            // Return the available runway number
            return availableRunways.poll();
            
        } finally {
            // Ensure the lock is always released
            lock.unlock();
        }
    }

    /**
     * Assigns a runway for a departing flight.
     *
     * @param flightNum the flight number of the departing flight
     * @return the number of the assigned runway, or -1 if no runways are available
     */
    public int depart(int flightNum) {
        return allocateRunway(flightNum);
    }

    /**
     * Assigns a runway for a landing flight.
     *
     * @param flightNum the flight number of the landing flight
     * @return the number of the assigned runway, or -1 if no runways are available
     */
    public int land(int flightNum) {
        return allocateRunway(flightNum);
    }

    /**
     * Frees a runway after a flight has departed/landed, making it available for future use.
     * The methods adds the inputted runway number to the availableRunways list.
     *
     * @param flightNum the flight number of the landed flight
     * @param runwayNum the number of the runway to be made available
     */
    public void free(int flightNum, int runwayNum) {
        lock.lock();
        try {
            availableRunways.add(runwayNum);
            if (!waitingQueue.isEmpty()) {
                waitingQueue.peek().signal();
            } else {
                runwayAvailable.signal();
            }
        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public String toString() {
        return name;
    }
}

