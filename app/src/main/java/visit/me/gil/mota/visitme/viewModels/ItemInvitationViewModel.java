package visit.me.gil.mota.visitme.viewModels;

import android.databinding.ObservableField;
import android.view.View;

import visit.me.gil.mota.visitme.models.Visit;

public class ItemInvitationViewModel {
    private Visit visit;
    public ObservableField<String> username;
    public ObservableField<String> time;
    public ObservableField<String> community;
    public ObservableField<String> validTill;
    private Contract contract;

    public ItemInvitationViewModel(Visit visit, Contract contract) {
        username = new ObservableField<>("");
        time = new ObservableField<>("");
        community = new ObservableField<>("");
        validTill = new ObservableField<>("");
        setVisit(visit);
        this.contract = contract;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
        username.set(visit.getResident().getName());
        community.set(visit.getCommunity().getName());
        validTill.set("Valido hasta:" + visit.getValidTill());
        time.set(visit.getDayOfVisit());
    }

    public void open(View view) {
        contract.openItem(visit);
    }

    public interface Contract {
        void openItem(Visit visit);
    }

}
