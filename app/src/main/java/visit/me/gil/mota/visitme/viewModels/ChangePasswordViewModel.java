package visit.me.gil.mota.visitme.viewModels;

import android.databinding.ObservableField;
import android.view.View;

import java.util.Observable;

import visit.me.gil.mota.visitme.useCases.ChangePassword;
import visit.me.gil.mota.visitme.useCases.UseCase;

public class ChangePasswordViewModel extends Observable implements UseCase.Result {

    private Contract contract;
    public ObservableField<String> password;
    public ObservableField<String> confirm;
    private String code, email;
    public ChangePasswordViewModel(Contract contract, String code, String email) {
        this.contract = contract;
        password = new ObservableField<>("");
        confirm = new ObservableField<>("");
        this.code = code;
        this.email = email;
    }

    @Override
    public void onError(String error) {
        contract.onError(error);
    }

    @Override
    public void onSuccess() {
        contract.showInfo("Contraseña Actualizada!");
        contract.finishSuccessfull();
    }

    public void enviar(View view){
        ChangePassword changePassword = new ChangePassword(this);
        changePassword.setPassword(password.get());
        changePassword.setEmail(email);
        changePassword.setCode(code);
        changePassword.run();
    }

    public interface Contract {
        void showInfo(String str);
        void onError(String err);
        void finishSuccessfull();
    }
}
