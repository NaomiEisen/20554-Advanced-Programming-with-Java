/**
 * The Messages class encapsulates static methods for generating informative messages
 * related to flight operations in an airport simulation.
 */
public class Messages {
	/**
	 * Displays a message for flight departure.
	 *
	 * @param flight the flight object representing the departing flight
	 * @param runway the number of the departure runway
	 */
	public static void depart(Flight flight, int runway) {
		System.out.println("༄‿︵‿ ✈︎ ‧₊˚☁️	" + flight + "has departed on runway" + runway);
	}

	/**
	 * Displays a message for flight departure failure.
	 *
	 * @param flight the flight object that failed to depart
	 */
	public static void departFailed(Flight flight) {
		System.out.println("⛝" +  flight + "could not depart due to no available runways");
	}
	
	/**
	 * Displays a message for runway freeing.
	 *
	 * @param runway the number of the freed runway
	 * @param airport the name of the airport where the runway is located
	 * @param flightID the ID of the flight that freed the runway
	 */
	public static void free(int runway,  String airport , int flightID) {
		System.out.printf("☑	Runway number %d in airport %s has been freed by flight number %d\n" , 
				runway, airport ,flightID );
	}
	
	/**
	 * Displays a message for flight landing.
	 *
	 * @param flight the flight object representing the landed flight
	 * @param runway the number of the landing runway
	 */
	public static void land(Flight flight, int runway) {
		System.out.println("‿︵‿︵✈ - - -📍	" + flight + "has landed on runway" + runway);
	}

	/**
	 * Displays a message for flight landing failure.
	 *
	 * @param flight the flight object that failed to land
	 */
	public static void landFailed(Flight flight) {
		System.out.println("⛝	" + flight + "could not land due to no available runways."
				+ "Guess this is Manifest 2.");
	}
} // end of class Messages
