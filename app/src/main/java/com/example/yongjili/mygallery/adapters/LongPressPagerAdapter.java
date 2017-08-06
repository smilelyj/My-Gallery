package com.example.yongjili.mygallery.adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.yongjili.mygallery.R;

import java.util.List;

/**
 * Created by yongjili on 8/6/17.
 */


public class LongPressPagerAdapter extends PagerAdapter {
    private Context context;
    private List<String> values;
    private LayoutInflater inflater;

    public LongPressPagerAdapter(Context context, List<String> values) {
        this.context = context;
        this.values = values;
        inflater = ((Activity)context).getLayoutInflater();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View rootView = inflater.inflate(R.layout.dialog_pager_contents, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.image_view);

        rootView.setTag(position);
        container.addView(rootView);
        Glide.with(context)
                .load(Uri.parse("file:///android_asset/" + values.get(position)))
                .thumbnail(0.3f)
                .into(imageView);
        return rootView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return values.size();
    }
}