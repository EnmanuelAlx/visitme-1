package visit.me.gil.mota.visitme.models;

/**
 * Created by mota on 16/4/2018.
 */

public class Interval {
    private int from;
    private int to;
    private int day;

    public Interval(int day, int from, int to) {
        this.day = day;
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
