package bus.service.json;

import java.util.ArrayList;
import java.util.List;

public class SavingRoute {

    private int routeNumber;

    private List<SavingStop> deleted = new ArrayList<SavingStop>();
    private List<SavingStop> added = new ArrayList<SavingStop>();
    private List<ChangedSavingStop> changed = new ArrayList<ChangedSavingStop>();


    public int getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }

    public List<SavingStop> getDeleted() {
        return deleted;
    }

    public void setDeleted(List<SavingStop> deleted) {
        this.deleted = deleted;
    }

    public List<SavingStop> getAdded() {
        return added;
    }

    public void setAdded(List<SavingStop> added) {
        this.added = added;
    }

    public List<ChangedSavingStop> getChanged() {
        return changed;
    }

    public void setChanged(List<ChangedSavingStop> changed) {
        this.changed = changed;
    }
}
