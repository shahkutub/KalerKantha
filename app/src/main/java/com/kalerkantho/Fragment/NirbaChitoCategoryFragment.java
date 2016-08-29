package com.kalerkantho.Fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aapbd.utils.network.AAPBDHttpClient;
import com.google.gson.Gson;
import com.kalerkantho.Adapter.MyFvRecyAdapterList;
import com.kalerkantho.Model.Category;
import com.kalerkantho.MyDb.MyDBHandler;
import com.kalerkantho.R;
import com.kalerkantho.Utils.AlertMessage;
import com.kalerkantho.Utils.AllURL;
import com.kalerkantho.Utils.DividerItemDecoration;
import com.kalerkantho.Utils.NetInfo;
import com.kalerkantho.holder.AllNirbahito;

import java.util.concurrent.Executors;

/**
 * Created by Ratan on 7/29/2015.
 */
public class NirbaChitoCategoryFragment extends Fragment {
private Context con;
    private ImageView selectBtniv;
    private TextView tvLike,tvTitle1,tvTitle2,startBtn;
    private LinearLayout emptyFv;
    private MyDBHandler db;
    private RecyclerView recFvoList;
    private ProgressBar progressNirbachito;

    private MyFvRecyAdapterList myAdapter;
    Drawable dividerDrawable;
    private LinearLayoutManager myFvLayoutManager;

    private AllNirbahito allnirbahito;
    private String allCategoryID="";
    private int pageNumber =1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nirbachitofragment,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        con = getActivity();
        db = new MyDBHandler(con);

        initU();


    }

    private void initU() {

        final Typeface face_reg = Typeface.createFromAsset(con.getAssets(), "fonts/SolaimanLipi_reg.ttf");
        final Typeface face_bold = Typeface.createFromAsset(con.getAssets(), "fonts/SolaimanLipi_Bold.ttf");

        selectBtniv = (ImageView) getView().findViewById(R.id.selectBtniv);
        progressNirbachito = (ProgressBar) getView().findViewById(R.id.progressNirbachito);
        emptyFv = (LinearLayout) getView().findViewById(R.id.emptyFv);
        tvLike = (TextView) getView().findViewById(R.id.tvLike);
        tvTitle1 = (TextView) getView().findViewById(R.id.tvTitle1);
        tvTitle2 = (TextView) getView().findViewById(R.id.tvTitle2);
        startBtn = (TextView) getView().findViewById(R.id.startBtn);
        recFvoList = (RecyclerView) getView().findViewById(R.id.recFvoList);






        myFvLayoutManager = new LinearLayoutManager(con);
        recFvoList.setLayoutManager(myFvLayoutManager);

        dividerDrawable = ContextCompat.getDrawable(con, R.drawable.divider);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(dividerDrawable);
        recFvoList.addItemDecoration(dividerItemDecoration);


        tvLike.setTypeface(face_bold);
        tvTitle1.setTypeface(face_reg);
        tvTitle2.setTypeface(face_reg);
        startBtn.setTypeface(face_reg);


        for(Category category:db.getCatList())
        {
            allCategoryID+=category.getId()+"+";
        }


        if(allCategoryID.length()>0)
        {
            allCategoryID=allCategoryID.substring(0,allCategoryID.length()-1);
        }


        getNirbachitolist(AllURL.getNirbachitoList(allCategoryID,pageNumber));

    }


/*    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser){
            Handler handler= new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getNirbachitolist(AllURL.getNirbachitoList(allCategoryID,pageNumber));
                }
            },100);
        }
    }*/




    private void getNirbachitolist(final String url) {
        if (!NetInfo.isOnline(con)) {
            AlertMessage.showMessage(con, getString(R.string.app_name), "No Internet!");
            return;
        }
        Log.e("URL : ", url);

        progressNirbachito.setVisibility(View.VISIBLE);
        Executors.newSingleThreadScheduledExecutor().submit(new Runnable() {
            String response = "";

            @Override
            public void run() {

                try {
                    response = AAPBDHttpClient.get(url).body();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        progressNirbachito.setVisibility(View.GONE);

                        try {
                            Log.e("Response", ">>" + new String(response));
                            if (!TextUtils.isEmpty(new String(response))) {
                                Gson g = new Gson();
                                allnirbahito=g.fromJson(new String(response),AllNirbahito.class);

                                if (allnirbahito.getStatus().equalsIgnoreCase("1")) {

                                    if (allnirbahito.getMy_news().size()>0){

                                        recFvoList.setVisibility(View.VISIBLE);
                                        emptyFv.setVisibility(View.GONE);
                                        myAdapter =new MyFvRecyAdapterList(con,allnirbahito.getMy_news(),null);
                                        recFvoList.setAdapter(myAdapter);
                                        myAdapter.notifyDataSetChanged();


                                    }else{
                                        recFvoList.setVisibility(View.GONE);
                                        emptyFv.setVisibility(View.VISIBLE);
                                    }
                                }
                            }

                        } catch (final Exception e) {
                            e.printStackTrace();
                            progressNirbachito.setVisibility(View.GONE);
                        }


                    }
                });
            }
        });


    }


}
