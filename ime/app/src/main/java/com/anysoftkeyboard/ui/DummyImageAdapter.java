package com.anysoftkeyboard.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.menny.android.anysoftkeyboard.R;

import java.util.List;

public class DummyImageAdapter extends RecyclerView.Adapter<DummyImageAdapter.ImageViewHolder> {

    public interface OnImageClickListener {
        void onClick(String url);
    }

    private final Context mContext;
    private final List<String> mImageUrls;
    private final OnImageClickListener mListener;

    public DummyImageAdapter(Context context, List<String> urls, OnImageClickListener listener) {
        mContext = context;
        mImageUrls = urls;
        mListener = listener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.floating_image_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String url = mImageUrls.get(position);
        Glide.with(mContext).load(url).error(R.drawable.blacktheme_key_action_pressed).into(holder.imageView);
        holder.imageView.setOnClickListener(v -> mListener.onClick(url));
        Log.d("DummyImageAdapter", "Trying to load image: " + url);
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imageView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}