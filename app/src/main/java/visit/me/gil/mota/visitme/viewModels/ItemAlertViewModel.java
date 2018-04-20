package visit.me.gil.mota.visitme.viewModels;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import java.util.Observable;

import visit.me.gil.mota.visitme.models.Alert;


/**
 * Created by mota on 19/4/2018.
 */

public class ItemAlertViewModel extends Observable {
    private Alert alert;
    private Context context;
    public ObservableField<String> title;
    public ObservableField<String> time;

    public ItemAlertViewModel(Alert alert, Context context) {
        this.context = context;
        title = new ObservableField<>("");
        time = new ObservableField<>("");
        setAlert(alert);

    }

    public void abrir(View view)
    {
        /*
        AlertDialog dialog = new AlertDialog(context,alert);
        dialog.show();*/
    }

    public void menu(View view)
    {
        //TODO PUT MENU HERE!
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
        title.set(alert.getMessage());
        time.set(alert.getCreated_at());
    }

}
