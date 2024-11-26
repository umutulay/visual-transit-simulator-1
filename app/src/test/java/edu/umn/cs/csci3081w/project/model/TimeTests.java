package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TimeTests {
  private Bus testBus1;
  private Bus testBus2;
  private Time testTime;

  private Route testRouteIn;
  private Route testRouteOut;


  /**
   * Setup operations before each test runs.
   */
  @BeforeEach
  public void setUp() {
    List<Stop> stopsIn = new ArrayList<Stop>();
    Stop stop1 = new Stop(0, "test stop 1", new Position(-93.243774, 44.972392));
    Stop stop2 = new Stop(1, "test stop 2", new Position(-93.235071, 44.973580));
    stopsIn.add(stop1);
    stopsIn.add(stop2);
    List<Double> distancesIn = new ArrayList<>();
    distancesIn.add(0.843774422231134);
    List<Double> probabilitiesIn = new ArrayList<Double>();
    probabilitiesIn.add(.025);
    probabilitiesIn.add(0.3);
    PassengerGenerator generatorIn = new RandomPassengerGenerator(stopsIn, probabilitiesIn);

    testRouteIn = new Route(0, "testRouteIn",
        stopsIn, distancesIn, generatorIn);

    List<Stop> stopsOut = new ArrayList<Stop>();
    stopsOut.add(stop2);
    stopsOut.add(stop1);
    List<Double> distancesOut = new ArrayList<>();
    distancesOut.add(0.843774422231134);
    List<Double> probabilitiesOut = new ArrayList<Double>();
    probabilitiesOut.add(0.3);
    probabilitiesOut.add(.025);
    PassengerGenerator generatorOut = new RandomPassengerGenerator(stopsOut, probabilitiesOut);

    testRouteOut = new Route(1, "testRouteOut",
        stopsOut, distancesOut, generatorOut);

    testBus1 = new Bus(1, new Line(10000, "testLine", "BUS", testRouteOut, testRouteIn), 3, 1.0);
    testBus2 = new Bus(2, new Line(10001, "testLine", "BUS", testRouteOut, testRouteIn), 3, 2.0);
    testTime = new Time();
  }

  /**
   * Tests adding and removing observers.
   */
  @Test
  public void addAndRemoveObserverTest() {
    assertEquals(0, testTime.getObserverCount());
    testTime.addObserver(testBus1);
    assertEquals(1, testTime.getObserverCount());
    testTime.addObserver(testBus2);
    assertEquals(2, testTime.getObserverCount());
    testTime.removeObserver(testBus1);
    assertEquals(1, testTime.getObserverCount());
    testTime.removeObserver(testBus2);
    assertEquals(0, testTime.getObserverCount());
  }

  /**
   * Tests to ensure all observers are updated.
   */
  @Test
  public void notifyObserversTest() {
    testTime.addObserver(testBus1);
    testTime.addObserver(testBus2);
    int i = 0;
    for (int j = 1; i < 2; j = (j + 1) % 5) {
      testTime.notifyObservers();
      Observer temp = testTime.getObservers().getFirst();
      assertEquals(j, temp.getCo2Index());
      temp = testTime.getObservers().getLast();
      assertEquals(j, temp.getCo2Index());
      if (j == 0) {
        i++;
      }
    }
  }

  /**
   * Tests to ensure all observers are updated after increment time is called.
   */
  @Test
  public void incrementTimeTest() {
    testTime.addObserver(testBus1);
    testTime.addObserver(testBus2);
    int i = 0;
    for (int j = 1; i < 2; j = (j + 1) % 5) {
      testTime.incrementTime();
      Observer temp = testTime.getObservers().getFirst();
      assertEquals(j, temp.getCo2Index());
      temp = testTime.getObservers().getLast();
      assertEquals(j, temp.getCo2Index());
      if (j == 0) {
        i++;
      }
    }
  }

  /**
   * Tests if lines going down are being updated correctly.
   */
  @Test
  public void lineDownTest() {
    testTime.addObserver(testBus1);
    testTime.addObserver(testBus2);
    assertEquals(0, testTime.getObservers().getFirst().getCo2Index());
    assertEquals(0, testTime.getObservers().getLast().getCo2Index());
    testTime.incrementTime();
    assertEquals(1, testTime.getObservers().getFirst().getCo2Index());
    assertEquals(1, testTime.getObservers().getLast().getCo2Index());
    testTime.lineDown("10000");
    for (int i = 0; i < 10; i++) {
      testTime.updateLineStatus();
      testTime.incrementTime();
      assertEquals(1, testTime.getObservers().getFirst().getCo2Index());
      assertEquals(1, testTime.getObservers().getLast().getCo2Index());
    }
    testTime.updateLineStatus();
    testTime.incrementTime();
    assertEquals(2, testTime.getObservers().getFirst().getCo2Index());
    assertEquals(2, testTime.getObservers().getLast().getCo2Index());
    testTime.lineDown("10000");
    for (int i = 0; i < 5; i++) {
      testTime.updateLineStatus();
      testTime.incrementTime();
      assertEquals(2, testTime.getObservers().getFirst().getCo2Index());
      assertEquals(2, testTime.getObservers().getLast().getCo2Index());
    }
    testTime.lineDown("10000");
    for (int i = 0; i < 10; i++) {
      testTime.updateLineStatus();
      testTime.incrementTime();
      assertEquals(2, testTime.getObservers().getFirst().getCo2Index());
      assertEquals(2, testTime.getObservers().getLast().getCo2Index());
    }
    testTime.updateLineStatus();
    testTime.incrementTime();
    assertEquals(3, testTime.getObservers().getFirst().getCo2Index());
    assertEquals(3, testTime.getObservers().getLast().getCo2Index());
  }

  /**
   * Clean up our variables after each test.
   */
  @AfterEach
  public void cleanUpEach() {
    testBus1 = null;
    testBus2 = null;
  }
}
