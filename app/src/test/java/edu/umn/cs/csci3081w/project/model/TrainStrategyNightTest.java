package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TrainStrategyNightTest {
  /**
   * Test the order of the trains deployed by the strategy.
   */
  @Test
  public void orderTest() {
    TrainStrategyNight testTrainStrategyNight = new TrainStrategyNight();
    for (int i = 0; i < 100; i++) {
      String ithTrain = testTrainStrategyNight.trainTypeToDeploy(i);
      if ((i + 1) % 2 == 0) {
        assertEquals(ithTrain, Train.DIESEL_TRAIN);
      } else {
        assertEquals(ithTrain, Train.ELECTRIC_TRAIN);
      }
    }
  }
}
