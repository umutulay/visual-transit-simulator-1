package edu.umn.cs.csci3081w.project.model;

/**
 * StrategyBasedTrainFactory class.
 */
public class StrategyBasedTrainFactory extends TrainFactory {
  private TrainStrategy trainStrategy;

  /**
   * Constructor for StrategyBasedTrainFactory.
   *
   * @param trainStrategy Train strategy to use.
   */
  public StrategyBasedTrainFactory(TrainStrategy trainStrategy) {
    this.trainStrategy = trainStrategy;
  }

  /**
   * Generates a Train.
   *
   * @param id Trains ID
   * @param line route of in/out bound
   * @param totalTrainsSent Trains sent from StorageFacility
   * @param storageFacility StorageFacility containing amount of trains
   * @return Generated Train
   */
  public Train generateTrain(int id, Line line, int totalTrainsSent,
                             StorageFacility storageFacility) {
    if (trainStrategy.trainTypeToDeploy(totalTrainsSent).equals(Train.ELECTRIC_TRAIN)
        && storageFacility.getElectricTrainNum() > 0) {
      ElectricTrain electricTrain = new ElectricTrain(id, line,
          ElectricTrain.CAPACITY, ElectricTrain.SPEED);
      storageFacility.decrementElectricTrainsNum();
      return electricTrain;
    } else if (trainStrategy.trainTypeToDeploy(totalTrainsSent).equals(Train.DIESEL_TRAIN)
        && storageFacility.getDieselTrainNum() > 0) {
      DieselTrain dieselTrain = new DieselTrain(id, line, DieselTrain.CAPACITY, DieselTrain.SPEED);
      storageFacility.decrementDieselTrainsNum();
      return dieselTrain;
    } else {
      return null;
    }
  }

  public TrainStrategy getTrainStrategy() {
    return trainStrategy;
  }
}
