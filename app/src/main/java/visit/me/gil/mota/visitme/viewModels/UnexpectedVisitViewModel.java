package visit.me.gil.mota.visitme.viewModels;

import android.databinding.ObservableField;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Observable;

import visit.me.gil.mota.visitme.useCases.GiveAccess;
import visit.me.gil.mota.visitme.useCases.UseCase;

public class UnexpectedVisitViewModel extends Observable implements UseCase.Result {

    public ObservableField<String> name;
    private Contract contract;
    private JSONObject visit;
    private ArrayList<String> photos;
    private GiveAccess giveAccess;

    public UnexpectedVisitViewModel(Contract contract, JSONObject visit, JSONArray photos) throws JSONException {
        this.contract = contract;
        this.name = new ObservableField<>(visit.getJSONObject("guest").getString("name"));
        this.visit = visit;
        this.photos = new ArrayList<>();
        fillPhotos(photos);
        giveAccess = new GiveAccess(this);
    }

    private void fillPhotos(JSONArray photos) throws JSONException {
        for (int i = 0; i < photos.length(); i++) {
            this.photos.add(photos.getString(i));
        }
    }

    public void accept(View view) {
        try {
            giveAccess.setVisit(visit.getString("id"));
            giveAccess.setAccess(true);
            giveAccess.run();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void decline(View view) {
        try {
            giveAccess.setVisit(visit.getString("id"));
            giveAccess.setAccess(false);
            giveAccess.run();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    @Override
    public void onError(String error) {
        contract.onError(error);
    }

    @Override
    public void onSuccess() {
        contract.close();
    }

    public interface Contract {
        void close();
        void onError(String error);
    }
}
