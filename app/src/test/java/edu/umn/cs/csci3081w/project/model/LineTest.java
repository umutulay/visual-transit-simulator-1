package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LineTest {

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
  }

  /**
   * Tests Line constructor.
   */
  @Test
  public void lineConstructorTest() {
    Line testLine = new Line(1, "test", "Bus", testRouteOut, testRouteIn);
    assertEquals(1, testLine.getId());
    assertEquals("test", testLine.getName());
    assertEquals("Bus", testLine.getType());
    assertSame(testRouteOut, testLine.getOutboundRoute());
    assertSame(testRouteIn, testLine.getInboundRoute());
  }

  /**
   * Tests the Line report method.
   */
  @Test
  public void reportTest() {
    Line testLine = new Line(1, "test", "Bus", testRouteOut, testRouteIn);
    try {
      final Charset charset = StandardCharsets.UTF_8;
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      PrintStream testStream = new PrintStream(outputStream, true, charset.name());
      testLine.report(testStream);
      outputStream.flush();
      String data = new String(outputStream.toByteArray(), charset);

      outputStream.reset();
      testLine.getOutboundRoute().report(testStream);
      outputStream.flush();
      String outboundData = new String(outputStream.toByteArray(), charset);

      outputStream.reset();
      testLine.getInboundRoute().report(testStream);
      outputStream.flush();
      String inboundData = new String(outputStream.toByteArray(), charset);
      testStream.close();
      outputStream.close();

      String strToCompare =
          "====Line Info Start====" + System.lineSeparator()
              + "ID: 1" + System.lineSeparator()
              + "Name: test" + System.lineSeparator()
              + "Type: Bus" + System.lineSeparator()
              + outboundData
              + inboundData
              + "====Line Info End====" + System.lineSeparator();
      assertEquals(data, strToCompare);
    } catch (IOException ioe) {
      fail();
    }
  }

  /**
   * Tests Line shallowCopy method.
   */
  @Test
  public void shallowCopyTest() {
    Line testLine = new Line(1, "test", "Bus", testRouteOut, testRouteIn);
    Line copyLine = testLine.shallowCopy();
    assertEquals(testLine.getId(), copyLine.getId());
    assertEquals(testLine.getName(), copyLine.getName());
    assertEquals(testLine.getType(), copyLine.getType());
    assertEquals(testLine.getOutboundRoute().getId(), copyLine.getOutboundRoute().getId());
    assertEquals(testLine.getInboundRoute().getId(), copyLine.getInboundRoute().getId());
  }
}
