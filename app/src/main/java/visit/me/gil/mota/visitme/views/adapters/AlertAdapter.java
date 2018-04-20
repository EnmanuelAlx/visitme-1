package visit.me.gil.mota.visitme.views.adapters;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.AlertItemBinding;
import visit.me.gil.mota.visitme.databinding.VisitItemBinding;
import visit.me.gil.mota.visitme.models.Alert;
import visit.me.gil.mota.visitme.views.adapters.holders.AlertViewHolder;
import visit.me.gil.mota.visitme.views.adapters.holders.BaseViewHolder;
import visit.me.gil.mota.visitme.views.adapters.holders.VisitViewHolder;

/**
 * Created by mota on 19/4/2018.
 */

public class AlertAdapter extends BaseRecyclerAdapter<Alert> {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AlertItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.alert_item,
                parent, false);
        return new AlertViewHolder(binding);
    }
}
