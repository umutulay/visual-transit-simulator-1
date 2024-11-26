package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassengerUnloaderTest {
  private PassengerLoader passengerLoader;
  private PassengerUnloader passengerUnloader;
  private List<Passenger> passengers;


  /**
   * Setup operation before each test runs.
   */
  @BeforeEach
  public void setUp() {
    passengerLoader = new PassengerLoader();
    passengerUnloader = new PassengerUnloader();
    passengers = new ArrayList<>();

  }

  /**
   * Tests if unloadPassengers unloads passengers at their destination stop.
   */
  @Test
  public void testUnloadPassengers() {
    int maxCapacity = 3;
    Passenger passenger1 = new Passenger(0, "John");
    Passenger passenger2 = new Passenger(0, "Jane");
    Passenger passenger3 = new Passenger(1, "Doe");

    Stop stop1 = new Stop(0, "test stop 1", new Position(-93.243774, 44.972392));
    Stop stop2 = new Stop(1, "test stop 2", new Position(-93.235071, 44.973580));

    passengerLoader.loadPassenger(passenger1,
        maxCapacity, passengers);
    passengerLoader.loadPassenger(passenger2,
        maxCapacity, passengers);
    passengerLoader.loadPassenger(passenger3,
        maxCapacity, passengers);
    assertEquals(3, passengers.size());

    passengerUnloader.unloadPassengers(passengers, stop1);
    assertEquals(1, passengers.size());

    passengerUnloader.unloadPassengers(passengers, stop2);
    assertEquals(0, passengers.size());

  }
}
