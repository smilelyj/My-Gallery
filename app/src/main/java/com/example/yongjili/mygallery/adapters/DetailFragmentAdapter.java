package com.example.yongjili.mygallery.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yongjili.mygallery.R;

import java.util.List;

/**
 * Created by yongjili on 8/6/17.
 */

public class DetailFragmentAdapter
        extends RecyclerView.Adapter<DetailFragmentAdapter.ViewHolder> {
    private Context context;
    private List<String> filePaths;

    public DetailFragmentAdapter(Context context, List<String> filePaths){
        this.context = context;
        this.filePaths = filePaths;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        String filePath = filePaths.get(position);
        Glide.with(context)
                .load(Uri.parse("file:///android_asset/" + filePath))
                .thumbnail(0.3f)
                .into(holder.mImageView);

        holder.mFileNameTextView.setText(filePath.substring(filePath.indexOf("/")+1));
    }

    @Override
    public int getItemCount() {
        return filePaths.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mFileNameTextView;
        public final ImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mFileNameTextView = (TextView) view.findViewById(R.id.title);
            mImageView = (ImageView) view.findViewById(R.id.image_view);
        }
    }
}