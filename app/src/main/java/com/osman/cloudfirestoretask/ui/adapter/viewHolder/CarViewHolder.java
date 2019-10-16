package com.osman.cloudfirestoretask.ui.adapter.viewHolder;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.osman.cloudfirestoretask.R;
import com.osman.cloudfirestoretask.databinding.ItemCarBinding;
import com.osman.cloudfirestoretask.helper.Constants;
import com.osman.cloudfirestoretask.helper.GlideApp;

public class CarViewHolder extends RecyclerView.ViewHolder {

    public ItemCarBinding binding;

    public CarViewHolder(ItemCarBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    @BindingAdapter("tools:bind")
    public static void setImageUrl(ImageView view, String url) {
        if (url != null) {
            GlideApp.with(view.getContext())
                    .load(url)
                    .apply(RequestOptions.option(Option.memory(Constants.GLIDE_TIMEOUT), 0))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.error_placeholder)
                    .placeholder(R.drawable.placeholder)
                    .thumbnail(0.1f)
                    .into(view);
        }
    }
}
