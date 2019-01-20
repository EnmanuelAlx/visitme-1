package visit.me.gil.mota.visitme.useCases;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collection;
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

public class GetVisits extends FillListByType<Visit> {

    private static int VISITS_LIMIT = 30;

    public GetVisits(Result result) {
        super(result);
    }

    @Override
    protected Observable<JSONObject> getPromiseByType() {
        switch (type) {
            case 0:
                return RequestManager.getInstance().getScheduledVisits(skip, VISITS_LIMIT);
            case 1:
                return RequestManager.getInstance().getFrequentVisits(skip, VISITS_LIMIT);

            case 2:
                return RequestManager.getInstance().getSporadicVisits(skip, VISITS_LIMIT);
            case 3:
                return RequestManager.getInstance().getGuestsVisits(skip, VISITS_LIMIT);
        }
        return new Observable<JSONObject>() {
            @Override
            protected void subscribeActual(Observer<? super JSONObject> observer) {
                observer.onError(new Throwable("Error Inesperado!"));
            }
        };
    }

    @Override
    protected Collection<? extends Visit> parseList(JSONObject obj) throws JSONException {
        JSONArray arry = obj.getJSONArray("visits");
        Log.e("MOTA","VISIT JSON"+  obj.toString());
        Visit[] visits = Functions.parse(arry, Visit[].class);
        Log.e("MOTA","VISIT OBJ"+ Arrays.toString(visits));
        return Arrays.asList(visits);
    }

}
