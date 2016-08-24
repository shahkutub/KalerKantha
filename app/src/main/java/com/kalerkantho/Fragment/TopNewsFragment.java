package com.kalerkantho.Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aapbd.utils.storage.PersistData;
import com.google.gson.Gson;
import com.kalerkantho.Adapter.TopNewsRecyAdapter;
import com.kalerkantho.Model.CommonNewsItem;
import com.kalerkantho.R;
import com.kalerkantho.Utils.AppConstant;
import com.kalerkantho.Utils.DividerItemDecoration;
import com.kalerkantho.holder.AllCommonNewsItem;
import com.kalerkantho.holder.AllNewsObj;

import java.util.ArrayList;
import java.util.List;

public class TopNewsFragment extends Fragment {
   private Context con;
   private AllNewsObj allObj;
   private List<AllCommonNewsItem> topnews = new ArrayList<AllCommonNewsItem>();
   private RecyclerView topnewRecList;
   private LinearLayoutManager mLayoutManager;
   private Drawable dividerDrawable;
   private TopNewsRecyAdapter tAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.topnews,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        con = getActivity();

        intiU();
    }

    private void intiU() {


        Gson g = new Gson();
        allObj = g.fromJson(PersistData.getStringData(con, AppConstant.HOMERESPONSE),AllNewsObj.class);

        topnews.clear();

        int i=0;

        for(CommonNewsItem topNews:allObj.getTop_news())
        {
            if(i==0)
            {
                AllCommonNewsItem singleObj=new AllCommonNewsItem();
                singleObj.setType("fullscreen");
                singleObj.setNews_obj(topNews);
                topnews.add(singleObj);

            }else
            {
                AllCommonNewsItem singleObj=new AllCommonNewsItem();
                singleObj.setType("defaultscreen");
                singleObj.setNews_obj(topNews);
                topnews.add(singleObj);
            }
            i++;
        }

        topnewRecList= (RecyclerView) getView().findViewById(R.id.topnewRecList);
        mLayoutManager = new LinearLayoutManager(con);
        topnewRecList.setLayoutManager(mLayoutManager);

        dividerDrawable = ContextCompat.getDrawable(con, R.drawable.divider);
        dividerDrawable.clearColorFilter();
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(dividerDrawable);
        topnewRecList.addItemDecoration(dividerItemDecoration);

        tAdapter = new TopNewsRecyAdapter(con,topnews);
        topnewRecList.setAdapter(tAdapter);

    }

}