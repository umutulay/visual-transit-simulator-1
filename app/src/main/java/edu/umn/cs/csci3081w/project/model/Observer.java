package edu.umn.cs.csci3081w.project.model;

import java.util.List;

/**
 * Observer interface.
 */
public interface Observer {

  /**
   * Updates observers pending change in subject.
   */
  void updateCO2();
  
  /**
   * Gets the id of the observer.
   *
   * @return the id of the observer
   */
  int getId();

  /**
   * Gets the co2 array of the observer.
   * @return the co2 array of the observer
   */
  int[] getCo2Array();

  /**
   * Gets the co2 index of the observer.
   * @return the co2 index of the observer
   */
  int getCo2Index();

  /**
   * Gets the position of the observer.
   * @return the position of the observer
   */
  Position getPosition();

  /**
   * Gets the passengers of the observer.
   * @return the passengers of the observer
   */
  List<Passenger> getPassengers();
}
