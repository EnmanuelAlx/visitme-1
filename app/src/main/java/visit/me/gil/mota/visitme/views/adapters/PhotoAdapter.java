package visit.me.gil.mota.visitme.views.adapters;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.io.File;

import visit.me.gil.mota.visitme.R;
import visit.me.gil.mota.visitme.databinding.PhotoItemBinding;
import visit.me.gil.mota.visitme.views.adapters.holders.BaseViewHolder;
import visit.me.gil.mota.visitme.views.adapters.holders.PhotoViewHolder;


public class PhotoAdapter extends BaseRecyclerAdapter<String>{
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PhotoItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.photo_item,
                parent, false);
        return new PhotoViewHolder(binding);
    }

}
