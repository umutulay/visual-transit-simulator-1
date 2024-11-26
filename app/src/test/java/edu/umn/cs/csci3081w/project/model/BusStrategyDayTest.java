package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class BusStrategyDayTest {

  /**
   * Test the constructor.
   */
  @Test
  public void constructorTest() {
    BusStrategyDay testBusStrategyDay = new BusStrategyDay();
    assertNotNull(testBusStrategyDay);
  }

  /**
   * Test the busTypeToDeploy method.
   */
  @Test
  public void orderTest() {
    BusStrategyDay testBusStrategyDay = new BusStrategyDay();
    for (int i = 0; i < 100; i++) {
      String ithBus = testBusStrategyDay.busTypeToDeploy(i);
      if ((i + 1) % 3 == 0) {
        assertEquals(ithBus, SmallBus.SMALL_BUS);
      } else {
        assertEquals(ithBus, LargeBus.LARGE_BUS);
      }
    }
  }
}
