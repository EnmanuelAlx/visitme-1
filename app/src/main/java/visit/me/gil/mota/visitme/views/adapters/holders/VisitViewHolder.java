package visit.me.gil.mota.visitme.views.adapters.holders;

import android.view.View;

import com.bumptech.glide.Glide;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.VisitItemBinding;
import visit.me.gil.mota.visitme.models.Visit;
import visit.me.gil.mota.visitme.viewModels.ItemVisitViewModel;

/**
 * Created by mota on 16/4/2018.
 */

public class VisitViewHolder extends BaseViewHolder<Visit> {
    private VisitItemBinding binding;

    public VisitViewHolder(VisitItemBinding itemView) {
        super(itemView.itemVisit);
        binding = itemView;
    }

    @Override
    public void onBind() {
        if (binding.getViewModel() == null)
            binding.setViewModel(new ItemVisitViewModel(item, itemView.getContext()));
        else
            binding.getViewModel().setVisit(item);

        Glide.with(itemView.getContext())
                .load(item.getGuest().getImage())
                .placeholder(R.drawable.guy)
                .error(R.drawable.guy)
                .dontAnimate()
                .into(binding.image);
    }
}
