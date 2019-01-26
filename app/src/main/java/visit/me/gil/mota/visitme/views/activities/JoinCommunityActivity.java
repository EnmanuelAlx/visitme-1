package visit.me.gil.mota.visitme.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.ActivityJoinCommunityBinding;
import visit.me.gil.mota.visitme.models.Community;
import visit.me.gil.mota.visitme.utils.Pnotify;
import visit.me.gil.mota.visitme.viewModels.JoinViewModel;
import visit.me.gil.mota.visitme.views.adapters.CommunityAdapter;
import visit.me.gil.mota.visitme.views.dialogs.AskFieldDialog;

public class JoinCommunityActivity extends BindeableActivity implements JoinViewModel.Contract {
    public static final int GO_TO_WAIT_APPROVE = 1245;
    private JoinViewModel viewModel;
    private ActivityJoinCommunityBinding binding;
    private CommunityAdapter adapter;
    private AskFieldDialog askReferenceDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setupObserver(viewModel);
        setupAdapter();
    }

    @Override
    public void onBackPressed() {
        //BLOCKED
    }

    private void setupAdapter() {
        adapter = new CommunityAdapter(viewModel.getItemContract());
        adapter.setList(viewModel.getList());
        adapter.setHasStableIds(true);
        binding.list.setAdapter(adapter);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_join_community);
        viewModel = new JoinViewModel(this);
        binding.setViewModel(viewModel);

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
        Pnotify.makeText(this, "Solicitud Creada satisfactoriamente", Toast.LENGTH_SHORT, Pnotify.INFO).show();
    }

    @Override
    public void showAskReferenceDialog(AskFieldDialog.Result result) {
        askReferenceDialog = new AskFieldDialog(this, "¿en que numero de oficina/apto/casa de esta comunidad?", result);
        askReferenceDialog.show();
    }

    @Override
    public void onError(String error) {
        Pnotify.makeText(this, error, Toast.LENGTH_SHORT, Pnotify.ERROR).show();
    }

    @Override
    public void loadCommunities() {
        adapter.notifyDataSetChanged();
    }

}
