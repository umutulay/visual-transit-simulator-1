package edu.umn.cs.csci3081w.project.model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Vehicle class.
 * This class is an abstract class that represents a vehicle in the transit simulation.
 */
public abstract class Vehicle implements Observer {
  private int id;
  private int capacity;
  //the speed is in distance over a time unit
  private double speed;
  private PassengerLoader loader;
  private PassengerUnloader unloader;
  private List<Passenger> passengers;
  private String name;
  private Position position;
  private Line line;
  private double distanceRemaining;
  private Stop nextStop;
  private int co2Index = 0;
  private int[] co2Array = new int[5];

  /**
   * Constructor for a vehicle.
   *
   * @param id       vehicle identifier
   * @param capacity vehicle capacity
   * @param speed    vehicle speed
   * @param loader   passenger loader for vehicle
   * @param unloader passenger unloader for vehicle
   * @param line     line the vehicle is on
   */
  public Vehicle(int id, int capacity, double speed, PassengerLoader loader,
                 PassengerUnloader unloader, Line line) {
    this.id = id;
    this.capacity = capacity;
    this.speed = speed;
    this.loader = loader;
    this.unloader = unloader;
    this.passengers = new ArrayList<Passenger>();
    this.line = line;
    this.distanceRemaining = 0;
    this.nextStop = line.getOutboundRoute().getNextStop();
    setName(line.getOutboundRoute().getName() + id);
    setPosition(new Position(nextStop.getPosition().getLongitude(),
        nextStop.getPosition().getLatitude()));
  }

  /**
   * Reports statistics for the vehicle.
   *
   * @param out stream for printing
   */
  public abstract void report(PrintStream out);

  /**
   * Determines if both routes are complete.
   *
   * @return True if both routes are complete. False otherwise.
   */
  public boolean isTripComplete() {
    return line.getOutboundRoute().isAtEnd() && line.getInboundRoute().isAtEnd();
  }

  /**
   * Simulates loading a passenger onto a vehicle.
   *
   * @param newPassenger Passenger being loaded
   * @return Passengers loaded onto the vehicle
   */
  public int loadPassenger(Passenger newPassenger) {
    return getPassengerLoader().loadPassenger(newPassenger, getCapacity(), getPassengers());
  }

  /**
   * Moves the vehicle on its route.
   */
  public void move() {
    // update passengers FIRST
    // new passengers will get "updated" when getting on the bus
    for (Passenger passenger : getPassengers()) {
      passenger.pasUpdate();
    }
    //actually move
    double speed = updateDistance();
    if (!isTripComplete() && distanceRemaining <= 0) {
      //load & unload
      int passengersHandled = handleStop();
      if (passengersHandled >= 0) {
        distanceRemaining = 0;
      }
      //switch to next stop
      toNextStop();
    }

    // Get the correct route and early exit
    Route currentRoute = line.getOutboundRoute();
    if (line.getOutboundRoute().isAtEnd()) {
      if (line.getInboundRoute().isAtEnd()) {
        return;
      }
      currentRoute = line.getInboundRoute();
    }
    Stop prevStop = currentRoute.prevStop();
    Stop nextStop = currentRoute.getNextStop();
    double distanceBetween = currentRoute.getNextStopDistance();
    // the ratio shows us how far from the previous stop are we in a ratio from 0 to 1
    double ratio;
    // check if we are at the first stop
    if (distanceBetween - 0.00001 < 0) {
      ratio = 1;
    } else {
      ratio = distanceRemaining / distanceBetween;
      if (ratio < 0) {
        ratio = 0;
        distanceRemaining = 0;
      }
    }
    double newLongitude = nextStop.getPosition().getLongitude() * (1 - ratio)
        + prevStop.getPosition().getLongitude() * ratio;
    double newLatitude = nextStop.getPosition().getLatitude() * (1 - ratio)
        + prevStop.getPosition().getLatitude() * ratio;
    setPosition(new Position(newLongitude, newLatitude));
  }

  /**
   * Update the simulation state for this vehicle.
   */
  public void update() {
    move();
  }

  public abstract int getCurrentCO2Emission();

  public int getId() {
    return id;
  }

  public int getCapacity() {
    return capacity;
  }

  public double getSpeed() {
    return speed;
  }

  public PassengerLoader getPassengerLoader() {
    return loader;
  }

  public PassengerUnloader getPassengerUnloader() {
    return unloader;
  }

  public List<Passenger> getPassengers() {
    return passengers;
  }

  public Stop getNextStop() {
    return nextStop;
  }

  public Line getLine() {
    return line;
  }

  public double getDistanceRemaining() {
    return distanceRemaining;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  /**
   * Simulates picking up and dropping off passengers at a stop.
   *
   * @return Amount of passengers picked up and dropped off.
   */
  private int handleStop() {
    // This function handles arrival at a train stop
    int passengersHandled = 0;
    // unloading passengers
    passengersHandled += unloadPassengers();
    // loading passengers
    passengersHandled += nextStop.loadPassengers(this);
    if (passengersHandled != 0) {
      distanceRemaining = 0;
    }
    return passengersHandled;
  }

  /**
   * Simulate unloading Passengers from a vehicle.
   *
   * @return Passengers unloaded
   */
  private int unloadPassengers() {
    return getPassengerUnloader().unloadPassengers(getPassengers(), nextStop);
  }

  /**
   * Sets the next stop of the current route and updates the distance remaining.
   */
  private void toNextStop() {
    //current stop
    currentRoute().nextStop();
    if (!isTripComplete()) {
      // it's important we call currentRoute() again,
      // as nextStop() may have caused it to change.
      nextStop = currentRoute().getNextStop();
      distanceRemaining +=
          currentRoute().getNextStopDistance();
      // note, if distanceRemaining was negative because we
      // had extra time left over, that extra time is
      // effectively counted towards the next stop
    } else {
      nextStop = null;
      distanceRemaining = 999;
    }
  }

  /**
   * Returns which Route the vehicle is currently on.
   *
   * @return Current Route
   */
  private Route currentRoute() {
    // Figure out if we're on the outgoing or incoming route
    if (!line.getOutboundRoute().isAtEnd()) {
      return line.getOutboundRoute();
    }
    return line.getInboundRoute();
  }

  /**
   * Updates the distance remaining.
   *
   * @return The speed of the vehicle
   */
  private double updateDistance() {
    // Updates the distance remaining and returns the effective speed of the vehicle
    // vehicle does not move if speed is negative or train is at end of route
    if (isTripComplete()) {
      return 0;
    }
    if (getSpeed() < 0) {
      return 0;
    }
    distanceRemaining -= getSpeed();
    return getSpeed();
  }

  /**
   * Tracks the last 5 tics of CO2.
   */
  @Override
  public void updateCO2() {
    co2Array[co2Index] = this.getCurrentCO2Emission();
    co2Index = (co2Index + 1) % 5;
  }

  public int[] getCo2Array() {
    return co2Array;
  }

  public int getCo2Index() {
    return co2Index;
  }

}
