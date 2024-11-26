package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class BusStrategyNightTest {

  /**
   * Test the constructor.
   */
  @Test
  public void constructorTest() {
    BusStrategyNight testBusStrategyNight = new BusStrategyNight();
    assertNotNull(testBusStrategyNight);
  }

  /**
   * Test the order of buses deployed.
   */
  @Test
  public void orderTest() {
    BusStrategyNight testBusStrategyNight = new BusStrategyNight();
    for (int i = 0; i < 100; i++) {
      String ithBus = testBusStrategyNight.busTypeToDeploy(i);
      if ((i + 1) % 4 == 0) {
        assertEquals(ithBus, LargeBus.LARGE_BUS);
      } else {
        assertEquals(ithBus, SmallBus.SMALL_BUS);
      }
    }
  }
}
