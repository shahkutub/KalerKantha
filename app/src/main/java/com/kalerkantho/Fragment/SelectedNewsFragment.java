package com.kalerkantho.Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aapbd.utils.storage.PersistData;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.kalerkantho.Adapter.LatestRecyAdapter;
import com.kalerkantho.Model.CommonNewsItem;
import com.kalerkantho.R;
import com.kalerkantho.Utils.AppConstant;
import com.kalerkantho.Utils.GridSpacingItemDecoration;
import com.kalerkantho.holder.AllCommonNewsItem;
import com.kalerkantho.holder.AllNewsObj;

import java.util.ArrayList;
import java.util.List;

public class SelectedNewsFragment extends Fragment {
   private Context con;
   private AllNewsObj allObj;
   private List<AllCommonNewsItem> selectedNews= new ArrayList<AllCommonNewsItem>();
   private RecyclerView selectedNewRecList;
   private LinearLayoutManager mLayoutManager;
   private Drawable dividerDrawable;
   private LatestRecyAdapter sAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.selectednews,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        con = getActivity();

        intiU();
    }

    private void intiU() {


        try {
            Gson g = new Gson();
            if (!(TextUtils.isEmpty(PersistData.getStringData(con, AppConstant.HOMERESPONSE)))){
                allObj = g.fromJson(PersistData.getStringData(con, AppConstant.HOMERESPONSE),AllNewsObj.class);

                selectedNews.clear();

                for(CommonNewsItem topNews:allObj.getSelected_news())
                {
                    AllCommonNewsItem singleObj=new AllCommonNewsItem();
                    singleObj.setType("fullscreen");
                    singleObj.setNews_obj(topNews);
                    selectedNews.add(singleObj);

                }


                selectedNewRecList= (RecyclerView) getView().findViewById(R.id.selectedNewRecList);
                selectedNewRecList.setLayoutManager(new GridLayoutManager(con, 2));
                GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(con, R.dimen.space);
                selectedNewRecList.addItemDecoration(itemDecoration);

                sAdapter = new LatestRecyAdapter(con,selectedNews,null);
                selectedNewRecList.setAdapter(sAdapter);
            }

        }catch (JsonSyntaxException e){
            e.printStackTrace();
        }
    }

  /*  @Override
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
    }*/
}
