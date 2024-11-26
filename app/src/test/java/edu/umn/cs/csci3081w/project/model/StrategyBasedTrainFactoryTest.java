package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class StrategyBasedTrainFactoryTest {
  private StrategyBasedTrainFactory testSbtfDay;
  private StrategyBasedTrainFactory testSbtfNight;
  private Line testLine;
  private StorageFacility storageFacility;
  private Route testRouteIn;
  private Route testRouteOut;

  /**
   * Set up the test class.
   */
  @BeforeEach
  public void setUp() {
    testSbtfDay = new StrategyBasedTrainFactory(new TrainStrategyDay());
    testSbtfNight = new StrategyBasedTrainFactory(new TrainStrategyNight());
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
   * Test the constructor of StrategyBasedTrainFactory.
   */
  @Test
  public void testConstructor() {
    assertTrue(testSbtfDay.getTrainStrategy() instanceof TrainStrategyDay);
    assertTrue(testSbtfNight.getTrainStrategy() instanceof TrainStrategyNight);
  }

  /**
   * Test the generateTrain method of StrategyBasedTrainFactory.
   */
  @Test
  public void dayTrainTest() {
    storageFacility = new StorageFacility(100, 100, 100, 100);
    for (int i = 0; i < 12; i++) {
      Train generatedTrain = testSbtfDay.generateTrain(0, testLine, i, storageFacility);
      if ((i + 1) % 4 == 0) {
        assertTrue(generatedTrain instanceof DieselTrain);
      } else {
        assertTrue(generatedTrain instanceof ElectricTrain);
      }
    }
    assertEquals(91, storageFacility.getElectricTrainNum());
    assertEquals(97, storageFacility.getDieselTrainNum());
  }

  /**
   * Test the generateTrain method of StrategyBasedTrainFactory.
   */
  @Test
  public void nightTrainTest() {
    storageFacility = new StorageFacility(100, 100, 100, 100);
    for (int i = 0; i < 12; i++) {
      Train generatedTrain = testSbtfNight.generateTrain(0, testLine, i, storageFacility);
      if ((i + 1) % 2 == 0) {
        assertTrue(generatedTrain instanceof DieselTrain);
      } else {
        assertTrue(generatedTrain instanceof ElectricTrain);
      }
    }
    assertEquals(94, storageFacility.getElectricTrainNum());
    assertEquals(94, storageFacility.getDieselTrainNum());
  }

  /**
   * Test the generateTrain method of StrategyBasedTrainFactory.
   */
  @Test
  public void emptyStorageFacility() {
    storageFacility = new StorageFacility(100, 100, 0, 100);
    Train shouldBeElectricButEmptySoNull = testSbtfDay.generateTrain(0,
        testLine, 0, storageFacility);
    assertNull(shouldBeElectricButEmptySoNull);
    storageFacility = new StorageFacility(100, 100, 100, 0);
    Train shouldBeDieselButEmptySoNull = testSbtfDay.generateTrain(0, testLine, 3, storageFacility);
    assertNull(shouldBeDieselButEmptySoNull);
  }
}
