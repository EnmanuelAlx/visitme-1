package visit.me.gil.mota.visitme.views.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.FragmentVisitsBinding;
import visit.me.gil.mota.visitme.models.Visit;
import visit.me.gil.mota.visitme.utils.Pnotify;
import visit.me.gil.mota.visitme.viewModels.TabedListViewModel;
import visit.me.gil.mota.visitme.viewModels.VisitsViewModel;
import visit.me.gil.mota.visitme.views.adapters.VisitAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisitsFragment extends Fragment implements Observer, TabLayout.OnTabSelectedListener,
                                                        TabedListViewModel.Interactor {

    private FragmentVisitsBinding binding;
    private VisitsViewModel viewModel;
    private TabLayout tabLayout;
    private VisitAdapter visitAdapter;
    public VisitsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_visits, container, false);
        viewModel = new VisitsViewModel(this);
        binding.setViewModel(viewModel);
        setupObserver(viewModel);
        setupTabs();
        setupListView();
        return binding.getRoot();
    }

    private void setupTabs() {
        tabLayout = binding.tabs;
        tabLayout.addTab(tabLayout.newTab().setText("ESPERADAS"));
        tabLayout.addTab(tabLayout.newTab().setText("FRECUENTES"));
        tabLayout.addTab(tabLayout.newTab().setText("ESPORADICAS"));
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
        viewModel.changeTab(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void setupListView()
    {
        visitAdapter = new VisitAdapter();
        visitAdapter.setHasStableIds(true);
        binding.list.setAdapter(visitAdapter);
        binding.list.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.refresh();
            }
        });
    }

    public void changeList(List list){
        visitAdapter.setList(list);
        binding.swipe.setRefreshing(false);
    }

    @Override
    public void loading(boolean loading) {
        binding.swipe.setRefreshing(loading);
    }

    @Override
    public void showError(String error) {
        Pnotify.makeText(getContext(),error, Toast.LENGTH_SHORT, Pnotify.ERROR).show();
        binding.swipe.setRefreshing(false);
    }

    public void add(Visit visit) {
        viewModel.add(visit);
        visitAdapter.notifyDataSetChanged();
    }
}
