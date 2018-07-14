package visit.me.gil.mota.visitme.views.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.otaliastudios.autocomplete.Autocomplete;
import com.otaliastudios.autocomplete.AutocompletePolicy;
import com.otaliastudios.autocomplete.AutocompletePresenter;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager;

import java.util.Calendar;
import java.util.List;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.FragmentGuestDataBinding;
import visit.me.gil.mota.visitme.models.Interval;
import visit.me.gil.mota.visitme.models.User;
import visit.me.gil.mota.visitme.utils.CompaniesPresenter;
import visit.me.gil.mota.visitme.utils.Pnotify;
import visit.me.gil.mota.visitme.viewModels.GuestDataViewModel;
import visit.me.gil.mota.visitme.views.adapters.UserAdapter;
import visit.me.gil.mota.visitme.views.adapters.IntervalAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuestDataFragment extends Fragment implements GuestDataViewModel.Contract,
        com.wdullaer.materialdatetimepicker.time.TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener {


    private FragmentGuestDataBinding binding;
    private GuestDataViewModel viewModel;
    private Contract contract;
    private IntervalAdapter adapter;

    public GuestDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_guest_data, container, false);
        viewModel = new GuestDataViewModel(this);
        viewModel.setArguments(getArguments());
        binding.setViewModel(viewModel);
        setupAdapter(binding);
        if (viewModel.isSporadic())
            setupAutoComplete();
        return binding.getRoot();
    }

    private void setupAutoComplete() {
        CompaniesPresenter presenter = new CompaniesPresenter(this.getActivity(), viewModel);
        Autocomplete c = Autocomplete.on(binding.nameField).with(presenter).build();
        viewModel.setAutoComplete(c);
    }

    private void setupAdapter(FragmentGuestDataBinding binding) {
        adapter = new IntervalAdapter(IntervalAdapter.SHOWABLE_TYPE);
        adapter.setHasStableIds(true);
        binding.intervals.setAdapter(adapter);
        adapter.setList(viewModel.getIntervals());
        binding.intervals.setLayoutManager(new FlowLayoutManager().maxItemsPerLine(3));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            contract = (Contract) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement MyInterface ");
        }
    }

    @Override
    public void setError(String error) {
        Pnotify.makeText(getActivity(), error, Toast.LENGTH_SHORT, Pnotify.ERROR).show();
    }

    @Override
    public void showGetDay() {

        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setMinDate(now);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void showGetTime() {
        Calendar now = Calendar.getInstance();
        TimePickerDialog dialog = TimePickerDialog.newInstance(this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE), true);
        dialog.show(getFragmentManager(), "timeDialog");
    }

    @Override
    public void register(String cedula, String name, String dayOfVisit, String partOfDay, List<Interval> intervals) {
        contract.onFillGuestData(cedula, name, dayOfVisit, partOfDay,  intervals);
    }

    @Override
    public Context giveContext() {
        return getActivity();
    }

    @Override
    public void refreshIntervalsData() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setImage(String image) {
        Glide.with(getActivity()).load(image).placeholder(R.drawable.guy).error(R.drawable.guy).dontAnimate().into(binding.image);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        viewModel.setDay(year, monthOfYear, dayOfMonth);
    }

    @Override
    public void onTimeSet(com.wdullaer.materialdatetimepicker.time.TimePickerDialog view, int hourOfDay, int minute, int second) {
        viewModel.setTime(hourOfDay, minute);
    }


    public interface Contract {
        void onFillGuestData(String cedula, String name, String dayOfVisit, String partOfDay, List<Interval> intervals);
    }

}
