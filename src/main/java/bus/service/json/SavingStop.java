package bus.service.json;

import java.util.ArrayList;
import java.util.List;

public class SavingStop {

    private long id;
    private String name;
    private boolean backWay;
    private double altitude;
    private double latitude;
    private List<Time> times = new ArrayList<Time>();

    public List<Time> getTimes() {
        return times;
    }

    public void setTimes(List<Time> times) {
        this.times = times;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBackWay() {
        return backWay;
    }

    public void setBackWay(boolean backWay) {
        this.backWay = backWay;
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
}
