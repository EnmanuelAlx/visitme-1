package visit.me.gil.mota.visitme.views.adapters;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.VisitItemBinding;
import visit.me.gil.mota.visitme.models.Visit;
import visit.me.gil.mota.visitme.views.adapters.holders.BaseViewHolder;
import visit.me.gil.mota.visitme.views.adapters.holders.VisitViewHolder;

/**
 * Created by mota on 16/4/2018.
 */

public class VisitAdapter extends BaseRecyclerAdapter<Visit> {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        VisitItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.visit_item,
                parent, false);
        return new VisitViewHolder(binding);

    }
}
