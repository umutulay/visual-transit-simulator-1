package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class PassengerFactoryTests {

  /**
   * Tests to see if a new passenger is correctly generated.
   */
  @Test
  public void testGeneratePassenger() {
    Passenger passenger = PassengerFactory.generate(1, 5);
    assertNotNull(passenger);
    assertEquals(0, passenger.getTimeOnVehicle());
    assertEquals(0, passenger.getWaitAtStop());
  }

  /**
   * Tests to ensure name generated is not null or empty.
   */
  @Test
  public void generateNameTest() {
    String name = PassengerFactory.nameGeneration();
    assertNotNull(name);
    assertFalse(name.isEmpty());
  }
}
