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
import visit.me.gil.mota.visitme.models.Community;
import visit.me.gil.mota.visitme.models.Interval;
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
                          Community community, String visitType)
    {
        try {
            params.put("identification",cedula);
            params.put("name",name);
            params.put("dayOfVisit",dayOfVisit);
            params.put("community",community.get_id());
            params.put("kind",visitType);
            params.put("intervals",intervalToJsonArray(intervals));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private JSONArray intervalToJsonArray(List<Interval> intervals) throws JSONException {
        JSONArray arry = new JSONArray();
        if (intervals == null) return  arry;
        for(Interval i : intervals)
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
        resultSetter.onSuccess();
    }

    @Override
    public void onError(Throwable e) {
        resultSetter.onError(e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
