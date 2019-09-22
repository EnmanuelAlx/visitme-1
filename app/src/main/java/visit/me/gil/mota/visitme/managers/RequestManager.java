package visit.me.gil.mota.visitme.managers;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Function;
import visit.me.gil.mota.visitme.MyApplication;
import visit.me.gil.mota.visitme.Urls;
import visit.me.gil.mota.visitme.models.Community;
import visit.me.gil.mota.visitme.utils.CustomMultipartRequest;
import visit.me.gil.mota.visitme.utils.RxRequestAdapter;
import visit.me.gil.mota.visitme.utils.SingletonRequester;


/**
 * Created by Slaush on 22/05/2017.
 */

public class RequestManager {
    private static RequestManager instance;
    private String url, urlApi;
    
    private RequestManager() {
        //url = "http://visitme.southcentralus.cloudapp.azure.com:3001/";
        //urlApi = "http://visitme.southcentralus.cloudapp.azure.com:3001/api";
        url = "http://192.168.0.104:3000/";
        urlApi = "http://192.168.0.104:3000/api";
    }

    public static RequestManager getInstance() {
        if (instance == null)
            instance = new RequestManager();
        return instance;
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

    private Observable<JSONObject> multipartRequest(int method, String url, Map<String, String> data,
                                                    Map<String, String> files, Map<String, JSONArray> arrays,
                                                    Map<String, JSONObject> jsons) {
        try {
            RxRequestAdapter<JSONObject> adapter = new RxRequestAdapter<>();
            CustomMultipartRequest request = new CustomMultipartRequest(method, url, adapter, adapter);
            String auth = UserManager.getInstance().getAuth();
            request.addHeader("Authorization", "Bearer " + auth);
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
                    observer.onError(new Throwable("Error Inesperado"));
                }
            };
        }
    }


    public Observable<JSONObject> login(JSONObject obj) {
        return request(Request.Method.POST, urlApi + Urls.LOGIN, obj);
    }

    public Observable<JSONObject> register(HashMap<String, String> data, String image) {
        HashMap<String, String> images = new HashMap<>();
        images.put("image", image);
        return multipartRequest(Request.Method.POST, urlApi + Urls.REGISTER, data, images, null, null);
    }

    public Observable<JSONObject> editProfile(HashMap<String, String> data, String image) {
        HashMap<String, String> images = new HashMap<>();
        if (image != null && !image.isEmpty())
            images.put("image", image);
        return multipartRequest(Request.Method.PUT, urlApi + Urls.USER_PROFILE, data, images, null, null);
    }

    public Observable<JSONObject> getScheduledVisits(int skip, int limit) {
        return request(Request.Method.GET, urlApi + Urls.USER_VISITS_SCHEDULED + "?skip=" + skip + "&limit=" + limit, null);
    }

    public Observable<JSONObject> getGuestsVisits(int skip, int limit) {
        return request(Request.Method.GET, urlApi + Urls.GUESTS_VISITS + "?skip=" + skip + "&limit=" + limit, null);
    }

    public Observable<JSONObject> getFrequentVisits(int skip, int limit) {
        return request(Request.Method.GET, urlApi + Urls.USER_VISITS_FREQUENT + "?skip=" + skip + "&limit=" + limit, null);
    }

    public Observable<JSONObject> getSporadicVisits(int skip, int limit) {
        return request(Request.Method.GET, urlApi + Urls.USER_VISITS_SPORADIC + "?skip=" + skip + "&limit=" + limit, null);
    }

    public Observable<JSONObject> getUserCommunities() {
        return request(Request.Method.GET, urlApi + Urls.USER_COMMUNITIES, null);
    }


    public Observable<JSONObject> createAlert(JSONObject params) {
        return request(Request.Method.POST, urlApi + Urls.CREATE_ALERT, params);
    }

    public Observable<JSONObject> addDevice(String device) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("device", device);
        return request(Request.Method.POST, urlApi + Urls.USER_DEVICES, obj);
    }

    public Observable<JSONObject> removeDevice(String device) {
        return request(Request.Method.DELETE, urlApi + Urls.USER_DEVICES + "/" + device, null);
    }


    public Observable<JSONObject> getIncidentAlerts(int skip, int limit) {
        return request(Request.Method.GET, urlApi + Urls.USER_ALERTS_INCIDENT + "?skip=" + skip + "&limit=" + limit, null);
    }

    public Observable<JSONObject> getInformationAlerts(int skip, int limit) {
        return request(Request.Method.GET, urlApi + Urls.USER_ALERTS_INFORMATION + "?skip=" + skip + "&limit=" + limit, null);
    }

    public Observable<JSONObject> getOtherAlerts(int skip, int limit) {
        return request(Request.Method.GET, urlApi + Urls.USER_ALERTS_OTHER + "?skip=" + skip + "&limit=" + limit, null);
    }

    public Observable<JSONObject> createVisit(JSONObject params) {
        return request(Request.Method.POST, urlApi + Urls.VISITS, params);
    }

    public Observable<JSONObject> deleteVisit(String visit) throws JSONException {
        return request(Request.Method.DELETE, urlApi + Urls.VISITS + "/" + visit, null);
    }

    public Observable<JSONObject> forgotPassword(String email) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("email", email);
        return request(Request.Method.POST, urlApi + Urls.FORGOT_PASSWORD, obj);
    }

    public Observable<JSONObject> sendPasswordCode(String code, String email) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("email", email);
        obj.put("code", code);
        return request(Request.Method.POST, urlApi + Urls.FORGOT_PASSWORD_CODE, obj);
    }

    public Observable<JSONObject> changePassword(String password, String email, String code) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("email", email);
        obj.put("password", password);
        obj.put("code", code);
        return request(Request.Method.POST, urlApi + Urls.CHANGE_PASSWORD, obj);
    }

    public Observable<JSONObject> getCompanies(String query) {
        return request(Request.Method.GET, urlApi + Urls.COMPANIES + "?query=" + query, null);
    }

    public String getUrl() {
        return url;
    }

    public Observable<JSONObject> editVisit(String visitId, JSONObject params) {
        return request(Request.Method.PUT, urlApi + Urls.VISITS + "/" + visitId, params);
    }

    public Observable<JSONObject> findFirstUserThatMatch(String identification) {
        return request(Request.Method.GET, urlApi + Urls.FIND_FIRST_USER + "?identification=" + identification, null);
    }

    public Observable<JSONObject> giveAccess(String visit, boolean access) {
        return request(Request.Method.PUT, urlApi + Urls.GIVE_ACCESS.replace(":visit", visit) + "?access=" + access, null);
    }

    public Observable<JSONArray> getCommunities(String search) {
        return requestArray(Request.Method.GET, urlApi + Urls.COMMUNITIES + "?query=" + search);
    }

    private Observable<JSONArray> requestArray(int method, String url) {
        try {
            final RxRequestAdapter<String> adapter = new RxRequestAdapter<>();
            StringRequest request = new StringRequest(method, url, adapter, adapter) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<>();
                    String auth = UserManager.getInstance().getAuth();
                    if (!auth.equals(""))
                        headers.put("Authorization", "Bearer " + auth);
                    return headers;
                }
            };
            SingletonRequester.getInstance(MyApplication.getInstance()).addToRequestQueue(request);


            return adapter.getObservable().flatMap(new Function<String, ObservableSource<JSONArray>>() {
                @Override
                public ObservableSource<JSONArray> apply(final String s) {
                    return new Observable<JSONArray>() {
                        @Override
                        protected void subscribeActual(Observer<? super JSONArray> observer) {
                            try {
                                observer.onNext(new JSONArray(s));
                                observer.onComplete();
                            } catch (JSONException e) {
                                observer.onError(new Throwable("error inesperado"));
                            }
                        }
                    };
                }
            });
        } catch (Exception e) {
            return new Observable<JSONArray>() {
                @Override
                protected void subscribeActual(Observer<? super JSONArray> observer) {
                    observer.onError(new Throwable("Error Inesperado!"));
                }
            };
        }
    }

    public Observable<JSONObject> joinCommunity(Community community, String reference) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("reference", reference);
        return request(Request.Method.POST, urlApi + Urls.JOIN_COMMUNITY.replace(":community", community.get_id()), obj);
    }
}
