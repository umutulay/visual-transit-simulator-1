package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;
import edu.umn.cs.csci3081w.project.model.Observer;
import edu.umn.cs.csci3081w.project.model.Vehicle;
import java.util.List;

/**
 * ObserveVehicleCommand class.
 */
public class ObserveVehicleCommand extends SimulatorCommand {

  private VisualTransitSimulator simulator;

  /**
   * Constructor for ObserveVehicleCommand.
   *
   * @param simulator VisualTransitSimulator object
   */
  public ObserveVehicleCommand(VisualTransitSimulator simulator) {
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
    String text = vehicleId + "\n" + "-----------------------------\n";
    List<Observer> observers = simulator.time.getObservers();
    for (int i = 0; i < observers.size(); i++) {
      Observer observer = observers.get(i);
      if (observer.getId() == command.get("id").getAsInt()) {
        text += "Type: ";
        if (command.get("id").getAsInt() < 2000) {
          text += "Bus\n";
        } else {
          text += "Train\n";
        }
        text += "Position: (" + observer.getPosition().getLongitude() + ", "
            + observer.getPosition().getLatitude() + ")\n";
        text += "Passengers: " + observer.getPassengers().size() + "\n";
        text += "CO2: ";
        String tempText = "";
        //the index is 1 ahead of the most recent addition to the array
        int j = observer.getCo2Index();
        //add the least recently added CO2 data to temporary string first
        while (true) {
          tempText = observer.getCo2Array()[j] + " " + tempText;
          //array only stores the last 5 tics of recorded CO2 information
          j = (j + 1) % 5;
          if (j == observer.getCo2Index()) {
            break;
          }
        }
        text += tempText;
        break;
      }
    }
    JsonObject data = new JsonObject();
    data.addProperty("command", "observedVehicle");
    data.addProperty("text", text);
    session.sendJson(data);
  }

}
