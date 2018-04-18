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
public class SelectVisitTypeFragment extends Fragment {


    public SelectVisitTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_visit_type, container, false);
    }

}
