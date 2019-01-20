package visit.me.gil.mota.visitme.views.adapters.holders;

import com.bumptech.glide.Glide;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.InvitationItemBinding;
import visit.me.gil.mota.visitme.models.Visit;
import visit.me.gil.mota.visitme.viewModels.ItemInvitationViewModel;

public class InvitationViewHolder extends BaseViewHolder<Visit> {

    private InvitationItemBinding binding;
    private ItemInvitationViewModel.Contract contract;

    public InvitationViewHolder(InvitationItemBinding itemView, ItemInvitationViewModel.Contract contract) {
        super(itemView.itemVisit);
        binding = itemView;
        this.contract = contract;
    }

    @Override
    public void onBind() {
        if (binding.getViewModel() == null)
            binding.setViewModel(new ItemInvitationViewModel(item, contract));
        else
            binding.getViewModel().setVisit(item);

        Glide.with(itemView.getContext())
                .load(item.getResident().getImage())
                .placeholder(R.drawable.guy)
                .error(R.drawable.guy)
                .dontAnimate()
                .into(binding.image);
    }
}
