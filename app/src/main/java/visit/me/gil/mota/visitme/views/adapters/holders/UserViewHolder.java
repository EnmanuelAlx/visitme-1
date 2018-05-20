package visit.me.gil.mota.visitme.views.adapters.holders;

import android.view.View;

import com.bumptech.glide.Glide;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.UserItemBinding;
import visit.me.gil.mota.visitme.models.User;
import visit.me.gil.mota.visitme.viewModels.ItemUserViewModel;
import visit.me.gil.mota.visitme.viewModels.ItemVisitViewModel;

public class UserViewHolder extends BaseViewHolder<User>{

    private UserItemBinding binding;
    private ItemUserViewModel.Contract contract;
    public UserViewHolder(UserItemBinding binding, ItemUserViewModel.Contract contract) {
        super(binding.getRoot());
        this.binding = binding;
        this.contract = contract;
    }

    @Override
    public void onBind() {
        if (binding.getViewModel() == null)
            binding.setViewModel(new ItemUserViewModel(contract,item));
        else
            binding.getViewModel().setUser(item);

        Glide.with(itemView.getContext())
                .load(item.getImage())
                .placeholder(R.drawable.guy)
                .error(R.drawable.guy)
                .dontAnimate()
                .into(binding.image);
    }
}
