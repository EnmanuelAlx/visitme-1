package visit.me.gil.mota.visitme.views.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.FragmentInvitationsBinding;
import visit.me.gil.mota.visitme.models.Visit;
import visit.me.gil.mota.visitme.viewModels.InvitationsViewModel;
import visit.me.gil.mota.visitme.views.adapters.InvitationAdapter;
import visit.me.gil.mota.visitme.views.dialogs.VisitDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvitationsFragment extends Fragment implements Observer, InvitationsViewModel.Contract, SwipeRefreshLayout.OnRefreshListener {

    FragmentInvitationsBinding binding;
    InvitationsViewModel viewModel;
    InvitationAdapter adapter;

    public InvitationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_invitations, container, false);
        viewModel = new InvitationsViewModel(this);
        binding.setViewModel(viewModel);
        setupAdapter();
        setupObserver(viewModel);
        viewModel.init();
        return binding.getRoot();
    }


    private void setupAdapter() {
        adapter = new InvitationAdapter(viewModel.getItemContract());
        adapter.setHasStableIds(true);
        binding.list.setAdapter(adapter);
        binding.list.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        binding.swipe.setOnRefreshListener(this);
    }


    private void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof InvitationsViewModel) {
            InvitationsViewModel viewModel = (InvitationsViewModel) observable;
        }
    }

    @Override
    public void loading(boolean loading) {
        binding.swipe.setRefreshing(loading);
    }

    @Override
    public void setList(List<Visit> list) {
        adapter.setList(list);
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void openItem(Visit visit) {
        VisitDialog dialog = new VisitDialog(this.getActivity(), visit);
        dialog.show();
    }

    @Override
    public void onRefresh() {
        viewModel.refresh();
    }
}
