package visit.me.gil.mota.visitme.viewModels;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import visit.me.gil.mota.visitme.models.Visit;
import visit.me.gil.mota.visitme.useCases.GetVisits;
import visit.me.gil.mota.visitme.useCases.UseCase;

public class InvitationsViewModel extends Observable implements UseCase.Result, ItemInvitationViewModel.Contract {

    private static int INVITATIONS_TYPE = 3;
    private GetVisits getInvitations;
    private List<Visit> invitations;

    private Contract contract;

    public InvitationsViewModel(Contract contract) {
        getInvitations = new GetVisits(this);
        invitations = new ArrayList<>();
        this.contract = contract;
    }

    public void init() {
        getInvitations.setParams(INVITATIONS_TYPE, 0, invitations);
        contract.setList(invitations);
        refresh();
    }

    @Override
    public void onError(String error) {
        contract.loading(false);
    }

    @Override
    public void onSuccess() {
        contract.updateList();
        contract.loading(false);
    }

    public ItemInvitationViewModel.Contract getItemContract() {
        return this;
    }

    public void refresh() {
        contract.loading(true);
        getInvitations.run();
    }

    @Override
    public void openItem(Visit visit) {
        contract.openItem(visit);
    }

    public interface Contract {
        void loading(boolean loading);
        void setList(List<Visit> list);
        void updateList();
        void openItem(Visit visit);
    }

}
