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
import visit.me.gil.mota.visitme.models.Interval;
import visit.me.gil.mota.visitme.utils.Functions;


public class EditVisit extends UseCase implements Observer<JSONObject> {

    private JSONObject params;
    private String visitId;

    public EditVisit(Result result) {
        super(result);
        params = new JSONObject();
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

    @Override
    public void run() {
        RequestManager.getInstance().editVisit(visitId, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    public void setParam(String name, Interval[] arry) throws JSONException {
        JSONArray jsonArry = new JSONArray();
        for (Interval o : arry)
            jsonArry.put(Functions.toJSON(o));
        params.put(name, jsonArry);
    }

    public void setParam(String name, int i) throws JSONException {
        params.put(name, i);
    }

    public void setParam(String name, String i) throws JSONException {
        params.put(name, i);
    }
    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }
}
