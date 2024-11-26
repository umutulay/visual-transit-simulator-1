package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;
import edu.umn.cs.csci3081w.project.model.Bus;
import edu.umn.cs.csci3081w.project.model.BusStrategy;
import edu.umn.cs.csci3081w.project.model.BusStrategyDay;
import edu.umn.cs.csci3081w.project.model.BusStrategyNight;
import edu.umn.cs.csci3081w.project.model.Counter;
import edu.umn.cs.csci3081w.project.model.DieselTrain;
import edu.umn.cs.csci3081w.project.model.ElectricTrain;
import edu.umn.cs.csci3081w.project.model.LargeBus;
import edu.umn.cs.csci3081w.project.model.Line;
import edu.umn.cs.csci3081w.project.model.Route;
import edu.umn.cs.csci3081w.project.model.SmallBus;
import edu.umn.cs.csci3081w.project.model.StorageFacility;
import edu.umn.cs.csci3081w.project.model.StrategyBasedBusFactory;
import edu.umn.cs.csci3081w.project.model.StrategyBasedTrainFactory;
import edu.umn.cs.csci3081w.project.model.Time;
import edu.umn.cs.csci3081w.project.model.Train;
import edu.umn.cs.csci3081w.project.model.TrainStrategy;
import edu.umn.cs.csci3081w.project.model.TrainStrategyDay;
import edu.umn.cs.csci3081w.project.model.TrainStrategyNight;
import edu.umn.cs.csci3081w.project.model.Vehicle;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * VisualTransitSimulator class.
 */
public class VisualTransitSimulator {

  private static boolean LOGGING = false;
  private int numTimeSteps = 0;
  private int simulationTimeElapsed = 0;
  private Counter counter;
  private List<Line> lines;
  private List<Route> routes;
  private List<Vehicle> activeVehicles;
  private List<Vehicle> completedTripVehicles;
  private List<Integer> vehicleStartTimings;
  private List<Integer> timeSinceLastVehicle;
  private StorageFacility storageFacility;
  private WebServerSession webServerSession;
  private boolean paused = false;
  private StrategyBasedTrainFactory trainFactory;
  private int totalTrainsSent = 0;
  private TrainStrategy trainStrategy;
  private StrategyBasedBusFactory busFactory;
  private int totalBusesSent = 0;
  private BusStrategy busStrategy;
  private int busLineDownUntil = 0;
  private int trainLineDownUntil = 0;
  Time time = new Time();
  boolean busLineDown = false;
  boolean trainLineDown = false;

  /**
   * Constructor for Simulation.
   *
   * @param configFile file containing the simulation configuration
   * @param webServerSession session associated with the simulation
   */
  public VisualTransitSimulator(String configFile, WebServerSession webServerSession) {
    this.webServerSession = webServerSession;
    this.counter = new Counter();
    ConfigManager configManager = new ConfigManager();
    configManager.readConfig(counter, configFile);
    this.lines = configManager.getLines();
    this.routes = configManager.getRoutes();
    this.activeVehicles = new ArrayList<Vehicle>();
    this.completedTripVehicles = new ArrayList<Vehicle>();
    this.vehicleStartTimings = new ArrayList<Integer>();
    this.timeSinceLastVehicle = new ArrayList<Integer>();
    this.storageFacility = configManager.getStorageFacility();
    if (this.storageFacility == null) {
      this.storageFacility = new StorageFacility(Integer.MAX_VALUE, Integer.MAX_VALUE,
          Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    LocalDateTime currentDateTime = LocalDateTime.now();
    if ((currentDateTime.getHour() >= 8) && (currentDateTime.getHour() < 18)) {
      this.trainStrategy = new TrainStrategyDay();
      this.busStrategy = new BusStrategyDay();
    } else {
      this.trainStrategy = new TrainStrategyNight();
      this.busStrategy = new BusStrategyNight();
    }
    this.trainFactory = new StrategyBasedTrainFactory(trainStrategy);
    this.busFactory = new StrategyBasedBusFactory(busStrategy);

    if (VisualTransitSimulator.LOGGING) {
      System.out.println("////Simulation Routes////");
      for (int i = 0; i < lines.size(); i++) {
        lines.get(i).getOutboundRoute().report(System.out);
        lines.get(i).getInboundRoute().report(System.out);
      }
    }
  }

  /**
   * Starts the simulation.
   *
   * @param vehicleStartTimings start timings of bus
   * @param numTimeSteps        number of time steps
   */
  public void start(List<Integer> vehicleStartTimings, int numTimeSteps) {
    this.vehicleStartTimings = vehicleStartTimings;
    this.numTimeSteps = numTimeSteps;
    for (int i = 0; i < vehicleStartTimings.size(); i++) {
      this.timeSinceLastVehicle.add(i, 0);
    }
    simulationTimeElapsed = 0;
  }

  /**
   * Toggles the pause state of the simulation.
   */
  public void togglePause() {
    paused = !paused;
  }

  /**
   * Updates the simulation at each step.
   */
  public void update() {
    if (!paused) {
      simulationTimeElapsed++;
      if (simulationTimeElapsed > numTimeSteps) {
        return;
      }
      System.out.println("~~~~The simulation time is now at time step "
          + simulationTimeElapsed + "~~~~");
      // generate vehicles
      for (int i = 0; i < timeSinceLastVehicle.size(); i++) {
        if (timeSinceLastVehicle.get(i) <= 0
            && (simulationTimeElapsed - 1) % vehicleStartTimings.get(i) == 0) {
          Route outbound = lines.get(i).getOutboundRoute();
          Route inbound = lines.get(i).getInboundRoute();
          Line line = findLineBasedOnRoute(outbound);
          if (line.getType().equals(Line.BUS_LINE)
              && !busLineDown) {
            Bus busToDeploy = busFactory.generateBus(counter.getBusIdCounterAndIncrement(),
                line.shallowCopy(), totalBusesSent, storageFacility);
            if (busToDeploy != null) {
              activeVehicles.add(busToDeploy);
              time.addObserver(activeVehicles.getLast());
              this.totalBusesSent += 1;
            } else {
              counter.busIdCounter -= 1;
              //setting counter back since incremented but no bus deployed
            }
            timeSinceLastVehicle.set(i, vehicleStartTimings.get(i));
            timeSinceLastVehicle.set(i, timeSinceLastVehicle.get(i) - 1);
          } else if (line.getType().equals(Line.TRAIN_LINE)
              && !trainLineDown) {
            Train trainToDeploy = trainFactory.generateTrain(counter
                    .getTrainIdCounterAndIncrement(), line.shallowCopy(),
                totalTrainsSent, storageFacility);
            if (trainToDeploy != null) {
              activeVehicles.add(trainToDeploy);
              time.addObserver(activeVehicles.getLast());
              this.totalTrainsSent += 1;
            } else {
              counter.trainIdCounter -= 1; //setting counter
              // back since incremented but no train deployed
            }
            timeSinceLastVehicle.set(i, vehicleStartTimings.get(i));
            timeSinceLastVehicle.set(i, timeSinceLastVehicle.get(i) - 1);
          }
        } else {
          if (i == 0 && !busLineDown) {
            timeSinceLastVehicle.set(i, timeSinceLastVehicle.get(i) - 1);
          } else if (i == 1 && !trainLineDown) {
            timeSinceLastVehicle.set(i, timeSinceLastVehicle.get(i) - 1);
          }
        }
      }
      // update vehicles
      for (int i = activeVehicles.size() - 1; i >= 0; i--) {
        Vehicle currVehicle = activeVehicles.get(i);
        //if bus line is down don't update buses
        if (currVehicle.getId() < 2000 && busLineDown) {
          continue;
        }
        //if train line is down don't update trains
        if (currVehicle.getId() >= 2000 && trainLineDown) {
          continue;
        }
        currVehicle.update();
        if (currVehicle.isTripComplete()) {
          Vehicle completedTripVehicle = activeVehicles.remove(i);
          completedTripVehicles.add(completedTripVehicle);
          time.removeObserver(completedTripVehicle);
          if (completedTripVehicle instanceof SmallBus) {
            this.storageFacility.incrementSmallBusesNum();
          } else if (completedTripVehicle instanceof LargeBus) {
            this.storageFacility.incrementLargeBusesNum();
          } else if (completedTripVehicle instanceof ElectricTrain) {
            this.storageFacility.incrementElectricTrainsNum();
          } else if (completedTripVehicle instanceof DieselTrain) {
            this.storageFacility.incrementDieselTrainsNum();
          }
        } else {
          if (VisualTransitSimulator.LOGGING) {
            currVehicle.report(System.out);
          }
        }
      }
      // update routes
      for (int i = 0; i < lines.size(); i++) {
        Route currOutRoute = lines.get(i).getOutboundRoute();
        Route currInRoute = lines.get(i).getInboundRoute();
        currOutRoute.update();
        currInRoute.update();
        if (VisualTransitSimulator.LOGGING) {
          currOutRoute.report(System.out);
          currInRoute.report(System.out);
        }
      }
      // update observers
      time.updateLineStatus();
      time.incrementTime();
      busLineDown = time.getBusLineDown();
      trainLineDown = time.getTrainLineDown();
    }
  }

  /**
   * Method to find the line of a route.
   *
   * @param route route to search
   * @return Line containing route
   */
  public Line findLineBasedOnRoute(Route route) {
    for (Line line : lines) {
      if (line.getOutboundRoute().getId() == route.getId()
          || line.getInboundRoute().getId() == route.getId()) {
        return line;
      }
    }
    throw new RuntimeException("Could not find the line of the route");
  }

  /**
   * Function that simulates a vehicle line going down in the simulator.
   *
   * @param lineID ID of line in dropdown menu on the visualization module
   */
  public void lineDown(String lineID) {
    if (lineID.equals("10000")) { //BUS
      busLineDownUntil = simulationTimeElapsed + 10;
    } else if (lineID.equals("10001")) { //TRAIN
      trainLineDownUntil = simulationTimeElapsed + 10;
    }
  }

  public List<Line> getLines() {
    return lines;
  }

  public List<Vehicle> getActiveVehicles() {
    return activeVehicles;
  }

}
