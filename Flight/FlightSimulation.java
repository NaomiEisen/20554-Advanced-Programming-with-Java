import java.util.Random;
/**
 * FlightSimulation class -  contains the main method to run the airport simulation.
 * It creates airports, generates flights, and starts flight threads.
 * The simulation waits for all flight threads to finish before exiting.
 */
public class FlightSimulation {
	static final int NUMBER_FLIGHTS = 10; // Number of flight threads
	
	/* Main method to run the airport simulation */
	public static void main(String[] args) {
		// Create new Airport object
		Airport benGurion = new Airport("Ben-Gurion" , 3);
		Airport hartsfield = new Airport("Hartsfield-Jackson" , 3);
		
		// Create array to contain the flight threads
		Flight[] arrFlight = new Flight[NUMBER_FLIGHTS];
		
		// Create a Random object to determine random flight directions
		Random random = new Random();

        for (int i = 0; i < arrFlight.length; i++) {
        	try { // Create flight threads and populate the arrFlight with them
        		arrFlight[i] = createFlight(i, random.nextBoolean(), benGurion, hartsfield);
        	} catch (IllegalArgumentException e) {
        		// if an error occurred - print error message and terminate the program
        		 System.out.println(e.getMessage());
        		 return;
        	}
        }
        
        // Start all flight threads
        for (Flight flight : arrFlight) {
            flight.start();
        }

        // Wait for all flight threads to finish
        for (Flight flight : arrFlight) {
            try {
                flight.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // Print final message
        System.out.println("\n\nFlight simulation is over.");
    }

	/**
	 * Creates a new Flight object based on the given parameters.
	 * The flight direction is determined by the boolean parameter fromIsrael.
	 *
	 * @param id the flight's id
	 * @param fromIsrael a boolean indicating the flight's origin:
	 *                   - true if the flight is departing from Ben-Gurion airport
	 *                   - false if the flight is departing from Hartsfield-Jackson airport
	 * @param benGurion the Ben-Gurion airport object
	 * @param hartsfield the Hartsfield-Jackson airport object
	 * @return a new Flight object representing the flight with the specified direction
	 * @throws IllegalArgumentException if the flight ID is invalid
	 */
    private static Flight createFlight(int id, boolean fromIsrael, 
    		Airport benGurion, Airport hartsfield) throws IllegalArgumentException{
        return fromIsrael ? new Flight(id, benGurion, hartsfield) : new Flight(id, hartsfield, benGurion);
    }
}

