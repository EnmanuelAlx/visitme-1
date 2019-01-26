package visit.me.gil.mota.visitme.views.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.FragmentJoinCommunityBinding;
import visit.me.gil.mota.visitme.utils.Pnotify;
import visit.me.gil.mota.visitme.viewModels.JoinViewModel;
import visit.me.gil.mota.visitme.views.adapters.CommunityAdapter;
import visit.me.gil.mota.visitme.views.dialogs.AskFieldDialog;


public class JoinCommunityFragment extends Fragment implements Observer, JoinViewModel.Contract {

    FragmentJoinCommunityBinding binding;
    JoinViewModel viewModel;
    CommunityAdapter adapter;
    private AskFieldDialog askReferenceDialog;

    public JoinCommunityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_join_community, container, false);
        viewModel = new JoinViewModel(this);
        binding.setViewModel(viewModel);
        setupAdapter();
        setupObserver(viewModel);
        return binding.getRoot();
    }


    private void setupAdapter() {
        adapter = new CommunityAdapter(viewModel.getItemContract());
        adapter.setList(viewModel.getList());
        adapter.setHasStableIds(true);
        binding.list.setAdapter(adapter);
        binding.list.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        binding.editText3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.search();
                    return true;
                }
                return false;
            }
        });
    }


    private void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof JoinViewModel) {
            JoinViewModel viewModel = (JoinViewModel) observable;
        }
    }

    @Override
    public void setLoading(boolean loading) {
        binding.loader.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onJoin() {

    }

    @Override
    public void showAskReferenceDialog(AskFieldDialog.Result result) {
        askReferenceDialog = new AskFieldDialog(getContext(), "¿en que numero de oficina/apto/casa de esta comunidad?", result);
        askReferenceDialog.show();
    }

    @Override
    public void onError(String error) {
        Pnotify.makeText(getContext(), error, Toast.LENGTH_SHORT, Pnotify.ERROR).show();
    }

    @Override
    public void loadCommunities() {
        adapter.notifyDataSetChanged();
    }

}
