package visit.me.gil.mota.visitme.useCases;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import visit.me.gil.mota.visitme.managers.RequestManager;
import visit.me.gil.mota.visitme.managers.UserManager;
import visit.me.gil.mota.visitme.models.User;
import visit.me.gil.mota.visitme.utils.Functions;

/**
 * Created by mota on 14/4/2018.
 */

public abstract class Auth extends UseCase implements Observer<JSONObject> {

    private Context context;
    public Auth(Result result, Context context) {
        super(result);
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(JSONObject obj) {
        try {
            onSuccess(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onError(Throwable e) {
        resultSetter.onError(e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    @Override
    abstract public void run();

    public void onSuccess(JSONObject obj) throws JSONException {
        User u = Functions.parse(obj.getJSONObject("user"),User.class);
        UserManager.getInstance().saveUserCredentials(u);
        UserManager.getInstance().saveAuth(obj.getString("token"));

        resultSetter.onSuccess();
    }
}
