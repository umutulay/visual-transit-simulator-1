package edu.umn.cs.csci3081w.project.model;

/**
 * BusStrategy interface.
 */
public interface BusStrategy {
  /**
   * Method to determine the type of bus to deploy.
   *
   * @param totalBusesSent The total number of buses sent
   * @return The type of bus to deploy
   */
  String busTypeToDeploy(int totalBusesSent);
}
