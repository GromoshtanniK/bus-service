package bus.service.json;


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

    public SavingRoute parseSaveRouteRequest() {

        JSONObject saveRoute = new JSONObject(json);
        SavingRoute route = new SavingRoute();
        route.setRouteNumber(saveRoute.getInt("routeNumber"));

        List<SavingStop> deletedStops = parseSavingStopsFromJson(saveRoute.getJSONArray("deleted"));
        route.setDeleted(deletedStops);

        List<SavingStop> added = parseSavingStopsFromJson(saveRoute.getJSONArray("added"));
        route.setAdded(added);

        List<ChangedSavingStop> changed = parseChangedSavingStopsFromJson(saveRoute.getJSONArray("changed"));
        route.setChanged(changed);

        return route;
    }

    private List<ChangedSavingStop> parseChangedSavingStopsFromJson(JSONArray changedSavingStopsJsonArray) {
        List<ChangedSavingStop> changedSavingStops = new ArrayList<ChangedSavingStop>();

        for (int i = 0; i < changedSavingStopsJsonArray.length(); i++) {
            JSONObject stopJson = changedSavingStopsJsonArray.getJSONObject(i);

            ChangedSavingStop changedSavingStop = new ChangedSavingStop();
            changedSavingStop.setId(stopJson.getLong("id"));
            changedSavingStop.setName(stopJson.getString("name"));
            changedSavingStop.setBackWay(stopJson.getBoolean("backWay"));
            changedSavingStop.setAltitude(stopJson.getJSONArray("cords").getDouble(0));
            changedSavingStop.setLatitude(stopJson.getJSONArray("cords").getDouble(1));

            List<Time> times = parseTimesFromJson(stopJson.getJSONArray("times"));
            changedSavingStop.setTimes(times);

            changedSavingStop.setChangedName(stopJson.getBoolean("changedName"));
            changedSavingStop.setChangedCords(stopJson.getBoolean("changedCords"));

            List<Time> changedTimes = parseTimesFromJson(stopJson.getJSONArray("changedTimes"));
            changedSavingStop.setChangedTimes(changedTimes);

            List<Time> addedTimes = parseTimesFromJson(stopJson.getJSONArray("addedTimes"));
            changedSavingStop.setAddedTimes(addedTimes);

            List<Time> deletedTimes = parseTimesFromJson(stopJson.getJSONArray("deletedTime"));
            changedSavingStop.setDeletedTime(deletedTimes);


            changedSavingStops.add(changedSavingStop);
        }


        return changedSavingStops;
    }

    private List<SavingStop> parseSavingStopsFromJson(JSONArray savingStopsJsonArray) {
        List<SavingStop> savingStops = new ArrayList<SavingStop>();

        for (int i = 0; i < savingStopsJsonArray.length(); i++) {
            JSONObject stopJson = savingStopsJsonArray.getJSONObject(i);

            SavingStop savingStop = new SavingStop();
            savingStop.setId(stopJson.getLong("id"));
            savingStop.setName(stopJson.getString("name"));
            savingStop.setBackWay(stopJson.getBoolean("backWay"));
            savingStop.setAltitude(stopJson.getJSONArray("cords").getDouble(0));
            savingStop.setLatitude(stopJson.getJSONArray("cords").getDouble(1));


            List<Time> times = parseTimesFromJson(stopJson.getJSONArray("times"));
            savingStop.setTimes(times);

            savingStops.add(savingStop);
        }

        return savingStops;
    }

    private List<Time> parseTimesFromJson(JSONArray timesJsonArray) {
        List<Time> times = new ArrayList<Time>();

        for (int i = 0; i < timesJsonArray.length(); i++) {
            JSONObject timeJson = timesJsonArray.getJSONObject(i);
            Time time = new Time();
            time.setId(timeJson.getLong("id"));
            time.setHours(timeJson.getInt("hours"));
            time.setMinutes(timeJson.getInt("minutes"));
            times.add(time);
        }

        return times;
    }
}

