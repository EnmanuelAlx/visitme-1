package visit.me.gil.mota.visitme.views.adapters;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.InvitationItemBinding;
import visit.me.gil.mota.visitme.models.Visit;
import visit.me.gil.mota.visitme.viewModels.ItemInvitationViewModel;
import visit.me.gil.mota.visitme.views.adapters.holders.BaseViewHolder;
import visit.me.gil.mota.visitme.views.adapters.holders.InvitationViewHolder;

public class InvitationAdapter extends BaseRecyclerAdapter<Visit> {
    private ItemInvitationViewModel.Contract contract;

    public InvitationAdapter(ItemInvitationViewModel.Contract contract) {
        this.contract = contract;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        InvitationItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.invitation_item,
                parent, false);
        return new InvitationViewHolder(binding, contract);

    }
}
