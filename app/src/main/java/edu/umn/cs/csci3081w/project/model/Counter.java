package edu.umn.cs.csci3081w.project.model;

/**
 * Counter class.
 */
public class Counter {

  /**
   * Route ID counter.
   */
  public int routeIdCounter = 10;
  /**
   * Stop ID counter.
   */
  public int stopIdCounter = 100;
  /**
   * Bus ID counter.
   */
  public int busIdCounter = 1000;
  /**
   * Train ID counter.
   */
  public int trainIdCounter = 2000;
  /**
   * Line ID counter.
   */
  public int lineIdCounter = 10000;

  /**
   * Constructor.
   */
  public Counter() {

  }

  public int getRouteIdCounterAndIncrement() {
    return routeIdCounter++;
  }

  public int getStopIdCounterAndIncrement() {
    return stopIdCounter++;
  }

  public int getBusIdCounterAndIncrement() {
    return busIdCounter++;
  }

  public int getTrainIdCounterAndIncrement() {
    return trainIdCounter++;
  }

  public int getLineIdCounterAndIncrement() {
    return lineIdCounter++;
  }

}
