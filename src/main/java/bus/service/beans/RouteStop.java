package bus.service.beans;

public class RouteStop {

    private long id;
    private double altitude;
    private double latitude;
    private boolean isBackWay;

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
}
