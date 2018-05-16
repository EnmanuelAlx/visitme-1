package visit.me.gil.mota.visitme.viewModels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import java.util.Observable;

import visit.me.gil.mota.visitme.useCases.SendChangePasswordCode;
import visit.me.gil.mota.visitme.useCases.UseCase;
import visit.me.gil.mota.visitme.utils.Pnotify;
import visit.me.gil.mota.visitme.views.activities.ChangePasswordActivity;
import visit.me.gil.mota.visitme.views.activities.CodeActivity;

/**
 * Created by mota on 17/4/2018.
 */

public class CodeViewModel extends Observable implements UseCase.Result {
    private final String email;
    public ObservableField<String> code;
    private Context context;

    public CodeViewModel(Context context, String email) {
        this.context = context;
        this.code = new ObservableField<>("");
        this.email = email;
    }

    public void enviar(View view) {
        SendChangePasswordCode useCase = new SendChangePasswordCode(this);
        useCase.setCode(code.get());
        useCase.run();
    }


    @Override
    public void onError(String error) {
        Pnotify.makeText(context,error, Toast.LENGTH_SHORT, Pnotify.ERROR).show();
    }

    @Override
    public void onSuccess() {
        goToChangePasswordActivity();
    }

    private void goToChangePasswordActivity() {
        Intent i = new Intent(context, ChangePasswordActivity.class);
        i.putExtra("email", email);
        i.putExtra("code", code.get());
        ((Activity)context).startActivityForResult(i, ChangePasswordActivity.CLOSE);
    }
}
