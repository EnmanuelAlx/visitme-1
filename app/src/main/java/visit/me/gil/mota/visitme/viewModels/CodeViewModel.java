package visit.me.gil.mota.visitme.viewModels;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import java.util.Observable;

import visit.me.gil.mota.visitme.views.activities.CodeActivity;

/**
 * Created by mota on 17/4/2018.
 */

public class CodeViewModel extends Observable {
    public ObservableField<String> code;
    private Context context;
    public CodeViewModel(Context context) {
        this.context = context;
        this.code = new ObservableField<>("");
    }

    public void enviar(View view)
    {
        // TODO!
    }


}
