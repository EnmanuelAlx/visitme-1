package visit.me.gil.mota.visitme.useCases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import visit.me.gil.mota.visitme.managers.RequestManager;
import visit.me.gil.mota.visitme.managers.UserManager;
import visit.me.gil.mota.visitme.models.Community;
import visit.me.gil.mota.visitme.utils.Functions;

public class JoinCommunity extends UseCase implements Observer<JSONObject> {

    public void setCommunity(Community community) {
        this.community = community;
    }

    Community community;

    public void setReference(String reference) {
        this.reference = reference;
    }

    String reference;
    public JoinCommunity(Result result) {
        super(result);
    }

    @Override
    public void run() {
        try {

            RequestManager.getInstance().joinCommunity(community, reference).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        } catch (JSONException e) {
            resultSetter.onError("Error Inesperado");
        }

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(JSONObject jsonObject) {
        try {
            UserManager.getInstance().addCommunity(community);
            resultSetter.onSuccess();
        } catch (JSONException e) {
            onError(new Throwable("Error al Guardar la comunidad en el dispositivo, por favor reinicia la aplicacion"));
        }

    }

    @Override
    public void onError(Throwable e) {
        resultSetter.onError(e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
