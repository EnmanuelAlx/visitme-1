package visit.me.gil.mota.visitme.useCases;

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
import visit.me.gil.mota.visitme.models.Alert;
import visit.me.gil.mota.visitme.models.Visit;
import visit.me.gil.mota.visitme.utils.Functions;

/**
 * Created by mota on 19/4/2018.
 */

public class GetAlerts extends FillListByType<Alert> {

    public GetAlerts(Result result) {
        super(result);
    }

    @Override
    protected Observable<JSONObject> getPromiseByType() {
        switch (type) {
            case 0:
                return RequestManager.getInstance().getIncidentAlerts(skip, 30);

            case 1:
                return RequestManager.getInstance().getInformationAlerts(skip, 30);

            case 2:
                return RequestManager.getInstance().getOtherAlerts(skip, 30);
        }
        return new Observable<JSONObject>() {
            @Override
            protected void subscribeActual(Observer<? super JSONObject> observer) {
                observer.onError(new Throwable("Error Inesperado!"));
            }
        };
    }

    @Override
    protected Collection<? extends Alert> parseList(JSONObject obj) throws JSONException {
        JSONArray arry = obj.getJSONArray("alerts");
        Alert[] alerts = Functions.parse(arry, Alert[].class);
        return Arrays.asList(alerts);
    }
}
