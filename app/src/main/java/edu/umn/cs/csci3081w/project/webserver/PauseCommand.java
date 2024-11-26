package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;

/**
 * PauseCommand class.
 */
public class PauseCommand extends SimulatorCommand {
  private VisualTransitSimulator visSim;

  /**
   * Constructor for PauseCommand.
   *
   * @param visSim Some initialized VisualTransitSimulator object
   */
  public PauseCommand(VisualTransitSimulator visSim) {
    this.visSim = visSim;
  }

  /**
   * Tells the simulator to pause the simulation.
   *
   * @param session object representing the simulation session
   * @param command object containing the original command
   */
  @Override
  public void execute(WebServerSession session, JsonObject command) {
    visSim.togglePause();
  }
}
