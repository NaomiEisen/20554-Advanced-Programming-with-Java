/**
 * The Flight class represents a flight that departs from one airport to another.
 * It extends Thread to allow for concurrent execution of flights.
 */
public class Flight extends Thread {
	private int flightID;                      // Flight's number
	private Airport departureAirport;          // The departure airport
	private Airport destinationAirport;        // The destination airport
	private final int DEPRAT_LAND_TIME = 2000; // Time for departure/landing
	private final int FLIGHT_TIME = 4000;      // Time for the flight duration

	/**
	 * Constructs a Flight object with the specified flight number and airports.
	 *
	 * @param flightID the flight number
	 * @param departAirport the departure airport
	 * @param landAirport the destination airport
	 * @throws IllegalArgumentException if the inputed flightID is negative.
	 */
	public Flight(int flightID, Airport departAirport, Airport landAirport) throws 
	IllegalArgumentException {
		if (flightID < 0) { // Validate id value
			// if value is illegal - throw IllegalArgumentException
			throw new IllegalArgumentException("Number of runways must be a " + 
		"non negative integer");
		}

		// Set values
		this.flightID = flightID;
		this.departureAirport = departAirport;
		this.destinationAirport = landAirport;
	}

	/**
	 * Gets the flight ID.
	 *
	 * @return the flight ID
	 */
	public int getFlightID() {
		return flightID;
	}

	/**
	 * Gets the departure airport.
	 *
	 * @return the departure airport
	 */
	public Airport getDepartureAirport() {
		return departureAirport;
	}

	/**
	 * Gets the destination airport.
	 *
	 * @return the destination airport
	 */
	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	/* Override toString method */
	@Override 
	public String toString() {
		return "Flight " + flightID + " from " + departureAirport + " to " + destinationAirport;
	}

	/* Override rum method */
	@Override
	public void run() {
		// Assign a departure runway
		int departRunway = departureAirport.getNumRunway();  
		// Simulate departure time
		flightSimulator(DEPRAT_LAND_TIME); 

		// Check if assignment succeeded
		if (departRunway != -1) {
			Messages.depart(this, departRunway); // Notify departure
		} else {
			Messages.departFailed(this); // Notify departure failure
			return; // Exit thread
		}

		// Free departure runway
		departureAirport.free(flightID, departRunway);
		// Notify runway free
		Messages.free(departRunway, this.getDepartureAirport().getName(), this.getFlightID());

		// Simulate flight duration
		flightSimulator(FLIGHT_TIME);

		// Assign a landing runway
		int landRunway = destinationAirport.getNumRunway();

		// Simulate landing time
		flightSimulator(DEPRAT_LAND_TIME);

		// Check if assignment succeeded
		if (landRunway != -1) {
			Messages.land(this, landRunway); // Notify landing
		} else {
			Messages.landFailed(this); // Notify landing failure
		}

		// Free landing runway
		destinationAirport.free(flightID, landRunway);
		// Notify runway free
		Messages.free(landRunway, this.getDepartureAirport().getName(), this.getFlightID());
	}

	/**
	 * Simulates a delay in the flight operation.
	 *
	 * @param time the duration of the delay in milliseconds
	 */
	private void flightSimulator(int time) {
		try {
			// Pause execution for the specified time
			Thread.sleep(time); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
} // end of class Flight


