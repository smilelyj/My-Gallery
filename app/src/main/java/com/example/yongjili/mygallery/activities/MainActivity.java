package com.example.yongjili.mygallery.activities;

import com.example.yongjili.mygallery.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.bumptech.glide.Glide;
import com.example.yongjili.mygallery.adapters.MainActivityAdapter;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    HashMap<String, List<String>> mFilePathHashMap;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(getResources().getColor(R.color.gray_300));
        mFilePathHashMap = new HashMap<>();
        listAssetFiles("");
        mContext = this;

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private boolean listAssetFiles(String path) {
        String[] list;
        try {
            list = getAssets().list(path);
            if (list.length > 0) {
                for (String folderName : list) {
                    if (!TextUtils.isEmpty(folderName) && !(folderName.equals("images") ||
                            folderName.equals("webkit") ||
                            folderName.equals("sounds"))) {
                        String[] secondList = getAssets().list(folderName);
                        List<String> filePaths = new ArrayList<>();
                        StringBuilder sb = new StringBuilder(128);

                        for (String s : secondList) {
                            sb.append(folderName).append("/").append(s);
                            filePaths.add(sb.toString());
                            //reset
                            sb.setLength(0);
                        }
                        mFilePathHashMap.put(folderName, filePaths);
                    }
                }
            }

        } catch (IOException e) {
            Log.d(TAG, e.toString());
            return false;
        }
        return true;
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new MainActivityAdapter(mContext, mFilePathHashMap));
    }
}
