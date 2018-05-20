package visit.me.gil.mota.visitme.viewModels;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.Observable;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.models.User;
import visit.me.gil.mota.visitme.useCases.Register;
import visit.me.gil.mota.visitme.useCases.UseCase;
import visit.me.gil.mota.visitme.utils.FilePath;
import visit.me.gil.mota.visitme.utils.Pnotify;
import visit.me.gil.mota.visitme.views.activities.MainActivity;

/**
 * Created by mota on 12/4/2018.
 */

public class RegisterViewModel extends Observable implements UseCase.Result {

    public ObservableField<String> cedula;
    public ObservableField<String> name;
    public ObservableField<String> password;
    public ObservableField<String> email;
    public ObservableField<String> confirmPassword;
    public ObservableField<String> cellPhone;
    public ObservableField<String> homePhone;
    public ObservableField<Drawable> image;
    public Contract contract;
    public ObservableField<Boolean> edit;
    private Uri imageSelected;
    private Context context;
    private Register register;
    private User user;

    public RegisterViewModel(@NonNull Context context, Contract contract) {
        this.context = context;
        this.cedula = new ObservableField<>("");
        this.password = new ObservableField<>("");
        this.confirmPassword = new ObservableField<>("");
        this.homePhone = new ObservableField<>("");
        this.cellPhone = new ObservableField<>("");
        this.name = new ObservableField<>("");
        this.email = new ObservableField<>("");
        this.image = new ObservableField<>(context.getResources().getDrawable(R.drawable.guy));
        this.edit = new ObservableField<>(false);
        this.contract = contract;
        register = new Register(this,context);

    }

    public RegisterViewModel(@NonNull Context context, Contract contract, User user) {
        this.context = context;
        this.cedula = new ObservableField<>(user.getIdentification());
        this.password = new ObservableField<>("");
        this.confirmPassword = new ObservableField<>("");
        this.homePhone = new ObservableField<>(user.getHomePhone());
        this.cellPhone = new ObservableField<>(user.getCellPhone());
        this.name = new ObservableField<>(user.getName());
        this.email = new ObservableField<>(user.getEmail());
        this.user = user;
        this.edit = new ObservableField<>(true);
        this.contract = contract;
        contract.changeImage(user.getImage());
        register = new Register(this,context);

    }


    public void register(View view) {
        if (imageSelected == null) {
            Pnotify.makeText(context, "No has seleccionado una imagen de Perfil", Toast.LENGTH_SHORT, Pnotify.WARNING).show();
            return;
        }

        if (!password.get().equals(confirmPassword.get())) {
            Pnotify.makeText(context, "Las contraseñas no Coinciden", Toast.LENGTH_SHORT, Pnotify.WARNING).show();
            return;
        }

        if(cellPhone.get().isEmpty() && homePhone.get().isEmpty()){
            Pnotify.makeText(context, "Debe proveer por lo menos 1 numero de telefono", Toast.LENGTH_SHORT, Pnotify.WARNING).show();
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            register.setParams(cedula.get(), name.get(),
                    email.get(), password.get(),
                    cellPhone.get(),
                    homePhone.get(),
                    FilePath.getPath(context,imageSelected));
            register.run();
        }

    }

    public void editProfile(View view) {
        /*if (imageSelected == null) {
            Pnotify.makeText(context, "No has seleccionado una imagen de Perfil", Toast.LENGTH_SHORT, Pnotify.WARNING).show();
            return;
        }*/

        if (!password.get().equals(confirmPassword.get())) {
            Pnotify.makeText(context, "Las contraseñas no Coinciden", Toast.LENGTH_SHORT, Pnotify.WARNING).show();
            return;
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            register.setParams(
                    cedula.get().isEmpty() ? user.getIdentification() : cedula.get(),
                    name.get().isEmpty() ? user.getName() : name.get(),
                    email.get().isEmpty() ? user.getEmail() : email.get(),
                    password.get().isEmpty() ? user.getPassword() : password.get(),
                    cellPhone.get().isEmpty() ? user.getCellPhone() : cellPhone.get(),
                    homePhone.get().isEmpty() ? user.getHomePhone() : homePhone.get(),
                    "");
            register.runEdit();
        }

    }

    public void selectImage(View view) {
        contract.select();
    }

    public void changeImage(Uri image) {
        Bitmap myBitmap = null;
        try {
            myBitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), image);
        } catch (IOException e) {
            return;
        }
        this.imageSelected = image;
        this.image.set(new BitmapDrawable(myBitmap));

    }

    @Override
    public void onError(String error) {
        Pnotify.makeText(context, error, Toast.LENGTH_SHORT, Pnotify.ERROR).show();
    }

    @Override
    public void onSuccess() {
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
    }

    public interface SelectImage {

    }

    public interface Contract {
        void changeImage(String image);
        void select();
    }

}
