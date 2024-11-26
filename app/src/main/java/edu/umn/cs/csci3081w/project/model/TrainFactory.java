package edu.umn.cs.csci3081w.project.model;

/**
 * TrainFactory class.
 */
public abstract class TrainFactory {

  /**
   * Generates a Train.
   *
   * @param id Trains ID
   * @param line route of in/out bound
   * @param totalTrainsSent Trains sent from StorageFacility
   * @param storageFacility StorageFacility containing amount of trains
   * @return Generated Train
   */
  public abstract Train generateTrain(int id, Line line,
                                      int totalTrainsSent, StorageFacility storageFacility);
}
