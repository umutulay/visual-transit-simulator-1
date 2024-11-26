package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RandomPassengerGeneratorTests {

  List<Stop> stops = new ArrayList<Stop>();
  List<Double> probs = new ArrayList<Double>();

  /**
   * Setup operations before each test runs.
   */
  @BeforeEach
  public void setUp() {
    Stop stop1 = new Stop(0, "test stop 1", new Position(-93.243774, 44.972392));
    Stop stop2 = new Stop(1, "test stop 2", new Position(-93.235071, 44.973580));
    stops.add(stop1);
    stops.add(stop2);
    probs.add(0.85);
    probs.add(0.90);
  }

  /**
   * Test for randomPassengerGenerator constructor.
   */
  @Test
  public void randomPassengerGeneratorConstructorTest() {
    RandomPassengerGenerator generator = new RandomPassengerGenerator(stops, probs);
    assertEquals(2, generator.getStops().size());
    assertEquals(2, generator.getProbabilities().size());
  }

  /**
   * Tests if passengers were generated with a high probability.
   */
  @Test
  public void generatePassengersTest() {
    RandomPassengerGenerator generator = new RandomPassengerGenerator(stops, probs);
    int t = 0;
    for (int i = 0; i < 50; i++) {
      t += generator.generatePassengers();
    }
    assertTrue(t > 0);
    boolean test = generator.getStops().get(0).getPassengers().size() > 0;
    assertTrue(test);
  }
}
