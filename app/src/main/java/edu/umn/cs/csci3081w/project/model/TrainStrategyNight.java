package edu.umn.cs.csci3081w.project.model;

/**
 * TrainStrategyNight class.
 */
public class TrainStrategyNight implements TrainStrategy {
  private static final String[] deploySequence;

  static {
    deploySequence = new String[]{
        Train.ELECTRIC_TRAIN,
        Train.DIESEL_TRAIN,
    };
  }

  /**
   * Determines what type of train to deploy during the night.
   *
   * @param totalTrainsSent Trains sent from StorageFacility
   * @return Type of train that should be deployed
   */
  public String trainTypeToDeploy(int totalTrainsSent) {
    int sequencePos = totalTrainsSent % 2;
    String trainType = deploySequence[sequencePos];
    return trainType;
  }
}
