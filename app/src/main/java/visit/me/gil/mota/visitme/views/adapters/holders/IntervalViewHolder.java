package visit.me.gil.mota.visitme.views.adapters.holders;

import android.view.View;

import visit.me.gil.mota.visitme.databinding.IntervalItem1Binding;
import visit.me.gil.mota.visitme.databinding.IntervalItemBinding;
import visit.me.gil.mota.visitme.models.Interval;
import visit.me.gil.mota.visitme.viewModels.ItemIntervalViewModel;

/**
 * Created by mota on 28/4/2018.
 */

public class IntervalViewHolder extends BaseViewHolder<Interval> {
    private ItemIntervalViewModel viewModel;
    private IntervalItemBinding binding;

    public IntervalViewHolder(IntervalItemBinding itemView) {
        super(itemView.getRoot());
        binding = itemView;
    }

    @Override
    public void onBind() {
        if (binding.getViewModel() == null)
            binding.setViewModel(new ItemIntervalViewModel(itemView.getContext(), item));
        else
            binding.getViewModel().setInterval(item);
    }
}
