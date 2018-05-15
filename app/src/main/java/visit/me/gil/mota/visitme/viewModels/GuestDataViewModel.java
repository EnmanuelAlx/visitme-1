package visit.me.gil.mota.visitme.viewModels;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import visit.me.gil.mota.visitme.Consts;
import visit.me.gil.mota.visitme.models.Interval;
import visit.me.gil.mota.visitme.views.dialogs.IntervalsDialog;

/**
 * Created by mota on 17/4/2018.
 */

public class GuestDataViewModel extends Observable implements IntervalsDialog.Result {

    private Contract contract;
    public ObservableField<String> cedula;
    public ObservableField<String> name;
    public ObservableField<String> day;
    public ObservableField<String> hour;
    public ObservableField<String> visitType;
    public ObservableField<Integer> partOfDay;
    public ObservableField<String> companions;
    private String dayF;
    private Bundle arguments;
    private List<Interval> intervals;



    public GuestDataViewModel(Contract contract) {
        this.contract = contract;
        cedula = new ObservableField<>("");
        name = new ObservableField<>("");
        day = new ObservableField<>("Dia");
        hour = new ObservableField<>("Hora");
        partOfDay = new ObservableField<>(0);
        companions = new ObservableField<>("");
        visitType = new ObservableField<>("");
        cedula.addOnPropertyChangedCallback(cedulaChanged);
        intervals  = new ArrayList<>();
        intervals.add(new Interval(0,0, 2400));

    }

    public void register(View view) {
        contract.register(cedula.get(),name.get(),dayF, Consts.PART_OF_DAYS[partOfDay.get()], Integer.valueOf(companions.get()), intervals);
    }



    public void selectDay(View view){
        contract.showGetDay();
    }

    public void selectHour(View view){
        contract.showGetTime();
    }

    public void setArguments(Bundle arguments) {
        this.arguments = arguments;
        visitType.set(arguments.getString("VISIT_TYPE"));
    }

    public void setDay(int year, int monthOfYear, int dayOfMonth) {
        monthOfYear++;
        String d = dayOfMonth > 9 ? ""+dayOfMonth : "0"+dayOfMonth;
        String m = monthOfYear > 9 ? ""+monthOfYear : "0"+monthOfYear;
        day.set(d+"/"+m+"/"+year);
        dayF = m+"-"+d+"-"+year;
    }

    public void setTime(int hourOfDay, int minute) {
        String h = hourOfDay > 9 ? ""+hourOfDay : "0"+hourOfDay;
        String m = minute > 9 ? ""+minute : "0"+minute;
        hour.set(""+h+":"+m);
    }


    public void modifyIntervals(View view){
        IntervalsDialog dialog = new IntervalsDialog(contract.giveContext(), this, intervals);
        dialog.show();
    }

    public List<Interval> getIntervals() {
        return intervals;
    }

    @Override
    public void onClose(List<Interval> intervals) {
        Log.i("INTERVAL","II"+intervals.toString() + "III"+this.intervals);
        contract.refreshIntervalsData();
    }

    @Override
    public void showError(String err) {
        contract.setError(err);
    }

    public interface Contract {
        void setError(String error);

        void showGetDay();

        void showGetTime();

        void register(String cedula, String name, String dayOfVisit, String partOfDay, int companions, List<Interval> intervals);

        Context giveContext();

        void refreshIntervalsData();
    }

    private android.databinding.Observable.OnPropertyChangedCallback cedulaChanged =
            new android.databinding.Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(android.databinding.Observable observable, int i) {
                    onCedulaChanged();
                }
            };

    private void onCedulaChanged()
    {

    }
}
