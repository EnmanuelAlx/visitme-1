package visit.me.gil.mota.visitme.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;
import java.util.Observable;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.ActivityJoinCommunityBinding;
import visit.me.gil.mota.visitme.models.Community;
import visit.me.gil.mota.visitme.viewModels.JoinViewModel;
import visit.me.gil.mota.visitme.views.adapters.CommunityAdapter;

public class JoinCommunityActivity extends BindeableActivity implements JoinViewModel.Contract {
    private JoinViewModel viewModel;
    private ActivityJoinCommunityBinding binding;
    private CommunityAdapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setupObserver(viewModel);
        setupAdapter();
    }

    private void setupAdapter() {
        adapter = new CommunityAdapter(viewModel.getItemContract());
        adapter.setHasStableIds(true);
        binding.list.setAdapter(adapter);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override public void initDataBinding()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_join_community);
        viewModel = new JoinViewModel(this);
        binding.setViewModel(viewModel);

    }


    @Override public void update(Observable observable, Object o)
    {
        if(observable instanceof JoinViewModel)
        {
            JoinViewModel viewModel = (JoinViewModel) observable;
        }
    }

    @Override
    public void setLoading(boolean loading) {
        binding.loader.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void loadCommunities(List<Community> communities) {
        adapter.setList(communities);
    }
}
