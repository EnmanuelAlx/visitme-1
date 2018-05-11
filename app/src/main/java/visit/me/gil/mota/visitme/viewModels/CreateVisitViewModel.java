package visit.me.gil.mota.visitme.viewModels;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;

import java.util.List;
import java.util.Observable;

import visit.me.gil.mota.visitme.managers.UserManager;
import visit.me.gil.mota.visitme.models.Community;
import visit.me.gil.mota.visitme.models.Interval;
import visit.me.gil.mota.visitme.useCases.CreateVisit;
import visit.me.gil.mota.visitme.useCases.UseCase;

/**
 * Created by mota on 22/4/2018.
 */

public class CreateVisitViewModel extends Observable implements UseCase.Result {

    private Contract contract;
    private String visitType;
    private Community community;
    private Bundle actualInfo;
    private CreateVisit createVisit;

    public CreateVisitViewModel(Contract contract) {
        this.contract = contract;
        actualInfo = new Bundle();
        createVisit = new CreateVisit(this);
    }

    public void setVisitType(String visitType) {

        try {
            this.visitType = visitType;
            actualInfo.putString("VISIT_TYPE", visitType);
            goToSelectCommunityIfNeeded();
        } catch (JSONException e) {
            contract.selectStep(1);
        }
    }

    private void goToSelectCommunityIfNeeded() throws JSONException {
        if (UserManager.getInstance().getCommunities().size() > 1)
            contract.selectStep(1);
        else {
            try {
                setCommunity(UserManager.getInstance().getCommunities().get(0));
                contract.selectStep(2);
            }catch (Exception e)
            {
                contract.setError("No estas en una comunidad aun!");
            }

        }

    }

    public void setCommunity(Community community) {
        this.community = community;
        actualInfo.putString("COMMUNITY", community.get_id());
        contract.selectStep(2);
    }

    public Bundle getActualInfo() {
        return actualInfo;
    }

    public void fillGuestData(String cedula, String name, String dayOfVisit, List<Interval> intervals) {
        createVisit.setParams(cedula, name, dayOfVisit, intervals, community, visitType);
        createVisit.run();
    }

    @Override
    public void onError(String error) {
        contract.setError(error);
    }

    @Override
    public void onSuccess() {
        contract.finishSuccess();
    }

    public int getStep() {
        return 0;
    }

    public interface Contract {
        void selectStep(int step);

        void prevStep();

        void setError(String error);

        void finishSuccess();
    }
}