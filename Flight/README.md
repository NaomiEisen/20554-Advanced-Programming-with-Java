# Flight Program

This project is part of the '20554 Advanced Programming with Java' course. 
It simulates plane flights using multi-threading, allowing you to observe the behavior of flights between two airports.
Graded 100 :)


## Description

The `FlightSimulation` program models an airport system where multiple flights take off and land between two airports. The simulation uses multi-threading to concurrently execute the flights, providing a realistic view of airport operations.

## Main Components

- ### FlightSimulation Class: 
This is the main class that runs the simulation. It creates airport objects, generates flight objects, starts the flight threads, and waits for all flights to finish.
- ### Flight Class
Represents a flight that departs from one airport and lands at another. This class extends `Thread` to enable concurrent execution of multiple flight objects.
### - Airport Class
Represents an airport with a specified number of runways. Manages the availability of runways for flight departures and landings.
### - Messages Class*
Provides static methods for displaying informative messages related to flight operations, such as departures, landings, and runway availability.

## Requirements
- Java Development Kit (JDK) 8 or higher
- A Java IDE (Eclipse, IntelliJ IDEA, NetBeans, etc.)
