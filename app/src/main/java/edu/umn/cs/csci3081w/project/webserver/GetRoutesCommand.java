package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.umn.cs.csci3081w.project.model.Line;
import edu.umn.cs.csci3081w.project.model.Route;
import java.util.ArrayList;
import java.util.List;

/**
 * GetRoutesCommand retrieves routes information from the simulation.
 */
public class GetRoutesCommand extends SimulatorCommand {

  private VisualTransitSimulator simulator;

  /**
   * Constructor for GetRoutesCommand.
   *
   * @param simulator Some initialized VisualTransitSimulator object
   */
  public GetRoutesCommand(VisualTransitSimulator simulator) {
    this.simulator = simulator;
  }

  /**
   * Retrieves routes information from the simulation.
   *
   * @param session current simulation session
   * @param command the get routes command content
   */
  @Override
  public void execute(WebServerSession session, JsonObject command) {
    List<Line> lines = simulator.getLines();
    List<Route> routes = new ArrayList<>();
    for (Line line : lines) {
      routes.add(line.getOutboundRoute());
      routes.add(line.getInboundRoute());
    }
    JsonObject data = new JsonObject();
    data.addProperty("command", "updateRoutes");
    JsonArray routesArray = new JsonArray();
    for (int i = 0; i < routes.size(); i++) {
      JsonObject r = new JsonObject();
      r.addProperty("id", routes.get(i).getId());
      JsonArray stopArray = new JsonArray();
      for (int j = 0; j < (routes.get(i).getStops().size()); j++) {
        JsonObject stopStruct = new JsonObject();
        stopStruct.addProperty("id", routes.get(i).getStops().get(j).getId());
        stopStruct.addProperty("numPeople", routes.get(i).getStops().get(j).getPassengers().size());
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("longitude",
            routes.get(i).getStops().get(j).getPosition().getLongitude());
        jsonObj.addProperty("latitude",
            routes.get(i).getStops().get(j).getPosition().getLatitude());
        stopStruct.add("position", jsonObj);
        stopArray.add(stopStruct);
      }
      r.add("stops", stopArray);
      routesArray.add(r);
    }
    data.add("routes", routesArray);
    session.sendJson(data);
  }
}

