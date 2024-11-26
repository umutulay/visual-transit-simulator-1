package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;

/**
 * SimulatorCommand class.
 */
public abstract class SimulatorCommand {
  /**
   * Execute the command.
   *
   * @param session The session to execute the command on
   * @param command The command to execute
   */
  public abstract void execute(WebServerSession session, JsonObject command);
}
