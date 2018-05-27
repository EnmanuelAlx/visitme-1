package visit.me.gil.mota.visitme.useCases;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import visit.me.gil.mota.visitme.models.Interval;

public class EditVisit extends UseCase implements Observer<JSONObject> {

    JSONObject params;
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

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void run() {

    }

    public void setParam(String name, Object[] arry) throws JSONException {
        params.put(name,arry);
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }
}