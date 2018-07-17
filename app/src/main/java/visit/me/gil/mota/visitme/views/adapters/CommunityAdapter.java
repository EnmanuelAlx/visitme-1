package visit.me.gil.mota.visitme.views.adapters;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.CommunityItemBinding;
import visit.me.gil.mota.visitme.models.Community;
import visit.me.gil.mota.visitme.viewModels.ItemCommunityViewModel;
import visit.me.gil.mota.visitme.views.adapters.holders.BaseViewHolder;
import visit.me.gil.mota.visitme.views.adapters.holders.CommunityViewHolder;

public class CommunityAdapter extends BaseRecyclerAdapter<Community> {
    private ItemCommunityViewModel.Contract contract;

    public CommunityAdapter(ItemCommunityViewModel.Contract contract) {
        this.contract = contract;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CommunityItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.community_item,
                parent, false);
        return new CommunityViewHolder(binding, contract);

    }

}
