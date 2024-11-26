package edu.umn.cs.csci3081w.project.model;

import java.io.PrintStream;

/**
 * Train class.
 */
public class Train extends Vehicle {
  /**
   * Constants for a train.
   */
  public static final String TRAIN_VEHICLE = "TRAIN_VEHICLE";
  /**
   * Constants for an electric train.
   */
  public static final String ELECTRIC_TRAIN = "ELECTRIC_TRAIN";
  /**
   * Constants for a diesel train.
   */
  public static final String DIESEL_TRAIN = "DIESEL_TRAIN";
  /**
   * Constants for a speed of a train.
   */
  public static final double SPEED = 1;
  /**
   * Constants for a capacity of a train.
   */
  public static final int CAPACITY = 120;

  /**
   * Constructor for a train.
   *
   * @param id       train identifier
   * @param line     route of in/out bound
   * @param capacity capacity of the train
   * @param speed    speed of the train
   */
  public Train(int id, Line line, int capacity, double speed) {
    super(id, capacity, speed, new PassengerLoader(), new PassengerUnloader(), line);
  }

  /**
   * Report statistics for the train.
   *
   * @param out stream for printing
   */
  public void report(PrintStream out) {
    out.println("####Train Info Start####");
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
    out.println("####Train Info End####");
  }

  /**
   * co2 consumption for a train.
   *
   * @return The current co2 consumption value
   */
  public int getCurrentCO2Emission() {
    return (3 * getPassengers().size()) + 6;
  }

}
