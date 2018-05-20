package visit.me.gil.mota.visitme.viewModels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import java.util.Observable;

import visit.me.gil.mota.visitme.useCases.ForgotPassword;
import visit.me.gil.mota.visitme.useCases.UseCase;
import visit.me.gil.mota.visitme.utils.Pnotify;
import visit.me.gil.mota.visitme.views.activities.ChangePasswordActivity;
import visit.me.gil.mota.visitme.views.activities.CodeActivity;
import visit.me.gil.mota.visitme.views.activities.ForgotPasswordActivity;

/**
 * Created by mota on 17/4/2018.
 */

public class ForgotPasswordViewModel extends Observable implements UseCase.Result {

    public ObservableField<String> email;
    private Context context;

    public ForgotPasswordViewModel(Context context) {
        this.context = context;
        this.email = new ObservableField<>("");
    }

    public void enviar(View view) {
        ForgotPassword forgotPassword = new ForgotPassword(this);
        forgotPassword.setEmail(email.get());
        forgotPassword.run();
    }

    private void goToCodeActivity() {
        Intent i = new Intent(context, CodeActivity.class);
        i.putExtra("email", email.get());
        ((Activity)context).startActivityForResult(i, ChangePasswordActivity.CLOSE);
    }

    @Override
    public void onError(String error) {
        Pnotify.makeText(context, error, Toast.LENGTH_SHORT, Pnotify.ERROR).show();
    }

    @Override
    public void onSuccess() {
        goToCodeActivity();
    }
}
