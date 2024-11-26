package edu.umn.cs.csci3081w.project.model;

import java.io.PrintStream;

/**
 * Passenger class.
 */
public class Passenger {

  // Name of the passenger
  private String name;
  // Destination stop id
  private int destinationStopId;
  // Time spent waiting at stop
  private int waitAtStop;
  // Time spent on vehicle
  private int timeOnVehicle;

  /**
   * Constructor for passenger.
   *
   * @param name              name of passenger
   * @param destinationStopId destination stop id
   */
  public Passenger(int destinationStopId, String name) {
    this.name = name;
    this.destinationStopId = destinationStopId;
    this.waitAtStop = 0;
    this.timeOnVehicle = 0;
  }

  /**
   * Updates time variables for passenger.
   */
  public void pasUpdate() {
    if (isOnVehicle()) {
      timeOnVehicle++;
    } else {
      waitAtStop++;
    }
  }

  public void setOnVehicle() {
    timeOnVehicle = 1;
  }

  /**
   * Check if passenger is on vehicle.
   *
   * @return true if passenger is on vehicle
   */
  public boolean isOnVehicle() {
    return timeOnVehicle > 0;
  }

  public int getDestination() {
    return destinationStopId;
  }

  /**
   * Report statistics for passenger.
   *
   * @param out stream for printing
   */
  public void report(PrintStream out) {
    out.println("####Passenger Info Start####");
    out.println("Name: " + name);
    out.println("Destination: " + destinationStopId);
    out.println("Wait at stop: " + waitAtStop);
    out.println("Time on vehicle: " + timeOnVehicle);
    out.println("####Passenger Info End####");
  }

  public int getWaitAtStop() {
    return waitAtStop;
  }

  public int getTimeOnVehicle() {
    return timeOnVehicle;
  }
}
