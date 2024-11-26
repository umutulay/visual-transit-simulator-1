package edu.umn.cs.csci3081w.project.model;

/**
 * BusStrategyDay class.
 */
public class BusStrategyDay implements BusStrategy {
  /**
   * Array of bus types to deploy during the day.
   */
  private static final String[] deploySequence;

  static {
    deploySequence = new String[]{
        LargeBus.LARGE_BUS,
        LargeBus.LARGE_BUS,
        SmallBus.SMALL_BUS,
    };
  }

  /**
   * Determines what type of bus to deploy during the day.
   *
   * @param totalBusesSent buses sent from StorageFacility
   * @return Type of bus that should be deployed
   */
  public String busTypeToDeploy(int totalBusesSent) {
    int sequencePos = totalBusesSent % 3;
    String busType = deploySequence[sequencePos];
    return busType;
  }
}
