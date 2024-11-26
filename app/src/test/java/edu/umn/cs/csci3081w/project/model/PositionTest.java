package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PositionTest {

  /**
   * Constructor test for Position.
   */
  @Test
  public void positionConstructorTest() {
    Position position = new Position(1.0, 2.0);
    assertEquals(1.0, position.getLongitude());
    assertEquals(2.0, position.getLatitude());
  }
}
