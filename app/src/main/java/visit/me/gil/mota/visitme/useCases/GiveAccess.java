package visit.me.gil.mota.visitme.useCases;

import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import visit.me.gil.mota.visitme.managers.RequestManager;

public class GiveAccess extends UseCase implements Observer<JSONObject> {

    private boolean access;
    private String visit;

    public GiveAccess(Result result) {
        super(result);
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
        RequestManager.getInstance().giveAccess(visit, access)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }
}
