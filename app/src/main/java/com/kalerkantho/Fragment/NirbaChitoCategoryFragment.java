package com.kalerkantho.Fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kalerkantho.Adapter.MyFvRecyAdapter;
import com.kalerkantho.Adapter.MyFvRecyAdapterList;
import com.kalerkantho.MyDb.MyDBHandler;
import com.kalerkantho.R;
import com.kalerkantho.Utils.AllURL;
import com.kalerkantho.Utils.DividerItemDecoration;

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

    private MyFvRecyAdapterList myAdapter;
    Drawable dividerDrawable;
    private LinearLayoutManager myFvLayoutManager;

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
        emptyFv = (LinearLayout) getView().findViewById(R.id.emptyFv);
        tvLike = (TextView) getView().findViewById(R.id.tvLike);
        tvTitle1 = (TextView) getView().findViewById(R.id.tvTitle1);
        tvTitle2 = (TextView) getView().findViewById(R.id.tvTitle2);
        startBtn = (TextView) getView().findViewById(R.id.startBtn);
        recFvoList = (RecyclerView) getView().findViewById(R.id.recFvoList);

        myFvLayoutManager = new LinearLayoutManager(con);
        recFvoList.setLayoutManager(myFvLayoutManager);

        dividerDrawable = ContextCompat.getDrawable(con, R.drawable.lineee);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(dividerDrawable);
        recFvoList.addItemDecoration(dividerItemDecoration);


        tvLike.setTypeface(face_bold);
        tvTitle1.setTypeface(face_reg);
        tvTitle2.setTypeface(face_reg);
        startBtn.setTypeface(face_reg);

        if(db.getCatList().size()>0){

            recFvoList.setVisibility(View.VISIBLE);
            emptyFv.setVisibility(View.GONE);
            myAdapter =new MyFvRecyAdapterList(con,null);
            recFvoList.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();

        }else{
            recFvoList.setVisibility(View.GONE);
            emptyFv.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser){
            Handler handler= new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    initU();

                }
            },100);
        }
    }
}
