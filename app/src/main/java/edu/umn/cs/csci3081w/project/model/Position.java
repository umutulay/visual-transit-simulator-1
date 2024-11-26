package edu.umn.cs.csci3081w.project.model;

/**
 * Position class.
 */
public class Position {

  private double longitude;
  private double latitude;

  /**
   * Constructor for Position.
   *
   * @param longitude longitude of thing at Position
   * @param latitude latitude of thing at Position
   */
  public Position(double longitude, double latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public double getLatitude() {
    return latitude;
  }

}
