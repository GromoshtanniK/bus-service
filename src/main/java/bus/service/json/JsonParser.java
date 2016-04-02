package bus.service.json;


import bus.service.beans.RouteStop;
import bus.service.beans.StopTime;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    private String json;

    public JsonParser(BufferedReader reader) throws IOException {
        json = readJSONRequest(reader);
    }

    private String readJSONRequest(BufferedReader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        String tempStr;

        while ((tempStr = reader.readLine()) != null) {
            builder.append(tempStr);
        }

        return builder.toString();
    }

    public EditRoute parseEditRouteRequest() {

        JSONObject saveRoute = new JSONObject(json);
        EditRoute route = new EditRoute();
        route.setRouteNumber(saveRoute.getInt("routeNumber"));

        List<RouteStop> deletedStops = parseRouteStopsFromJson(saveRoute.getJSONArray("deleted"));
        route.setDeleted(deletedStops);

        List<RouteStop> added = parseRouteStopsFromJson(saveRoute.getJSONArray("added"));
        route.setAdded(added);

        List<ChangedStop> changed = parseChangedSavingStopsFromJson(saveRoute.getJSONArray("changed"));
        route.setChanged(changed);

        return route;
    }

    private List<ChangedStop> parseChangedSavingStopsFromJson(JSONArray changedSavingStopsJsonArray) {
        List<ChangedStop> changedSavingStops = new ArrayList<ChangedStop>();

        for (int i = 0; i < changedSavingStopsJsonArray.length(); i++) {
            JSONObject stopJson = changedSavingStopsJsonArray.getJSONObject(i);

            ChangedStop changedStop = new ChangedStop();
            changedStop.setId(stopJson.getLong("id"));
            changedStop.setStopName(stopJson.getString("name"));
            changedStop.setIsBackWay(stopJson.getBoolean("backWay"));
            changedStop.setAltitude(stopJson.getJSONArray("cords").getDouble(0));
            changedStop.setLatitude(stopJson.getJSONArray("cords").getDouble(1));
            changedStop.setRouteId(stopJson.getLong("routeId"));

            List<bus.service.beans.StopTime> times = parseTimesFromJson(stopJson.getJSONArray("times"));
            changedStop.setStopTimes(times);

            changedStop.setChangedName(stopJson.getBoolean("changedName"));
            changedStop.setChangedCords(stopJson.getBoolean("changedCords"));

            List<StopTime> changedTimes = parseTimesFromJson(stopJson.getJSONArray("changedTimes"));
            changedStop.setChangedTimes(changedTimes);

            List<StopTime> addedTimes = parseTimesFromJson(stopJson.getJSONArray("addedTimes"));
            changedStop.setAddedTimes(addedTimes);

            List<StopTime> deletedTimes = parseTimesFromJson(stopJson.getJSONArray("deletedTime"));
            changedStop.setDeletedTime(deletedTimes);


            changedSavingStops.add(changedStop);
        }


        return changedSavingStops;
    }

    private List<RouteStop> parseRouteStopsFromJson(JSONArray savingStopsJsonArray) {
        List<RouteStop> savingStops = new ArrayList<RouteStop>();

        for (int i = 0; i < savingStopsJsonArray.length(); i++) {
            JSONObject stopJson = savingStopsJsonArray.getJSONObject(i);

            RouteStop routeStop = new RouteStop();
            routeStop.setId(stopJson.getLong("id"));
            routeStop.setStopName(stopJson.getString("name"));
            routeStop.setIsBackWay(stopJson.getBoolean("backWay"));
            routeStop.setAltitude(stopJson.getJSONArray("cords").getDouble(0));
            routeStop.setLatitude(stopJson.getJSONArray("cords").getDouble(1));
            routeStop.setRouteId(stopJson.getLong("routeId"));

            List<bus.service.beans.StopTime> times = parseTimesFromJson(stopJson.getJSONArray("times"));
            routeStop.setStopTimes(times);

            savingStops.add(routeStop);
        }

        return savingStops;
    }

    private List<bus.service.beans.StopTime> parseTimesFromJson(JSONArray timesJsonArray) {
        List<bus.service.beans.StopTime> times = new ArrayList<bus.service.beans.StopTime>();

        for (int i = 0; i < timesJsonArray.length(); i++) {
            JSONObject timeJson = timesJsonArray.getJSONObject(i);
            bus.service.beans.StopTime time = new bus.service.beans.StopTime();
            time.setId(timeJson.getLong("id"));
            time.setHours(timeJson.getInt("hours"));
            time.setMinutes(timeJson.getInt("minutes"));
            time.setRouteStopId(timeJson.getLong("roteStopId"));
            times.add(time);
        }

        return times;
    }
}

