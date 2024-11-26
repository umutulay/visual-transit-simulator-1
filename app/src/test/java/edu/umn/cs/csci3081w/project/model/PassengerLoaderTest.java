package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassengerLoaderTest {

  private PassengerLoader passengerLoader;
  private Passenger passenger;
  private  List<Passenger> passengers;

  /**
   * Setup operation before each test runs.
   */
  @BeforeEach
  public void setUp() {
    passengerLoader = new PassengerLoader();
    passengers = new ArrayList<>();
  }

  /**
   * Tests if LoadPassenger works correctly.
   */
  @Test
  public void testLoadPassenger() {
    int maxCapacity = 2;
    assertEquals(0, passengers.size());

    Passenger passenger1 = new Passenger(0, "John");
    Passenger passenger2 = new Passenger(0, "Jane");

    passengerLoader.loadPassenger(passenger1,
        maxCapacity, passengers);
    passengerLoader.loadPassenger(passenger2,
        maxCapacity, passengers);
    assertEquals(2, passengers.size());
  }

  //I am not sure if we need to add cases that vehicle is full

}
