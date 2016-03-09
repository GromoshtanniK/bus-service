package bus.service.beans;

import java.util.ArrayList;
import java.util.List;

public class RouteStop {

    private long id;
    private double altitude;
    private double latitude;
    private String stopName;
    private boolean isBackWay;
    private List<StopTime> stopTimes = new ArrayList<StopTime>();
    private long routeId;


    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public List<StopTime> getStopTimes() {
        return stopTimes;
    }

    public void setStopTimes(List<StopTime> stopTimes) {
        this.stopTimes = stopTimes;
    }

    public boolean isBackWay() {
        return isBackWay;
    }

    public void setIsBackWay(boolean isBackWay) {
        this.isBackWay = isBackWay;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }
}
