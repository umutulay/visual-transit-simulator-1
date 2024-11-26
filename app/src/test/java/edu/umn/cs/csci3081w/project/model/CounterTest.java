package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CounterTest {
  private Counter counter;

  /**
   * Setup operations before each test runs.
   */
  @BeforeEach
  public void seUp() {
    counter = new Counter();
  }

  /**
   * Tests if testGetRouteIdCounterAndIncrement function works properly.
   */
  @Test
  public void testGetRouteIdCounterAndIncrement() {
    assertEquals(10, counter.getRouteIdCounterAndIncrement());
    assertEquals(11, counter.getRouteIdCounterAndIncrement());
    assertEquals(12, counter.getRouteIdCounterAndIncrement());
  }

  /**
   * Tests if testGetStopIdCounterAndIncrement function works properly.
   */
  @Test
  public void testGetStopIdCounterAndIncrement() {
    assertEquals(100, counter.getStopIdCounterAndIncrement());
    assertEquals(101, counter.getStopIdCounterAndIncrement());
    assertEquals(102, counter.getStopIdCounterAndIncrement());
  }

  /**
   * Tests if testGetBusIdCounterAndIncrement function works properly.
   */
  @Test
  public void testGetBusIdCounterAndIncrement() {
    assertEquals(1000, counter.getBusIdCounterAndIncrement());
    assertEquals(1001, counter.getBusIdCounterAndIncrement());
    assertEquals(1002, counter.getBusIdCounterAndIncrement());
  }

  /**
   * Tests if testGetTrainIdCounterAndIncrement function works properly.
   */
  @Test
  public void testGetTrainIdCounterAndIncrement() {
    assertEquals(2000, counter.getTrainIdCounterAndIncrement());
    assertEquals(2001, counter.getTrainIdCounterAndIncrement());
    assertEquals(2002, counter.getTrainIdCounterAndIncrement());
  }

  /**
   * Tests if testGetLineIdCounterAndIncrement function works properly.
   */
  @Test
  public void testGetLineIdCounterAndIncrement() {
    assertEquals(10000, counter.getLineIdCounterAndIncrement());
    assertEquals(10001, counter.getLineIdCounterAndIncrement());
    assertEquals(10002, counter.getLineIdCounterAndIncrement());
  }
}
