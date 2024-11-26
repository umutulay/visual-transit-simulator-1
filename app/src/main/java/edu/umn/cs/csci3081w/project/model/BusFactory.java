package edu.umn.cs.csci3081w.project.model;

/**
 * BusFactory class.
 */
public abstract class BusFactory {
  /**
   * Generate a bus.
   *
   * @param id ID for bus
   * @param line route of in/out bound
   * @param totalBusesSent Buses sent from StorageFacility
   * @param storageFacility StorageFacility containing amount of buses
   * @return new Bus
   */
  public abstract Bus generateBus(int id, Line line, int totalBusesSent,
                                  StorageFacility storageFacility);
}
