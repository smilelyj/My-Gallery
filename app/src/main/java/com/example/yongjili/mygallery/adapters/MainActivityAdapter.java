package com.example.yongjili.mygallery.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yongjili.mygallery.R;
import com.example.yongjili.mygallery.activities.DetailActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yongjili on 8/5/17.
 */

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {
    private Context context;
    private HashMap<String, List<String>> filePathHashMap;
    List<String> mKeys;

    public MainActivityAdapter(Context context, HashMap<String, List<String>> filePathHashMap) {
        this.context = context;
        this.filePathHashMap = filePathHashMap;
        mKeys = new ArrayList<>(filePathHashMap.keySet());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String filePath = filePathHashMap.get(mKeys.get(position)).get(0);
        Glide.with(context)
                .load(Uri.parse("file:///android_asset/" + filePath))
                .thumbnail(0.3f)
                .into(holder.mImageView);
        holder.mTitleView.setText(mKeys.get(position));
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_long_press);

                ViewPager pager = (ViewPager) dialog.findViewById(R.id.container);
                pager.setAdapter(new LongPressPagerAdapter(context,filePathHashMap.get(mKeys.get(position))));
                dialog.show();

                //Grab the window of the dialog, and change the width
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                Window window = dialog.getWindow();
                lp.copyFrom(window.getAttributes());
                //This makes the dialog take up the full width
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);
                return true;
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                List<String> fileInfo = filePathHashMap.get(mKeys.get(position));

                intent.putStringArrayListExtra("fileInfo", new ArrayList<>(fileInfo));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mKeys.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final ImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.main_title_text_view);
            mImageView = (ImageView) view.findViewById(R.id.image_view);
        }
    }
}
