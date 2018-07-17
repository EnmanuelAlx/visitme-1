package visit.me.gil.mota.visitme.viewModels;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;

import java.util.Observable;

import visit.me.gil.mota.visitme.managers.UserManager;
import visit.me.gil.mota.visitme.models.Alert;
import visit.me.gil.mota.visitme.models.Visit;
import visit.me.gil.mota.visitme.useCases.CreateAlert;
import visit.me.gil.mota.visitme.useCases.GetCommunities;
import visit.me.gil.mota.visitme.useCases.GetUserCommunities;
import visit.me.gil.mota.visitme.useCases.SignOut;
import visit.me.gil.mota.visitme.useCases.UseCase;
import visit.me.gil.mota.visitme.utils.Functions;
import visit.me.gil.mota.visitme.utils.Pnotify;
import visit.me.gil.mota.visitme.views.dialogs.CreateAlertDialog;

/**
 * Created by mota on 14/4/2018.
 */

public class MainViewModel extends Observable implements DialogInterface.OnClickListener, CreateAlertDialog.Result, UseCase.Result {


    private Context context;
    private SignOut signOut;
    private GetUserCommunities getUserCommunities;
    private CreateAlert createAlert;
    private Contract contract;
    public MainViewModel(@NonNull Context context, Contract contract) {
        this.context = context;
        signOut = new SignOut(context);
        getUserCommunities = new GetUserCommunities(this);
        getUserCommunities.run();
        createAlert = new CreateAlert(createAlertResult);
        this.contract = contract;
    }

    public void signOut() {
        Functions.showAskDialog(context,"¿Esta seguro que desea terminar su sesion?",this);
    }

    public void createAlert(View view) {
        CreateAlertDialog dialog = new CreateAlertDialog(context,this);
        dialog.show();
    }

    public void createVisit(View view) {
        contract.createVisit();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        signOut.run();
    }

    @Override
    public void onAlertCreated(Alert alert) {
        try {
            createAlert.setParams(alert);
            createAlert.run();
        } catch (JSONException e) {
            createAlertResult.onError("Error Inesperado");
        }

    }

    public void addVisit(Visit visit) {
        contract.addVisit(visit);
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onSuccess() {
        try {
            if(UserManager.getInstance().getCommunities().isEmpty())
                contract.goToJoinCommunity();
        } catch (JSONException e) {

        }
    }



    public interface Contract {
        void createVisit();
        void addVisit(Visit visit);
        void updateAlerts();

        void goToJoinCommunity();
    }

    private final UseCase.Result createAlertResult = new UseCase.Result() {
        @Override
        public void onError(String error) {
            Pnotify.makeText(context,error, Toast.LENGTH_SHORT, Pnotify.ERROR).show();
        }

        @Override
        public void onSuccess() {
            Pnotify.makeText(context,"Creacion Satisfactoria", Toast.LENGTH_SHORT, Pnotify.INFO).show();
        }


    };
}
