package bus.service.json;

import bus.service.beans.RouteStop;
import bus.service.beans.StopTime;

import java.util.ArrayList;
import java.util.List;

public class ChangedStop extends RouteStop {

    private boolean changedName;
    private boolean changedCords;
    private List<StopTime> deletedTimes = new ArrayList<StopTime>();
    private List<StopTime> addedTimes = new ArrayList<StopTime>();
    private List<StopTime> changedTimes = new ArrayList<StopTime>();

    public boolean isChangedName() {
        return changedName;
    }

    public void setChangedName(boolean changedName) {
        this.changedName = changedName;
    }

    public boolean isChangedCords() {
        return changedCords;
    }

    public void setChangedCords(boolean changedCords) {
        this.changedCords = changedCords;
    }

    public List<StopTime> getDeletedTimes() {
        return deletedTimes;
    }

    public void setDeletedTimes(List<StopTime> deletedTimes) {
        this.deletedTimes = deletedTimes;
    }

    public List<StopTime> getAddedTimes() {
        return addedTimes;
    }

    public void setAddedTimes(List<StopTime> addedTimes) {
        this.addedTimes = addedTimes;
    }

    public List<StopTime> getChangedTimes() {
        return changedTimes;
    }

    public void setChangedTimes(List<StopTime> changedTimes) {
        this.changedTimes = changedTimes;
    }
}
