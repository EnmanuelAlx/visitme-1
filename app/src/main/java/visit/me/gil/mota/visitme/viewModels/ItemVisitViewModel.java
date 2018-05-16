package visit.me.gil.mota.visitme.viewModels;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.Observable;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.models.Visit;
import visit.me.gil.mota.visitme.useCases.DeleteVisit;
import visit.me.gil.mota.visitme.useCases.UseCase;
import visit.me.gil.mota.visitme.utils.Functions;
import visit.me.gil.mota.visitme.utils.Pnotify;
import visit.me.gil.mota.visitme.views.dialogs.VisitDialog;

/**
 * Created by mota on 16/4/2018.
 */

public class ItemVisitViewModel extends Observable implements PopupMenu.OnMenuItemClickListener,
                                                              DialogInterface.OnClickListener, UseCase.Result {
    private Visit visit;
    private Context context;
    public ObservableField<String> username;
    public ObservableField<String> time;
    public ObservableField<String> community;
    private Contract contract;
    public ItemVisitViewModel(Visit visit, Context context, Contract contract) {
        this.context = context;
        username = new ObservableField<>("");
        time = new ObservableField<>("");
        community = new ObservableField<>("");
        setVisit(visit);
        this.contract = contract;
    }

    public void abrir(View view)
    {
        VisitDialog dialog = new VisitDialog(context,visit);
        dialog.show();
    }

    public void menu(View view)
    {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_visit);

        if(visit.getKind().equals("SPORADIC"))
            popupMenu.getMenu().removeItem(R.id.edit);

        popupMenu.show();
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
        username.set(visit.getGuest().getName());
        community.set(visit.getCommunity().getName());
        time.set(visit.getDayOfVisit());
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.edit:

                return true;
            case R.id.delete:
                delete();
                return true;
        }
        return false;
    }

    private void delete() {
        Functions.showConfirmDialog(context,this);
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        DeleteVisit delete = new DeleteVisit(this);
        delete.setVisit(this.visit);
        delete.run();
    }

    @Override
    public void onError(String error) {
        Pnotify.makeText(context, "Hubo un Error", Toast.LENGTH_SHORT, Pnotify.ERROR).show();
    }

    @Override
    public void onSuccess() {
        Pnotify.makeText(context, "Eliminado Satisfactoriamente", Toast.LENGTH_SHORT, Pnotify.INFO).show();
        contract.remove(visit);
    }

    public interface Contract {
        void remove(Visit visit);
    }
}
