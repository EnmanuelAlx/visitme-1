package visit.me.gil.mota.visitme.viewModels;

import android.content.Context;
import android.databinding.ObservableField;

import java.util.Calendar;
import java.util.Observable;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.models.Interval;

/**
 * Created by mota on 28/4/2018.
 */

public class ItemIntervalViewModel extends Observable {
    public ObservableField<String> day;
    public ObservableField<String> from;
    public ObservableField<String> to;
    private Interval interval;
    private Context context;
    public ItemIntervalViewModel(Context context, Interval interval) {

        day = new ObservableField<>("");
        from = new ObservableField<>("");
        to = new ObservableField<>("");
        this.context = context;
        setInterval(interval);
    }

    public void setInterval(Interval interval){
        this.interval = interval;
        day.set(context.getResources().getStringArray(R.array.days)[interval.getDay()]);
        from.set(""+interval.getFrom());
        to.set(""+interval.getTo());
    }
}
