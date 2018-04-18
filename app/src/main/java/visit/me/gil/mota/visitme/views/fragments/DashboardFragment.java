package visit.me.gil.mota.visitme.views.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Observable;
import java.util.Observer;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.FragmentDashboardBinding;
import visit.me.gil.mota.visitme.databinding.FragmentVisitsBinding;
import visit.me.gil.mota.visitme.viewModels.DashboardViewModel;
import visit.me.gil.mota.visitme.viewModels.VisitsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements Observer, TabLayout.OnTabSelectedListener {

    private FragmentDashboardBinding binding;
    private DashboardViewModel viewModel;
    private TabLayout tabLayout;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
        viewModel = new DashboardViewModel(getContext());
        binding.setViewModel(viewModel);
        setupObserver(viewModel);
        setupTabs();
        return binding.getRoot();
    }

    private void setupTabs() {
        tabLayout = binding.tabs;
        tabLayout.addTab(tabLayout.newTab().setText("ALERTAS"));
        tabLayout.addTab(tabLayout.newTab().setText("INFORMACION"));
        tabLayout.addTab(tabLayout.newTab().setText("OTRAS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(this);
    }

    private void setupObserver(Observable observable)
    {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof VisitsViewModel)
        {
            VisitsViewModel appViewModel = (VisitsViewModel) observable;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
