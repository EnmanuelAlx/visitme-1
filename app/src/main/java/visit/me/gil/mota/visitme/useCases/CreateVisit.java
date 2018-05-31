package visit.me.gil.mota.visitme.useCases;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.TimeZone;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import visit.me.gil.mota.visitme.managers.RequestManager;
import visit.me.gil.mota.visitme.models.Community;
import visit.me.gil.mota.visitme.models.Interval;
import visit.me.gil.mota.visitme.models.Visit;
import visit.me.gil.mota.visitme.utils.Functions;

/**
 * Created by mota on 28/4/2018.
 */

public class CreateVisit extends UseCase implements Observer<JSONObject> {

    private JSONObject params;

    public CreateVisit(Result result) {
        super(result);
        params = new JSONObject();
    }

    @Override
    public void run() {
        RequestManager.getInstance().createVisit(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    public void setParams(String cedula, String name,
                          String dayOfVisit, List<Interval> intervals,
                          String partOfDay, int companions, Community community, String visitType) {
        try {
            if (visitType.equals("FREQUENT") || visitType.equals("SCHEDULED"))
                params.put("identification", cedula);

            params.put("name", name);
            params.put("community", community.get_id());
            params.put("kind", visitType);

            if (visitType.equals("FREQUENT"))
                params.put("intervals", intervalToJsonArray(intervals));


            if (visitType.equals("SCHEDULED")) {
                params.put("partOfDay", partOfDay);
                params.put("companions", companions);
                params.put("dayOfVisit", dayOfVisit);
            }

            params.put("timezone", TimeZone.getDefault().getID());
            Log.i("VISIT","VISIT AS JSON" + params.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private JSONArray intervalToJsonArray(List<Interval> intervals) throws JSONException {
        JSONArray arry = new JSONArray();
        if (intervals == null) return arry;
        for (Interval i : intervals)
            arry.put(intervalToJSONObject(i));
        return arry;
    }

    private JSONObject intervalToJSONObject(Interval i) throws JSONException {
        return Functions.toJSON(i);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(JSONObject jsonObject) {
        Result result = (Result) resultSetter;
        Log.i("CREATE VISIT","JS "+jsonObject);
        Visit v = Functions.parse(jsonObject, Visit.class);
        Log.i("CREATE VISIT", "VISIT_ "+v.toString());
        result.onVisitCreated(v);
    }

    @Override
    public void onError(Throwable e) {
        resultSetter.onError(e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    public interface Result extends UseCase.Result {
        void onVisitCreated(Visit visit);
    }
}
