package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StorageFacilityTest {

  /**
   * Default constructor test for StorageFacility.
   */
  @Test
  public void defaultStorageFacilityConstructorTest() {
    StorageFacility facility = new StorageFacility();
    assertEquals(0, facility.getSmallBusNum());
    assertEquals(0, facility.getLargeBusNum());
    assertEquals(0, facility.getDieselTrainNum());
    assertEquals(0, facility.getElectricTrainNum());
  }

  /**
   * Constructor test for StorageFacility.
   */
  @Test
  public void storageFacilityConstructorTest() {
    StorageFacility facility = new StorageFacility(1, 2, 3, 4);
    assertEquals(1, facility.getSmallBusNum());
    assertEquals(2, facility.getLargeBusNum());
    assertEquals(3, facility.getElectricTrainNum());
    assertEquals(4, facility.getDieselTrainNum());
  }

  /**
   * Tests the method for incrementing vehicles in the StorageFacility.
   */
  @Test
  public void storageFacilityIncrementTest() {
    StorageFacility facility = new StorageFacility(1, 2, 3, 4);
    facility.incrementSmallBusesNum();
    assertEquals(2, facility.getSmallBusNum());
    facility.incrementLargeBusesNum();
    assertEquals(3, facility.getLargeBusNum());
    facility.incrementElectricTrainsNum();
    assertEquals(4, facility.getElectricTrainNum());
    facility.incrementDieselTrainsNum();
    assertEquals(5, facility.getDieselTrainNum());
  }

  /**
   * Tests the method for decrementing vehicles in the StorageFacility.
   */
  @Test
  public void storageFacilityDecrementTest() {
    StorageFacility facility = new StorageFacility(1, 2, 3, 4);
    facility.decrementSmallBusesNum();
    assertEquals(0, facility.getSmallBusNum());
    facility.decrementLargeBusesNum();
    assertEquals(1, facility.getLargeBusNum());
    facility.decrementElectricTrainsNum();
    assertEquals(2, facility.getElectricTrainNum());
    facility.decrementDieselTrainsNum();
    assertEquals(3, facility.getDieselTrainNum());
  }

}
