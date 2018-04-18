package visit.me.gil.mota.visitme.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import visit.me.gil.mota.visitme.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuestDataFragment extends Fragment {


    public GuestDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guest_data, container, false);
    }

}
