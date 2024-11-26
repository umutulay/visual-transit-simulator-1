package edu.umn.cs.csci3081w.project.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Time class that is a subject in the observer pattern. It is responsible for keeping track of
 * the current time and notifying all observers of the time change.
 */
public class Time implements Subject {

  private List<Observer> observers = new ArrayList<Observer>();
  
  /**
   * The current time in the simulation.
   */
  public int currentTime = 0;
  private int busLineDownUntil = 0;
  private int trainLineDownUntil = 0;
  private boolean trainLineDown = false;
  private boolean busLineDown = false;


  /**
   * Uses an observer that is mapped to an Observer and then adds the Observer to the Observer list.
   *
   * @param o Some Observer object
   */
  @Override
  public void addObserver(Observer o) {
    observers.add(o);
  }

  /**
   * Uses an observer that is mapped to an Observer and then removes the Observer.
   *
   * @param o Some Observer object
   */
  @Override
  public void removeObserver(Observer o) {
    observers.remove(o);
  }


  /**
   * Run all observers update method.
   */
  @Override
  public void notifyObservers() {
    for (Observer o : observers) {
      if (o.getId() < 2000 && !busLineDown) { //BUS
        o.updateCO2();
      }
      if (o.getId() >= 2000 && !trainLineDown) { //TRAIN
        o.updateCO2();
      }
    }
  }

  /**
   * Increment current time and then notify observers of the change so they can run
   * their update method.
   */
  public void incrementTime() {
    currentTime++;
    notifyObservers();
  }

  /**
   * Function that simulates a vehicle line going down in the simulator.
   *
   * @param lineID ID of line in dropdown menu on the visualization module
   */
  public void lineDown(String lineID) {
    if (lineID.equals("10000")) { //BUS
      busLineDownUntil = currentTime + 10;
    } else if (lineID.equals("10001")) { //TRAIN
      trainLineDownUntil = currentTime + 10;
    }
  }

  /**
   * Updates if the bus or train line is down or up. True means that the line is down.
   * False means it is not.
   */
  public void updateLineStatus() {
    if (currentTime < busLineDownUntil) {
      busLineDown = true;
    } else {
      busLineDown = false;
    }
    if (currentTime < trainLineDownUntil) {
      trainLineDown = true;
    } else {
      trainLineDown = false;
    }
  }

  public List<Observer> getObservers() {
    return observers;
  }

  public boolean getBusLineDown() {
    return busLineDown;
  }

  public boolean getTrainLineDown() {
    return trainLineDown;
  }

  /**
   * FOR TESTING ONLY
   * Returns the size of the observer list.
   *
   * @return observer list size
   */
  public int getObserverCount() {
    return observers.size();
  }

}
