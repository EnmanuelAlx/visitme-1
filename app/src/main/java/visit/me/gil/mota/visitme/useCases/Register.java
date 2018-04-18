package visit.me.gil.mota.visitme.useCases;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import visit.me.gil.mota.visitme.managers.RequestManager;
import visit.me.gil.mota.visitme.managers.UserManager;
import visit.me.gil.mota.visitme.models.User;
import visit.me.gil.mota.visitme.utils.Functions;

/**
 * Created by mota on 14/4/2018.
 */

public class Register extends Auth {

    private HashMap<String, String> data;
    private String image;

    public Register(Result result, Context context) {
        super(result,context);
        data = new HashMap<>();

    }

    @Override
    public void run() {
        RequestManager.getInstance()
                .register(data,image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    public void setParams(String cedula, String nombre, String email, String password,String cellPhone, String homePhone, String image)
    {
        data.put("identification",cedula);
        data.put("name", nombre);
        data.put("email", email);
        data.put("password", password);
        data.put("cellPhone",cellPhone);
        data.put("homePhone",homePhone);
        this.image = image;
        Log.i("REGISTER","SET PARAMS" + data.toString() + image);
    }


}
