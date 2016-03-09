package bus.service.beans;

public class StopTime {

    private long id;
    private int hours;
    private int minutes;
    private long routeStopId;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public long getRouteStopId() {
        return routeStopId;
    }

    public void setRouteStopId(long routeStopId) {
        this.routeStopId = routeStopId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
