package visit.me.gil.mota.visitme.viewModels;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;

import com.otaliastudios.autocomplete.Autocomplete;
import com.otaliastudios.autocomplete.AutocompleteCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import visit.me.gil.mota.visitme.Consts;
import visit.me.gil.mota.visitme.models.Interval;
import visit.me.gil.mota.visitme.models.User;
import visit.me.gil.mota.visitme.useCases.FindFirstUserThatMatch;
import visit.me.gil.mota.visitme.views.dialogs.IntervalsDialog;

/**
 * Created by mota on 17/4/2018.
 */

public class GuestDataViewModel extends Observable implements IntervalsDialog.Result, ItemUserViewModel.Contract, FindFirstUserThatMatch.Result {

    private Contract contract;
    public ObservableField<String> cedula;
    public ObservableField<String> name;
    public ObservableField<String> day;
    public ObservableField<String> hour;
    public ObservableField<String> visitType;
    public ObservableField<Integer> partOfDay;
    private String dayF;
    private Bundle arguments;
    private List<Interval> intervals;
    private Autocomplete autoComplete;
    private FindFirstUserThatMatch findFirst;

    public GuestDataViewModel(Contract contract) {
        this.contract = contract;
        cedula = new ObservableField<>("");
        name = new ObservableField<>("");
        day = new ObservableField<>("Dia");
        hour = new ObservableField<>("Hora");
        partOfDay = new ObservableField<>(0);
        visitType = new ObservableField<>("");
        cedula.addOnPropertyChangedCallback(cedulaChanged);
        intervals = new ArrayList<>();
        intervals.add(new Interval(0, 0, 2400));
        findFirst = new FindFirstUserThatMatch(this);

    }

    public void register(View view) {
        contract.register(cedula.get(), name.get(), dayF,
                Consts.PART_OF_DAYS[partOfDay.get()],
                intervals);
    }


    public void selectDay(View view) {
        contract.showGetDay();
    }

    public void selectHour(View view) {
        contract.showGetTime();
    }

    public void setArguments(Bundle arguments) {
        this.arguments = arguments;
        visitType.set(arguments.getString("VISIT_TYPE"));
    }

    public void setDay(int year, int monthOfYear, int dayOfMonth) {
        monthOfYear++;
        String d = dayOfMonth > 9 ? "" + dayOfMonth : "0" + dayOfMonth;
        String m = monthOfYear > 9 ? "" + monthOfYear : "0" + monthOfYear;
        day.set(d + "/" + m + "/" + year);
        dayF = m + "-" + d + "-" + year;
    }

    public void setTime(int hourOfDay, int minute) {
        String h = hourOfDay > 9 ? "" + hourOfDay : "0" + hourOfDay;
        String m = minute > 9 ? "" + minute : "0" + minute;
        hour.set("" + h + ":" + m);
    }


    public boolean isSporadic() {
        return visitType.get().equals("SPORADIC");
    }

    public void modifyIntervals(View view) {
        IntervalsDialog dialog = new IntervalsDialog(contract.giveContext(), this, intervals);
        dialog.show();
    }

    public List<Interval> getIntervals() {
        return intervals;
    }

    @Override
    public void onClose(List<Interval> intervals) {
        Log.i("INTERVAL", "II" + intervals.toString() + "III" + this.intervals);
        contract.refreshIntervalsData();
    }

    @Override
    public void showError(String err) {
        contract.setError(err);
    }


    @Override
    public void onClick(User u) {
        setUser(u);
        if (autoComplete != null)
            autoComplete.dismissPopup();
    }

    public void setAutoComplete(Autocomplete autoComplete) {
        this.autoComplete = autoComplete;
    }

    @Override
    public void onUserFinded(User user) {
        setUser(user);
    }

    private void setUser(User user) {
        contract.setImage(user.getImage());
        name.set(user.getName());
    }

    @Override
    public void onError(String error) {

    }

    public interface Contract {
        void setError(String error);

        void showGetDay();

        void showGetTime();

        void register(String cedula, String name, String dayOfVisit, String partOfDay, List<Interval> intervals);

        Context giveContext();

        void refreshIntervalsData();

        void setImage(String image);
    }

    private android.databinding.Observable.OnPropertyChangedCallback cedulaChanged =
            new android.databinding.Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(android.databinding.Observable observable, int i) {
                    onCedulaChanged();
                }
            };

    private void onCedulaChanged() {
        String cedula = this.cedula.get();
        if (cedula.length() > 4 && !findFirst.isRunning())
        {
            findFirst.setIdentification(cedula);
            findFirst.run();
        }
    }
}
