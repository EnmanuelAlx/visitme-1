package visit.me.gil.mota.visitme.viewModels;

import android.view.View;

import java.util.List;
import java.util.Observable;

import visit.me.gil.mota.visitme.models.Community;
import visit.me.gil.mota.visitme.useCases.GetCommunities;

public class JoinViewModel extends Observable implements GetCommunities.Result, ItemCommunityViewModel.Contract {

    private final Contract contract;
    private GetCommunities getCommunities;
    private List<Community> communities;
    private Community selected;
    public JoinViewModel(Contract contract)
    {
        getCommunities = new GetCommunities(this);

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

    public void onClickJoin(View view)
    {

    }

    public ItemCommunityViewModel.Contract getItemContract() {
        return this;
    }

    public interface Contract {
        void loadCommunities(List<Community> communities);
        void setLoading(boolean loading);
    }
}
