package visit.me.gil.mota.visitme.useCases;

import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import visit.me.gil.mota.visitme.managers.RequestManager;
import visit.me.gil.mota.visitme.models.User;
import visit.me.gil.mota.visitme.utils.Functions;

public class FindFirstUserThatMatch extends UseCase implements Observer<JSONObject> {

    private String identification;
    private Result result;
    private boolean running;

    public boolean isRunning() {
        return running;
    }

    public FindFirstUserThatMatch(Result result) {
        super(null);
        this.result = result;
        running = false;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(JSONObject jsonObject) {
        User u = Functions.parse(jsonObject, User.class);
        result.onUserFinded(u);
        running = false;
    }

    @Override
    public void onError(Throwable e) {
        result.onError(e.getMessage());
        running = false;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void run() {
        RequestManager.getInstance().findFirstUserThatMatch(identification)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(this);
        running = true;
    }

    public interface Result {
        void onUserFinded(User user);
        void onError(String error);
    }
}
