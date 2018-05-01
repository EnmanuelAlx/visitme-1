package visit.me.gil.mota.visitme.viewModels;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import visit.me.gil.mota.visitme.models.Interval;
import visit.me.gil.mota.visitme.useCases.CreateVisit;
import visit.me.gil.mota.visitme.useCases.UseCase;

/**
 * Created by mota on 17/4/2018.
 */

public class GuestDataViewModel extends Observable {

    private Contract contract;
    public ObservableField<String> cedula;
    public ObservableField<String> name;
    public ObservableField<String> day;
    public ObservableField<String> hour;
    public ObservableField<String> visitType;
    private String dayF;
    private Bundle arguments;
    private List<Interval> intervals;


    public GuestDataViewModel(Contract contract) {
        this.contract = contract;
        cedula = new ObservableField<>("");
        name = new ObservableField<>("");
        day = new ObservableField<>("Dia");
        hour = new ObservableField<>("Hora");
        visitType = new ObservableField<>("");
        cedula.addOnPropertyChangedCallback(cedulaChanged);
        intervals  = new ArrayList<>();
        intervals.add(new Interval(1,1200, 1800));
        intervals.add(new Interval(3,1200, 1800));
        intervals.add(new Interval(5,1200, 1800));
        intervals.add(new Interval(1,1200, 1800));
        intervals.add(new Interval(3,1200, 1800));
        intervals.add(new Interval(5,1200, 1800));
        intervals.add(new Interval(1,1200, 1800));
        intervals.add(new Interval(3,1200, 1800));
        intervals.add(new Interval(5,1200, 1800));
    }

    public void register(View view) {
        contract.register(cedula.get(),name.get(),dayF+" "+hour.get(),intervals);
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

    }

    public List<Interval> getIntervals() {
        return intervals;
    }

    public interface Contract {
        void setError(String error);

        void showGetDay();

        void showGetTime();

        void register(String cedula, String name, String dayOfVisit, List<Interval> intervals);
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
