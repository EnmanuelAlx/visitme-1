package visit.me.gil.mota.visitme.viewModels;

import android.content.Context;

import java.util.Observable;

/**
 * Created by mota on 15/4/2018.
 */

public class DashboardViewModel extends Observable {
    private Context context;

    public DashboardViewModel(Context context) {
        this.context = context;
    }
}
