package edu.umn.cs.csci3081w.project.model;

import java.io.PrintStream;

/**
 * Bus class.
 */
public class Bus extends Vehicle {
  /**
   * Bus constructor.
   */
  public static final String BUS_VEHICLE = "BUS_VEHICLE";
  /**
   * Speed of the bus.
   */
  public static final double SPEED = 0.5;
  /**
   * Capacity of the bus.
   */
  public static final int CAPACITY = 60;

  /**
   * Constructor for a bus.
   *
   * @param id       bus identifier
   * @param line     route of in/out bound
   * @param capacity capacity of bus
   * @param speed    speed of bus
   */
  public Bus(int id, Line line, int capacity, double speed) {
    super(id, capacity, speed, new PassengerLoader(), new PassengerUnloader(), line);
  }

  /**
   * Report statistics for the bus.
   *
   * @param out stream for printing
   */
  public void report(PrintStream out) {
    out.println("####Bus Info Start####");
    out.println("ID: " + getId());
    out.println("Name: " + getName());
    out.println("Speed: " + getSpeed());
    out.println("Capacity: " + getCapacity());
    out.println("Position: " + (getPosition().getLatitude() + "," + getPosition().getLongitude()));
    out.println("Distance to next stop: " + getDistanceRemaining());
    out.println("****Passengers Info Start****");
    out.println("Num of passengers: " + getPassengers().size());
    for (Passenger pass : getPassengers()) {
      pass.report(out);
    }
    out.println("****Passengers Info End****");
    out.println("####Bus Info End####");
  }

  /**
   * co2 consumption for a bus.
   *
   * @return The current co2 consumption value
  */
  public int getCurrentCO2Emission() {
    return (2 * getPassengers().size()) + 4;
  }
}
