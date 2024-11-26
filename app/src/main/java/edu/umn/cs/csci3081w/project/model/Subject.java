package edu.umn.cs.csci3081w.project.model;

/**
 * Subject interface.
 */
public interface Subject {

  /**
   * Adds observers to a list of observers.
   *
   * @param o Some Observer object
   */
  void addObserver(Observer o);

  /**
   * Removes an observer from the list of observers.
   *
   * @param o Some Observer object
   */
  void removeObserver(Observer o);

  /**
   * Notifies all observers to run some updating method.
   */
  void notifyObservers();
}
