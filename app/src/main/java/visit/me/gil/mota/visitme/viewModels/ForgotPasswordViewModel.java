package visit.me.gil.mota.visitme.viewModels;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import java.util.Observable;

import visit.me.gil.mota.visitme.views.activities.CodeActivity;
import visit.me.gil.mota.visitme.views.activities.ForgotPasswordActivity;

/**
 * Created by mota on 17/4/2018.
 */

public class ForgotPasswordViewModel extends Observable {

    public ObservableField<String> identification;
    private Context context;
    public ForgotPasswordViewModel(Context context) {
        this.context = context;
        this.identification = new ObservableField<>("");
    }

    public void enviar(View view)
    {
        // TODO!
    }

    public void goToCodeActivity(View view)
    {
        Intent i = new Intent(context, CodeActivity.class);
        i.putExtra("identification",identification.get());
        context.startActivity(i);
    }
}
