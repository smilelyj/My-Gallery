package com.example.yongjili.mygallery.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yongjili.mygallery.R;
import com.example.yongjili.mygallery.adapters.DetailFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yongjili on 8/5/17.
 */

public class DetailFragment extends Fragment {

    private List<String> mFilePaths;

    public DetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.gray_300));

        mFilePaths = new ArrayList<>();

        if (getArguments().containsKey("fileInfo")) {
            mFilePaths = getArguments().getStringArrayList("fileInfo");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail_list, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.item_list);

        if (mFilePaths.size() != 0) {
            recyclerView.setAdapter(new DetailFragmentAdapter(this.getContext(), mFilePaths));
        }
        return rootView;
    }
}