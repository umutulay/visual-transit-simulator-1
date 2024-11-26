package edu.umn.cs.csci3081w.project.model;

/**
 * ElectricTrain class.
 */
public class ElectricTrain extends Train {

  /**
   * Constructor for a train.
   *
   * @param id       train identifier
   * @param line     route of in/out bound
   * @param capacity capacity of the train
   * @param speed    speed of the train
   */
  public ElectricTrain(int id, Line line, int capacity, double speed) {
    super(id, line, capacity, speed);
  }

  /**
   * co2 consumption for a train.
   *
   * @return The current co2 consumption value
   */
  @Override
  public int getCurrentCO2Emission() {
    return 0;
  }
}
