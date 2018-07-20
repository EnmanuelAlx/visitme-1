package visit.me.gil.mota.visitme.viewModels;

import android.view.View;

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
    public JoinViewModel(Contract contract) {
        getCommunities = new GetCommunities(this);
        joinCommunity = new JoinCommunity(joinResult);
        this.contract = contract;
        contract.setLoading(true);
        getCommunities.run();
    }

    @Override
    public void onCommunities(List<Community> communities) {
        this.communities = communities;
        contract.loadCommunities(communities);
        contract.setLoading(false);
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onSuccess() {

    }

    public void onClickJoin(View view) {

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
            contract.goToWaitApprove();

        }
    };

    public interface Contract {
        void loadCommunities(List<Community> communities);
        void goToWaitApprove();
        void setLoading(boolean loading);
        void showAskReferenceDialog(AskFieldDialog.Result result);
        void onError(String error);
    }
}
