package bus.service.json;


import java.util.ArrayList;
import java.util.List;

public class ChangedSavingStop extends SavingStop {
    private boolean changedName;
    private boolean changedCords;
    private List<Time> deletedTime = new ArrayList<Time>();
    private List<Time> addedTimes = new ArrayList<Time>();
    private List<Time> changedTimes = new ArrayList<Time>();

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

    public List<Time> getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(List<Time> deletedTime) {
        this.deletedTime = deletedTime;
    }

    public List<Time> getAddedTimes() {
        return addedTimes;
    }

    public void setAddedTimes(List<Time> addedTimes) {
        this.addedTimes = addedTimes;
    }

    public List<Time> getChangedTimes() {
        return changedTimes;
    }

    public void setChangedTimes(List<Time> changedTimes) {
        this.changedTimes = changedTimes;
    }
}
