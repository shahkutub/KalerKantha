package com.kalerkantho;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.kalerkantho.Adapter.FavrtRecycleAdapter;
import com.kalerkantho.Model.DetailsModel;
import com.kalerkantho.Model.FvrtModel;
import com.kalerkantho.MyDb.MyDBHandler;
import com.kalerkantho.holder.AllNewsObj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AppBd on 8/30/2016.
 */
public class FavrtActivity extends AppCompatActivity {

    private Context con;
    Drawable dividerDrawable;
    private RecyclerView favList;
    private FavrtRecycleAdapter fAdapter;
    private AllNewsObj allObj;
    private List<DetailsModel> allDetailsList = new ArrayList<DetailsModel>();
    private MyDBHandler db;
    private LinearLayout backFavBtn;
//
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_design);
        con = this;

        db = new MyDBHandler(con);
        //intiU();
        favList = (RecyclerView)findViewById(R.id.favrtList);
        backFavBtn = (LinearLayout) findViewById(R.id.backFavBtn);

        backFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        for(FvrtModel fm: db.getAllFvrtModels()){
            Log.e("Details",fm.getFvrtObject());
            Gson g = new Gson();
            DetailsModel dm = g.fromJson(new String(fm.getFvrtObject()),DetailsModel.class);
            allDetailsList.add(dm);
        }

        favList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(con,2);
        favList.setLayoutManager(layoutManager);

        fAdapter  = new FavrtRecycleAdapter(FavrtActivity.this,allDetailsList,null);
        favList.setAdapter(fAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}
