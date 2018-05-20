package visit.me.gil.mota.visitme.viewModels;

import android.databinding.ObservableField;
import android.view.View;

import java.util.Observable;

import visit.me.gil.mota.visitme.models.User;

public class ItemUserViewModel extends Observable {
    private User user;
    public ObservableField<String> username;
    private Contract contract;

    public ItemUserViewModel(Contract contract, User u)
    {
        username = new ObservableField<>("");
        this.contract = contract;
        setUser(u);
    }

    public void setUser(User u) {
        this.user = u;
        username.set(u.getName());
    }

    public void abrir(View view){
        contract.onClick(this.user);
    }

    public interface Contract {
        void onClick(User u);
    }
}
