import java.util.Random;

public class Main {
	static final int NUMBER_FLIGHTS = 3; 
	public static void main(String[] args) {
		Airport benGurion = new Airport("Ben-Gurion" , 3);
		Airport hartsfield = new Airport("Hartsfield-Jackson" , 3);
		
		Flight[] arrFlight = new Flight[NUMBER_FLIGHTS];
		Random random = new Random();

        for (int i = 0; i < arrFlight.length; i++) {
            arrFlight[i] = createFlight(i, random.nextBoolean(), benGurion, hartsfield);
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
    }

    private static Flight createFlight(int id, boolean fromIsrael, Airport benGurion, Airport hartsfield) {
        return fromIsrael ? new Flight(id, benGurion, hartsfield) : new Flight(id, hartsfield, benGurion);
    }
}

