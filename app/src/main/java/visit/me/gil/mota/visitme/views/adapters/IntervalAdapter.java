package visit.me.gil.mota.visitme.views.adapters;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.IntervalItem1Binding;
import visit.me.gil.mota.visitme.databinding.IntervalItemBinding;
import visit.me.gil.mota.visitme.models.Interval;
import visit.me.gil.mota.visitme.views.adapters.holders.BaseViewHolder;
import visit.me.gil.mota.visitme.views.adapters.holders.IntervalViewHolder;

/**
 * Created by mota on 28/4/2018.
 */

public class IntervalAdapter extends BaseRecyclerAdapter<Interval> {

    public static final int SHOWABLE_TYPE = 0;
    public static final int EDITABLE_TYPE = 1;
    private int type;

    public IntervalAdapter(int type) {
        this.type = type;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (type == SHOWABLE_TYPE) {
            IntervalItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.interval_item,
                    parent, false);
            return new IntervalViewHolder(binding);
        } else {
            IntervalItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.interval_item_1,
                    parent, false);
            return new IntervalViewHolder(binding);
        }

    }
}
