package edu.umn.cs.csci3081w.project.model;

/**
 * StorageFacility class.
 */
public class StorageFacility {
  private int smallBusNum;
  private int largeBusNum;
  private int electricTrainNum;
  private int dieselTrainNum;

  /**
   * Default constructor for StorageFacility.
   */
  public StorageFacility() {
    smallBusNum = 0;
    largeBusNum = 0;
    electricTrainNum = 0;
    dieselTrainNum = 0;
  }

  /**
   * Constructor for StorageFacility.
   *
   * @param smallBusesNum Initial small buses in storage
   * @param largeBusesNum Initial large buses in storage
   * @param electricTrainsNum Initial electric trains in storage
   * @param dieselTrainsNum Initial diesel trains in storage
   */
  public StorageFacility(int smallBusesNum, int largeBusesNum,
                         int electricTrainsNum, int dieselTrainsNum) {
    this.smallBusNum = smallBusesNum;
    this.largeBusNum = largeBusesNum;
    this.electricTrainNum = electricTrainsNum;
    this.dieselTrainNum = dieselTrainsNum;
  }

  /**
   * Decrement smallBusNum by 1.
   */
  public void decrementSmallBusesNum() {
    smallBusNum--;
  }

  /**
   * Decrement largeBusNum by 1.
   */
  public void decrementLargeBusesNum() {
    largeBusNum--;
  }

  /**
   * Decrement electricTrainNum by 1.
   */
  public void decrementElectricTrainsNum() {
    electricTrainNum--;
  }

  /**
   * Decrement dieselTrainNum by 1.
   */
  public void decrementDieselTrainsNum() {
    dieselTrainNum--;
  }

  /**
   * Increment smallBusNum by 1.
   */
  public void incrementSmallBusesNum() {
    smallBusNum++;
  }

  /**
   * Increment largeBusNum by 1.
   */
  public void incrementLargeBusesNum() {
    largeBusNum++;
  }

  /**
   * Increment electricTrainsNum by 1.
   */
  public void incrementElectricTrainsNum() {
    electricTrainNum++;
  }

  /**
   * Increment dieselTrainsNum by 1.
   */
  public void incrementDieselTrainsNum() {
    dieselTrainNum++;
  }

  public int getSmallBusNum() {
    return this.smallBusNum;
  }

  public void setSmallBusNum(int smallBusNum) {
    this.smallBusNum = smallBusNum;
  }

  public int getElectricTrainNum() {
    return electricTrainNum;
  }

  public void setElectricTrainNum(int electricTrainNum) {
    this.electricTrainNum = electricTrainNum;
  }

  public int getDieselTrainNum() {
    return dieselTrainNum;
  }

  public void setDieselTrainNum(int dieselTrainNum) {
    this.dieselTrainNum = dieselTrainNum;
  }

  public int getLargeBusNum() {
    return largeBusNum;
  }

  public void setLargeBusNum(int largeBusNum) {
    this.largeBusNum = largeBusNum;
  }
}
