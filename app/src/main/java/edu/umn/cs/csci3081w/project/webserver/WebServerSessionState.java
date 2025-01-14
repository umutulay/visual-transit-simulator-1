package edu.umn.cs.csci3081w.project.webserver;

import java.util.HashMap;
import java.util.Map;

/**
 * WebServerSessionState class.
 */
public class WebServerSessionState {

  private Map<String, SimulatorCommand> commands;

  /**
   * Constructor for WebServerSessionState.
   */
  public WebServerSessionState() {
    this.commands = new HashMap<String, SimulatorCommand>();
  }

  public Map<String, SimulatorCommand> getCommands() {
    return commands;
  }
}
