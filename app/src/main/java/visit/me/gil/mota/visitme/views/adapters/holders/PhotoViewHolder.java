package visit.me.gil.mota.visitme.views.adapters.holders;

import com.bumptech.glide.Glide;

import java.io.File;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.PhotoItemBinding;
import visit.me.gil.mota.visitme.managers.RequestManager;
import visit.me.gil.mota.visitme.viewModels.ItemPhotoViewModel;


public class PhotoViewHolder extends BaseViewHolder<String> {

    private PhotoItemBinding binding;
    public PhotoViewHolder(PhotoItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    @Override
    public void onBind() {
        if (binding.getViewModel() == null)
            binding.setViewModel(new ItemPhotoViewModel(item, itemView.getContext()));
        else
            binding.getViewModel().setPhoto(item);

        Glide.with(itemView.getContext())
                .load(RequestManager.getInstance().getUrl()+item)
                .placeholder(R.drawable.guy)
                .error(R.drawable.guy)
                .dontAnimate()
                .into(binding.image);
    }
}
