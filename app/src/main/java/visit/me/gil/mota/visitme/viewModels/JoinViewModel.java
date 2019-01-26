package visit.me.gil.mota.visitme.viewModels;

import android.databinding.ObservableField;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import visit.me.gil.mota.visitme.models.Community;
import visit.me.gil.mota.visitme.useCases.GetCommunities;
import visit.me.gil.mota.visitme.useCases.JoinCommunity;
import visit.me.gil.mota.visitme.useCases.UseCase;
import visit.me.gil.mota.visitme.views.dialogs.AskFieldDialog;

public class JoinViewModel extends Observable implements GetCommunities.Result, ItemCommunityViewModel.Contract, AskFieldDialog.Result {

    private final Contract contract;
    private GetCommunities getCommunities;
    private List<Community> communities;
    private Community selected;
    private JoinCommunity joinCommunity;
    public ObservableField<String> search;

    public JoinViewModel(Contract contract) {
        getCommunities = new GetCommunities(this);
        joinCommunity = new JoinCommunity(joinResult);
        search = new ObservableField<>("");
        this.contract = contract;
        contract.setLoading(true);
        communities = new ArrayList<>();
        getCommunities.setParams(search.get());
        getCommunities.run();
    }

    @Override
    public void onCommunities(List<Community> communities) {

        this.communities.clear();
        this.communities.addAll(communities);
        contract.loadCommunities();
        contract.setLoading(false);
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onSuccess() {

    }

    public ItemCommunityViewModel.Contract getItemContract() {
        return this;
    }

    @Override
    public void onItemClick(Community community) {
        selected = community;
        contract.showAskReferenceDialog(this);
    }

    @Override
    public void onFieldFilled(String field) {
        joinCommunity.setCommunity(selected);
        joinCommunity.setReference(field);
        contract.setLoading(true);
        joinCommunity.run();
    }

    private final UseCase.Result joinResult = new UseCase.Result() {
        @Override
        public void onError(String error) {
            contract.onError(error);
            contract.setLoading(false);
        }

        @Override
        public void onSuccess() {
            contract.setLoading(false);
            contract.onJoin();
        }
    };

    public List<Community> getList() {
        return communities;
    }

    public void search() {
        getCommunities.setParams(search.get());
        getCommunities.run();
    }

    public interface Contract {
        void loadCommunities();

        void setLoading(boolean loading);

        void onJoin();

        void showAskReferenceDialog(AskFieldDialog.Result result);

        void onError(String error);
    }
}
