package visit.me.gil.mota.visitme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;

import com.onesignal.OneSignal;

import visit.me.gil.mota.visitme.managers.UserManager;
import visit.me.gil.mota.visitme.views.activities.LoginActivity;


/**
 * Created by Slaush on 15/05/2017.
 */

public class MyApplication extends android.app.Application
{
    private static MyApplication instance;
    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }

    public static MyApplication getInstance() {
        return instance;
    }


    public void goToLoginActivity(Context context)
    {
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
        if(context instanceof Activity)
            ((Activity)context).finish();

    }

}
