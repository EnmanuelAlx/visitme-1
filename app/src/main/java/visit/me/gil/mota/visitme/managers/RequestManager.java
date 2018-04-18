package visit.me.gil.mota.visitme.managers;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import visit.me.gil.mota.visitme.MyApplication;
import visit.me.gil.mota.visitme.Urls;
import visit.me.gil.mota.visitme.utils.CustomMultipartRequest;
import visit.me.gil.mota.visitme.utils.RxRequestAdapter;
import visit.me.gil.mota.visitme.utils.SingletonRequester;


/**
 * Created by Slaush on 22/05/2017.
 */

public class RequestManager {
    private static RequestManager instance;
    private String urlBase;

    private RequestManager() {
        urlBase = "http://192.168.1.101:3000/api";
    }

    public static RequestManager getInstance() {
        if (instance == null)
            instance = new RequestManager();
        return instance;
    }

    public Observable<JSONObject> login(JSONObject obj) {
        return request(Request.Method.POST, urlBase + Urls.LOGIN, obj);
    }

    public Observable<JSONObject> register(HashMap<String, String> data, String image) {
        HashMap<String, String> images = new HashMap<>();
        images.put("image", image);
        return multipartRequest(Request.Method.POST, urlBase + Urls.REGISTER, data, images, null, null);
    }

    public Observable<JSONObject> getScheduledVisits(int skip, int limit) {
        return request(Request.Method.GET, urlBase + Urls.USER_VISITS_SCHEDULED + "?skip=" + skip + "&limit=" + limit, null);
    }

    public Observable<JSONObject> getFrequentVisits(int skip, int limit) {
        return request(Request.Method.GET, urlBase + Urls.USER_VISITS_FREQUENT + "?skip=" + skip + "&limit=" + limit, null);
    }

    public Observable<JSONObject> getSporadicVisits(int skip, int limit) {
        return request(Request.Method.GET, urlBase + Urls.USER_VISITS_SPORADIC+ "?skip=" + skip + "&limit=" + limit, null);
    }

    public Observable<JSONObject> getCommunities() {
        return request(Request.Method.GET, urlBase + Urls.USER_COMMUNITIES, null);
    }

    private Observable<JSONObject> request(int method, String url, JSONObject obj) {
        try {
            RxRequestAdapter<JSONObject> adapter = new RxRequestAdapter<>();
            JsonObjectRequest request = new JsonObjectRequest(method, url, obj, adapter, adapter) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<>();
                    String auth = UserManager.getInstance().getAuth();
                    if (!auth.equals(""))
                        headers.put("Authorization", "Bearer " + auth);
                    return headers;
                }
            };

            //request.getHeaders().put("Authentication",auth);
            SingletonRequester.getInstance(MyApplication.getInstance()).addToRequestQueue(request);
            return adapter.getObservable();
        } catch (Exception e) {
            return new Observable<JSONObject>() {
                @Override
                protected void subscribeActual(Observer<? super JSONObject> observer) {
                    observer.onError(new Throwable("Error Inesperado!"));
                }
            };
        }
    }

    public Observable<JSONObject> multipartRequest(int method, String url, Map<String, String> data,
                                                   Map<String, String> files, Map<String, JSONArray> arrays,
                                                   Map<String, JSONObject> jsons) {
        try {
            RxRequestAdapter<JSONObject> adapter = new RxRequestAdapter<>();
            CustomMultipartRequest request = new CustomMultipartRequest(method, url, adapter, adapter);
            String auth = UserManager.getInstance().getAuth();
            request.addHeader("Authentication", "Bearer " + auth);
            request.addDataMap(data);
            request.addFiles(files);
            request.addJsonsArray(arrays);
            request.addJsons(jsons);
            request.build();
            SingletonRequester.getInstance(MyApplication.getInstance()).addToRequestQueue(request);
            return adapter.getObservable();
        } catch (Exception e) {
            e.printStackTrace();

            return new Observable<JSONObject>() {
                @Override
                protected void subscribeActual(Observer<? super JSONObject> observer) {
                    observer.onError(new Throwable("Undefined Error!"));
                }
            };
        }
    }


    public Observable<JSONObject> createAlert(JSONObject params) {
        return request(Request.Method.POST, urlBase + Urls.CREATE_ALERT, params);
    }

    public Observable<JSONObject> addDevice(String device) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("device",device);
        return request(Request.Method.POST, urlBase + Urls.USER_DEVICES, obj);
    }

    public Observable<JSONObject> removeDevice(String device) {
        return request(Request.Method.DELETE, urlBase + Urls.USER_DEVICES+"/"+device, null);
    }
}
