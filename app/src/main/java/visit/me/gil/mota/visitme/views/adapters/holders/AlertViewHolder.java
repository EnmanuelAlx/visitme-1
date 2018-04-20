package visit.me.gil.mota.visitme.views.adapters.holders;

import android.view.View;

import com.bumptech.glide.Glide;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.AlertItemBinding;
import visit.me.gil.mota.visitme.models.Alert;
import visit.me.gil.mota.visitme.viewModels.ItemAlertViewModel;

/**
 * Created by mota on 19/4/2018.
 */

public class AlertViewHolder extends BaseViewHolder<Alert> {
    private AlertItemBinding binding;
    public AlertViewHolder(AlertItemBinding binding) {
        super(binding.itemAlert);
        this.binding = binding;
    }

    @Override
    public void onBind() {
        if (binding.getViewModel() == null)
            binding.setViewModel(new ItemAlertViewModel(item, itemView.getContext()));
        else
            binding.getViewModel().setAlert(item);

        Glide.with(itemView.getContext())
                .load(item.getAuthor().getImage())
                .placeholder(R.drawable.guy)
                .error(R.drawable.guy)
                .dontAnimate()
                .into(binding.image);
    }
}
