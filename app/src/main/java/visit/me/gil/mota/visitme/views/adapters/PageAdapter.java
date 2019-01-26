package visit.me.gil.mota.visitme.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import visit.me.gil.mota.visitme.models.Visit;
import visit.me.gil.mota.visitme.views.fragments.DashboardFragment;
import visit.me.gil.mota.visitme.views.fragments.InvitationsFragment;
import visit.me.gil.mota.visitme.views.fragments.JoinCommunityFragment;
import visit.me.gil.mota.visitme.views.fragments.VisitsFragment;

/**
 * Created by mota on 15/4/2018.
 */

public class PageAdapter extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    private VisitsFragment visits;
    private DashboardFragment alerts;
    private InvitationsFragment invitations;
    private JoinCommunityFragment communities;
    private Fragment[] fragments;


    //Constructor to the class
    public PageAdapter(FragmentManager fm, boolean waiting) {
        super(fm);
        //Initializing tab count

        invitations = new InvitationsFragment();
        communities = new JoinCommunityFragment();
        if (waiting) {
            fragments = new Fragment[]{invitations, communities};
        } else {
            visits = new VisitsFragment();
            alerts = new DashboardFragment();
            fragments = new Fragment[]{visits, alerts, invitations, communities};
        }
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        return fragments[position];

    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return fragments.length;
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
    }
}