package visit.me.gil.mota.visitme.useCases;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import visit.me.gil.mota.visitme.managers.RequestManager;
import visit.me.gil.mota.visitme.models.Visit;
import visit.me.gil.mota.visitme.utils.Functions;

/**
 * Created by mota on 16/4/2018.
 */

public class GetVisits extends UseCase implements Observer<JSONObject> {

    private int type;
    private int skip;
    private List<Visit> listToFill;

    public GetVisits(Result result) {
        super(result);
    }

    @Override
    public void run() {
        getPromiseByType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);

    }

    private Observable<JSONObject> getPromiseByType() {
        switch (type) {
            case 0:
                return RequestManager.getInstance().getScheduledVisits(skip, 30);

            case 1:
                return RequestManager.getInstance().getFrequentVisits(skip, 30);

            case 2:
                return RequestManager.getInstance().getSporadicVisits(skip, 30);

        }
        return new Observable<JSONObject>() {
            @Override
            protected void subscribeActual(Observer<? super JSONObject> observer) {
                observer.onError(new Throwable("Error Inesperado!"));
            }
        };
    }

    public void setParams(int type, int skip, List<Visit> visits) {
        this.type = type;
        this.skip = skip;
        this.listToFill = visits;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(JSONObject obj) {
        try {
            fillVisits(obj);
            resultSetter.onSuccess();
        } catch (JSONException e) {
            e.printStackTrace();
            resultSetter.onError("Error Inesperado");
        }
    }

    private void fillVisits(JSONObject obj) throws JSONException {
        JSONArray arry = obj.getJSONArray("visits");
        Visit[] visits = Functions.parse(arry, Visit[].class);
        this.listToFill.addAll(Arrays.asList(visits));
    }

    @Override
    public void onError(Throwable e) {
        resultSetter.onError(e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
