package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;

/**
 * UpdateCommand class.
 */
public class UpdateCommand extends SimulatorCommand {

  private VisualTransitSimulator simulator;

  /**
   * Constructor for UpdateCommand.
   *
   * @param simulator Some initialized VisualTransitSimulator
   */
  public UpdateCommand(VisualTransitSimulator simulator) {
    this.simulator = simulator;
  }

  /**
   * Updates the state of the simulation.
   *
   * @param session current simulation session
   * @param command the update simulation command content
   */
  @Override
  public void execute(WebServerSession session, JsonObject command) {
    simulator.update();
  }

}
