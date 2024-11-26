package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;

/**
 * LineIssueCommand class.
 */
public class LineIssueCommand extends SimulatorCommand {

  private VisualTransitSimulator simulator;

  /**
   * Constructor for ObserveVehicleCommand.
   *
   * @param simulator VisualTransitSimulator object
   */
  public LineIssueCommand(VisualTransitSimulator simulator) {
    this.simulator = simulator;
  }

  /**
   * Tells the simulator to display more detailed vehicle information.
   *
   * @param session object representing the simulation session
   * @param command object containing the original command
   */
  @Override
  public void execute(WebServerSession session, JsonObject command) {
    String vehicleId = command.get("id").getAsString();
    simulator.lineDown(vehicleId);
    simulator.time.lineDown(vehicleId);
  }

}
