import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Airport class represents an airport with a name and a number of runways.
 * It includes validation for the name and the number of runways. The class also 
 * provides methods for managing the assignment of runways for flight departures 
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
     * @throws IllegalArgumentException if the name is null or empty, or if the 
     * number of runways is not positive
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
     * Helper method to manage runway allocation for both landing and departing.
     *
     * @param flightNum the flight number requesting the runway
     * @return the number of the assigned runway, or -1 if no runways are available
     */
    private int allocateRunway(int flightNum) {
        lock.lock();
        try {
            Condition myCondition = lock.newCondition();
            waitingQueue.add(myCondition);

            while (availableRunways.isEmpty() || waitingQueue.peek() != myCondition) {
                try {
                    myCondition.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    waitingQueue.remove(myCondition);
                    return -1;
                }
            }

            waitingQueue.poll();
            return availableRunways.poll();
        } finally {
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

