package visit.me.gil.mota.visitme.viewModels;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import java.util.Observable;

import visit.me.gil.mota.visitme.models.Visit;
import visit.me.gil.mota.visitme.views.dialogs.VisitDialog;

/**
 * Created by mota on 16/4/2018.
 */

public class ItemVisitViewModel extends Observable {
    private Visit visit;
    private Context context;
    public ObservableField<String> username;
    public ObservableField<String> time;

    public ItemVisitViewModel(Visit visit, Context context) {
        this.context = context;
        username = new ObservableField<>("");
        time = new ObservableField<>("");
        setVisit(visit);

    }

    public void abrir(View view)
    {
        VisitDialog dialog = new VisitDialog(context,visit);
        dialog.show();
    }

    public void menu(View view)
    {
        //TODO PUT MENU HERE!
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
        Log.i("ITEM VISIT","ITEM "+ visit.toString());
        username.set(visit.getGuest().getName());
        time.set(visit.getDayOfVisit());
    }

}
