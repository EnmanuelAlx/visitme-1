package visit.me.gil.mota.visitme.viewModels;

import android.databinding.ObservableField;
import android.view.View;

import java.util.Observable;

import visit.me.gil.mota.visitme.models.Community;

public class ItemCommunityViewModel extends Observable {


    private final Contract contract;
    private Community community;
    public ObservableField<String> name, address;
    public ItemCommunityViewModel(Community community, Contract contract) {
        name = new ObservableField<>();
        address = new ObservableField<>();
        setCommunity(community);
        this.contract = contract;
    }

    public void select(View view) {

    }

    public void setCommunity(Community community) {
        this.community = community;
        name.set(community.getName());
        address.set(community.getAddress());
    }

    public interface  Contract {

    }
}