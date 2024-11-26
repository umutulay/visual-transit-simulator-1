package edu.umn.cs.csci3081w.project.model;

/**
 * TrainStrategy interface.
 */
public interface TrainStrategy {
  /**
   * Determines what type of train to deploy.
   *
   * @param totalTrainsSent Trains sent from StorageFacility
   * @return Type of train that should be deployed
   */
  String trainTypeToDeploy(int totalTrainsSent);
}
