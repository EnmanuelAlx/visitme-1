package visit.me.gil.mota.visitme.managers;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import visit.me.gil.mota.visitme.Consts;
import visit.me.gil.mota.visitme.MyApplication;
import visit.me.gil.mota.visitme.models.Community;
import visit.me.gil.mota.visitme.models.User;
import visit.me.gil.mota.visitme.utils.Functions;
import visit.me.gil.mota.visitme.utils.PreferencesHelper;


/**
 * Created by Slaush on 22/05/2017.
 */

public class UserManager
{
    private static UserManager instance;

    private UserManager()
    {

    }

    public static UserManager getInstance()
    {
        if(instance == null )
            instance = new UserManager();
        return  instance;
    }


    public void saveUserCredentials(User user, String auth, String deviceID) throws JSONException {
        PreferencesHelper.writeString(MyApplication.getInstance(), Consts.USER, Functions.toJSON(user).toString());
        PreferencesHelper.writeString(MyApplication.getInstance(), Consts.AUTH, auth);
        PreferencesHelper.writeString(MyApplication.getInstance(), Consts.DEVICE_ID, deviceID);
    }

    public void saveUserCredentials(User user) throws JSONException {
        PreferencesHelper.writeString(MyApplication.getInstance(), Consts.USER, Functions.toJSON(user).toString());
    }



    public User getUser(Context context) throws JSONException {
        String jsonStr = PreferencesHelper.readString(context,Consts.USER,"");
        return Functions.parse(new JSONObject(jsonStr), User.class);
    }

    public String getAuth()
    {
        return PreferencesHelper.readString(MyApplication.getInstance(), Consts.AUTH,"");
    }

    public void logout()
    {
        PreferencesHelper.deleteKey(MyApplication.getInstance(),Consts.USERNAME);
        PreferencesHelper.deleteKey(MyApplication.getInstance(),Consts.PASSWORD);
        PreferencesHelper.deleteKey(MyApplication.getInstance(), Consts.USER_ID);
        PreferencesHelper.deleteKey(MyApplication.getInstance(), Consts.AUTH);
        PreferencesHelper.deleteKey(MyApplication.getInstance(), Consts.DEVICE_ID);
    }


    public void saveCommunities(JSONObject obj) {
        PreferencesHelper.writeString(MyApplication.getInstance(), Consts.COMMUNITIES, obj.toString());
    }

    public List<Community> getCommunities() throws JSONException {
        String jsonStr = PreferencesHelper.readString(MyApplication.getInstance(),Consts.COMMUNITIES,"");
        JSONObject obj = new JSONObject(jsonStr);
        JSONArray arry = obj.getJSONArray(Consts.COMMUNITIES);
        Community [] communities = Functions.parse(arry,Community[].class);
        return Arrays.asList(communities);
    }

    public String getDevice() {
        return PreferencesHelper.readString(MyApplication.getInstance(), Consts.DEVICE_ID,"");
    }
}
