package edu.umn.cs.csci3081w.project.model;

/**
 * SmallBus class.
 */
public class SmallBus extends Bus {
  /**
   * Constants for a capacity of a small bus.
   */
  public static final int CAPACITY = 20;
  /**
   * Constants for a speed of a small bus.
   */
  public static final String SMALL_BUS = "SMALL_BUS";

  /**
   * Constructor for a bus.
   *
   * @param id       bus identifier
   * @param line     route of in/out bound
   * @param capacity capacity of bus
   * @param speed    speed of bus
   */
  public SmallBus(int id, Line line, int capacity, double speed) {
    super(id, line, capacity, speed);
  }

  /**
   * co2 consumption for a bus.
   *
   * @return The current co2 consumption value
   */
  @Override
  public int getCurrentCO2Emission() {
    return (2 * getPassengers().size()) + 3;
  }
}
