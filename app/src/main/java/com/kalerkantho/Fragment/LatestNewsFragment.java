package com.kalerkantho.Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aapbd.utils.storage.PersistData;
import com.google.gson.Gson;
import com.kalerkantho.Adapter.LatestRecyAdapter;
import com.kalerkantho.Model.CommonNewsItem;
import com.kalerkantho.R;
import com.kalerkantho.Utils.AllURL;
import com.kalerkantho.Utils.AppConstant;
import com.kalerkantho.Utils.DividerItemDecoration;
import com.kalerkantho.Utils.GridSpacingItemDecoration;
import com.kalerkantho.holder.AllCommonNewsItem;
import com.kalerkantho.holder.AllNewsObj;

import java.util.ArrayList;
import java.util.List;

public class LatestNewsFragment extends Fragment {
   private Context con;
   private AllNewsObj allObj;
   private List<AllCommonNewsItem> latestNews = new ArrayList<AllCommonNewsItem>();
   private RecyclerView latestNewRecList;
  // private LinearLayoutManager mLayoutManager;
   private Drawable dividerDrawable;
   private LatestRecyAdapter lAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.latestnews,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        con = getActivity();


    }

    private void intiU() {

        Gson g = new Gson();
        allObj = g.fromJson(PersistData.getStringData(con, AppConstant.HOMERESPONSE),AllNewsObj.class);

        latestNews.clear();

        for(CommonNewsItem topNews:allObj.getLatest_news())
        {
                 AllCommonNewsItem singleObj=new AllCommonNewsItem();
                 singleObj.setType("fullscreen");
                 singleObj.setNews_obj(topNews);
                 latestNews.add(singleObj);

            }


        latestNewRecList= (RecyclerView) getView().findViewById(R.id.latestNewRecList);

        latestNewRecList.setLayoutManager(new GridLayoutManager(con, 2));
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(con, R.dimen.space);
        latestNewRecList.addItemDecoration(itemDecoration);

        lAdapter = new LatestRecyAdapter(con,latestNews,null);
        latestNewRecList.setAdapter(lAdapter);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser){
            Handler handler= new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    intiU();
                }
            },100);
        }
    }
}