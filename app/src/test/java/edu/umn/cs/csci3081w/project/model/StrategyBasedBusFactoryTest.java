package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * StrategyBasedBusFactoryTest tests the StrategyBasedBusFactory class.
 */
public class StrategyBasedBusFactoryTest {
  private StrategyBasedBusFactory testSbbfDay;
  private StrategyBasedBusFactory testSbbfNight;
  private Line testLine;
  private StorageFacility storageFacility;
  private Route testRouteIn;
  private Route testRouteOut;

  /**
   * Set up the test fixture.
   */
  @BeforeEach
  public void setUp() {
    testSbbfDay = new StrategyBasedBusFactory(new BusStrategyDay());
    testSbbfNight = new StrategyBasedBusFactory(new BusStrategyNight());
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
    testLine = new Line(10000, "testLine", "BUS", testRouteOut, testRouteIn);
  }

  /**
   * Test the constructor.
   */
  @Test
  public void testConstructor() {
    assertTrue(testSbbfDay.getBusStrategy() instanceof BusStrategyDay);
    assertTrue(testSbbfNight.getBusStrategy() instanceof BusStrategyNight);
  }

  /**
   * Test the generateBus method.
   */
  @Test
  public void dayBusTest() {
    storageFacility = new StorageFacility(100, 100, 100, 100);
    for (int i = 0; i < 12; i++) {
      Bus generatedBus = testSbbfDay.generateBus(0, testLine, i, storageFacility);
      if ((i + 1) % 3 == 0) {
        assertTrue(generatedBus instanceof SmallBus);
      } else {
        assertTrue(generatedBus instanceof LargeBus);
      }
    }
    assertEquals(92, storageFacility.getLargeBusNum());
    assertEquals(96, storageFacility.getSmallBusNum());
  }

  /**
   * Test the generateBus method.
   */
  @Test
  public void nightBusTest() {
    storageFacility = new StorageFacility(100, 100, 100, 100);
    for (int i = 0; i < 12; i++) {
      Bus generatedBus = testSbbfNight.generateBus(0, testLine, i, storageFacility);
      if ((i + 1) % 4 == 0) {
        assertTrue(generatedBus instanceof LargeBus);
      } else {
        assertTrue(generatedBus instanceof SmallBus);
      }
    }
    assertEquals(91, storageFacility.getSmallBusNum());
    assertEquals(97, storageFacility.getLargeBusNum());
  }

  /**
   * Test the generateBus method.
   */
  @Test
  public void emptyStorageFacility() {
    storageFacility = new StorageFacility(100, 0, 100, 100);
    Bus shouldBeLargeButEmptySoNull = testSbbfDay.generateBus(0, testLine, 0, storageFacility);
    assertNull(shouldBeLargeButEmptySoNull);
    storageFacility = new StorageFacility(0, 100, 100, 100);
    Bus shouldBeSmallButEmptySoNull = testSbbfDay.generateBus(0, testLine, 2, storageFacility);
    assertNull(shouldBeSmallButEmptySoNull);
  }
}
