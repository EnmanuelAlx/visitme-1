package visit.me.gil.mota.visitme.viewModels;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.Observable;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.models.Interval;

/**
 * Created by mota on 28/4/2018.
 */

public class ItemIntervalViewModel extends Observable {
    public ObservableField<Integer> daySelected;
    public ObservableField<String> day;
    public ObservableField<String> from;
    public ObservableField<String> to;
    private Interval interval;
    private Context context;
    private IntervalItemInteractor interactor;

    public ItemIntervalViewModel(Context context, final Interval interval) {

        day = new ObservableField<>("");
        from = new ObservableField<>("");
        to = new ObservableField<>("");
        daySelected = new ObservableField<>(0);
        daySelected.addOnPropertyChangedCallback(new android.databinding.Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(android.databinding.Observable sender, int propertyId) {
                Log.i("INTERVAL", "Changed !" + daySelected.get() + " " + propertyId);
            }
        });
        this.context = context;
        setInterval(interval);
    }

    public ItemIntervalViewModel(Context context, Interval interval, IntervalItemInteractor interactor) {

        day = new ObservableField<>("");
        from = new ObservableField<>("");
        to = new ObservableField<>("");
        daySelected = new ObservableField<>(0);
        this.context = context;
        this.interactor = interactor;
        setInterval(interval);
    }

    public void setInterval(Interval interval){
        this.interval = interval;
        day.set(context.getResources().getStringArray(R.array.days)[interval.getDay()]);
        from.set(""+interval.getFrom());
        to.set(""+interval.getTo());
        daySelected.set(interval.getDay());
    }

    public void remove(View view){
        interactor.remove(interval);
    }

    public void changeTime(View view){
        String tag = (String) view.getTag();

    }

    public interface IntervalItemInteractor {
        void remove(Interval interval);
    }

}
