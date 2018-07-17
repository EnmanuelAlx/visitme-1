package visit.me.gil.mota.visitme.views.adapters.holders;

import com.bumptech.glide.Glide;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.CommunityItemBinding;
import visit.me.gil.mota.visitme.models.Community;
import visit.me.gil.mota.visitme.viewModels.ItemCommunityViewModel;

public class CommunityViewHolder extends BaseViewHolder<Community> {
    private CommunityItemBinding binding;
    private ItemCommunityViewModel.Contract contract;
    public CommunityViewHolder(CommunityItemBinding itemView, ItemCommunityViewModel.Contract contract) {
        super(itemView.root);
        binding = itemView;
        this.contract = contract;
    }

    @Override
    public void onBind() {
        if (binding.getViewModel() == null)
            binding.setViewModel(new ItemCommunityViewModel(item, contract));
        else
            binding.getViewModel().setCommunity(item);

        Glide.with(itemView.getContext())
                .load(item.getImage())
                .placeholder(R.drawable.house)
                .error(R.drawable.house)
                .dontAnimate()
                .into(binding.image);
    }
}