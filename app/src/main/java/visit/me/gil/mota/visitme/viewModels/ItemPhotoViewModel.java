package visit.me.gil.mota.visitme.viewModels;

import android.content.Context;
import android.view.View;

import java.io.File;
import java.util.Observable;



public class ItemPhotoViewModel extends Observable{


    private final Context context;
    private String photo;

    public ItemPhotoViewModel(String photo, Context context) {
        this.photo = photo;
        this.context = context;
    }

    public void abrir(View view)
    {

    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
