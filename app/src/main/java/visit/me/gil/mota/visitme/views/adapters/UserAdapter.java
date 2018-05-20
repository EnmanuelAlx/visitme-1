package visit.me.gil.mota.visitme.views.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.otaliastudios.autocomplete.AutocompletePresenter;
import com.otaliastudios.autocomplete.RecyclerViewPresenter;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.UserItemBinding;
import visit.me.gil.mota.visitme.models.User;
import visit.me.gil.mota.visitme.viewModels.ItemUserViewModel;
import visit.me.gil.mota.visitme.views.adapters.holders.BaseViewHolder;
import visit.me.gil.mota.visitme.views.adapters.holders.UserViewHolder;

public class UserAdapter extends BaseRecyclerAdapter<User> {

    ItemUserViewModel.Contract contract;

    public UserAdapter(ItemUserViewModel.Contract contract) {
        this.contract = contract;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UserItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.user_item,
                parent, false);
        return new UserViewHolder(binding, contract);
    }
}
