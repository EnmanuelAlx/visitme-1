package visit.me.gil.mota.visitme.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import visit.me.gil.mota.visitme.models.Visit;
import visit.me.gil.mota.visitme.views.fragments.DashboardFragment;
import visit.me.gil.mota.visitme.views.fragments.VisitsFragment;

/**
 * Created by mota on 15/4/2018.
 */

public class PageAdapter extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    private int tabCount;
    private VisitsFragment visits;
    private DashboardFragment alerts;
    //Constructor to the class
    public PageAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount = tabCount;
        visits = new VisitsFragment();
        alerts = new DashboardFragment();
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                return visits;
            case 1:
                return alerts;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
    }
}