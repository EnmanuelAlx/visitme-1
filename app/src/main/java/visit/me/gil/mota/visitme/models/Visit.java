package visit.me.gil.mota.visitme.models;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by mota on 16/4/2018.
 */

public class Visit {
    private String _id;
    private String kind;
    private Date dayOfVisit;
    private User guest;
    private User resident;
    private Community community;
    private Interval[] intervals;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getDayOfVisit() {
        return dayOfVisit.toString();
    }

    public void setDayOfVisit(Date dayOfVisit) {
        this.dayOfVisit = dayOfVisit;
    }

    public User getGuest() {
        return guest;
    }

    public void setGuest(User guest) {
        this.guest = guest;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Interval[] getIntervals() {
        return intervals;
    }

    public void setIntervals(Interval[] intervals) {
        this.intervals = intervals;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "_id='" + _id + '\'' +
                ", kind='" + kind + '\'' +
                ", dayOfVisit=" + dayOfVisit +
                ", guest=" + guest +
                ", community=" + community +
                ", intervals=" + Arrays.toString(intervals) +
                '}';
    }

    public String getKindString() {
        return kind;
    }

    public User getResident() {
        return resident;
    }

    public void setResident(User resident) {
        this.resident = resident;
    }
}
