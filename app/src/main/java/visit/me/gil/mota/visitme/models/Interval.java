package visit.me.gil.mota.visitme.models;

import android.util.Log;

import visit.me.gil.mota.visitme.utils.Functions;

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

    public String getFromStr() {
        return Functions.intHourToStr(from);
    }

    public String getToStr() {
        return Functions.intHourToStr(to);
    }


    @Override
    public String toString() {
        return "Interval{" +
                "from=" + from +
                ", to=" + to +
                ", day=" + day +
                '}';
    }

    public void setTo(String s) {
        try {
            if (s != null)
                to = Integer.valueOf(s.replace(":", ""));
        } catch (Exception e) {
            Log.i("INTERVAL" , "SET TO EX" + s + " " + s.replace(":", ""));
        }

    }

    public void setFrom(String s) {
        try {
            if (s != null)
                from = Integer.valueOf(s.replace(":", ""));
        } catch (Exception e) {
            Log.i("INTERVAL" , "SET FROM EX" + s + " " + s.replace(":", ""));
        }


    }
}
