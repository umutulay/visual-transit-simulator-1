package edu.umn.cs.csci3081w.project.model;

/**
 * StrategyBasedBusFactory class.
 */
public class StrategyBasedBusFactory extends BusFactory {
  private BusStrategy busStrategy;

  /**
   * Constructor for StrategyBasedBusFactory.
   *
   * @param busStrategy Bus strategy to use.
   */
  public StrategyBasedBusFactory(BusStrategy busStrategy) {
    this.busStrategy = busStrategy;
  }


  /**
   * Generates a Bus based off a BusStrategy.
   *
   * @param id ID for bus
   * @param line route of in/out bound
   * @param totalBusesSent Buses sent from StorageFacility
   * @param storageFacility StorageFacility containing amount of buses
   * @return Generated Bus
   */
  public Bus generateBus(int id, Line line, int totalBusesSent, StorageFacility storageFacility) {
    if (busStrategy.busTypeToDeploy(totalBusesSent).equals(SmallBus.SMALL_BUS)
        && storageFacility.getSmallBusNum() > 0) {
      SmallBus smallBus = new SmallBus(id, line, SmallBus.CAPACITY, SmallBus.SPEED);
      storageFacility.decrementSmallBusesNum();
      return smallBus;
    } else if (busStrategy.busTypeToDeploy(totalBusesSent).equals(LargeBus.LARGE_BUS)
        && storageFacility.getLargeBusNum() > 0) {
      LargeBus largeBus = new LargeBus(id, line, LargeBus.CAPACITY, LargeBus.SPEED);
      storageFacility.decrementLargeBusesNum();
      return largeBus;
    } else {
      return null;
    }
  }

  public BusStrategy getBusStrategy() {
    return busStrategy;
  }
}
