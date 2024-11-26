package edu.umn.cs.csci3081w.project.model;

/**
 * BusStrategyNight class.
 */
public class BusStrategyNight implements BusStrategy {
  /**
   * Array of Bus types to deploy during the night.
   */
  private static final String[] deploySequence;

  static {
    deploySequence = new String[]{
        SmallBus.SMALL_BUS,
        SmallBus.SMALL_BUS,
        SmallBus.SMALL_BUS,
        LargeBus.LARGE_BUS,
    };
  }

  /**
   * Determines what type of Bus to deploy during the night.
   *
   * @param totalBusesSent buses sent from StorageFacility
   * @return Type of Bus to deploy
   */
  public String busTypeToDeploy(int totalBusesSent) {
    int sequencePos = totalBusesSent % 4;
    String busType = deploySequence[sequencePos];
    return busType;
  }
}
