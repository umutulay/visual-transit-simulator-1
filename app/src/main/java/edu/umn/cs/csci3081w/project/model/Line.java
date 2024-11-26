package edu.umn.cs.csci3081w.project.model;

import java.io.PrintStream;

/**
 * Line class.
 */
public class Line {

  /**
   * Bus line type.
   */
  public static final String BUS_LINE = "BUS_LINE";
  /**
   * Train line type.
   */
  public static final String TRAIN_LINE = "TRAIN_LINE";
  /**
   * Identifier for the line.
   */
  private int id;
  /**
   * Name of the line.
   */
  private String name;
  /**
   * Type of the line.
   */
  private String type;
  /**
   * Outbound route for the line.
   */
  private Route outboundRoute;
  /**
   * Inbound route for the line.
   */
  private Route inboundRoute;

  /**
   * Constructor for a Line.
   *
   * @param id       line identifier
   * @param name     line name
   * @param type     line type
   * @param outboundRoute outbound route
   * @param inboundRoute    inbound route
   */
  public Line(int id, String name, String type, Route outboundRoute, Route inboundRoute) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.outboundRoute = outboundRoute;
    this.inboundRoute = inboundRoute;
  }

  /**
   * Report statistics for the line.
   *
   * @param out stream for printing
   */
  public void report(PrintStream out) {
    out.println("====Line Info Start====");
    out.println("ID: " + id);
    out.println("Name: " + name);
    out.println("Type: " + type);
    outboundRoute.report(out);
    inboundRoute.report(out);
    out.println("====Line Info End====");
  }

  /**
   * Shallow copies a line.
   * This method shallow copies a line as routes are shared
   * across copied lines
   *
   * @return Copy of line
   */
  public Line shallowCopy() {
    Line shallowCopy = new Line(this.id, this.name, this.type,
        outboundRoute.shallowCopy(), inboundRoute.shallowCopy());
    return shallowCopy;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public Route getOutboundRoute() {
    return this.outboundRoute;
  }

  public Route getInboundRoute() {
    return this.inboundRoute;
  }
}
