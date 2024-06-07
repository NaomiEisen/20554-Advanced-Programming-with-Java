import java.util.Random;
/**
 * Main class -  contains the main method to run the airport simulation.
 * It creates airports, generates flights, and starts flight threads.
 * The simulation waits for all flight threads to finish before exiting.
 */
public class FlightSimulation {
	static final int NUMBER_FLIGHTS = 10; 
	public static void main(String[] args) {
		Airport benGurion = new Airport("Ben-Gurion" , 3);
		Airport hartsfield = new Airport("Hartsfield-Jackson" , 3);
		
		Flight[] arrFlight = new Flight[NUMBER_FLIGHTS];
		Random random = new Random();

        for (int i = 0; i < arrFlight.length; i++) {
        	try {
        		arrFlight[i] = createFlight(i, random.nextBoolean(), benGurion, hartsfield);
        	} catch (IllegalArgumentException e) {
        		 System.out.println(e.getMessage());
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
        
        System.out.println("\n\nFlight simulation is over.");
    }

    private static Flight createFlight(int id, boolean fromIsrael, 
    		Airport benGurion, Airport hartsfield) throws IllegalArgumentException{
        return fromIsrael ? new Flight(id, benGurion, hartsfield) : new Flight(id, hartsfield, benGurion);
    }
}

