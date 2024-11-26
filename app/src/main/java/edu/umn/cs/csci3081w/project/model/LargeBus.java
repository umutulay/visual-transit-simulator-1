package edu.umn.cs.csci3081w.project.model;

/**
 * LargeBus class.
 */
public class LargeBus extends Bus {

  /**
   * Capacity of a large bus.
   */
  public static final int CAPACITY = 80;
  /**
   * Large bus type.
   */
  public static final String LARGE_BUS = "LARGE_BUS";

  /**
   * Constructor for a bus.
   *
   * @param id       bus identifier
   * @param line     route of in/out bound
   * @param capacity capacity of bus
   * @param speed    speed of bus
   */
  public LargeBus(int id, Line line, int capacity, double speed) {
    super(id, line, capacity, speed);
  }

  /**
   * co2 consumption for a bus.
   *
   * @return The current co2 consumption value
   */
  @Override
  public int getCurrentCO2Emission() {
    return (2 * getPassengers().size()) + 5;
  }
}
