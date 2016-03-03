package bus.service.beans;

public class Route {
    private long id;
    private int busNumber;
    private boolean forward;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(int busNumber) {
        this.busNumber = busNumber;
    }

    public boolean isForward() {
        return forward;
    }

    public void setForward(boolean forward) {
        this.forward = forward;
    }
}
