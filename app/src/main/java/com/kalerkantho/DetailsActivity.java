package com.kalerkantho;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aapbd.utils.network.AAPBDHttpClient;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kalerkantho.Model.DetailsModel;
import com.kalerkantho.Utils.AlertMessage;
import com.kalerkantho.Utils.AllURL;
import com.kalerkantho.Utils.NetInfo;

import java.util.concurrent.Executors;

/**
 * Created by AppBd on 8/29/2016.
 */
public class DetailsActivity extends AppCompatActivity {
    private Context con;
    private TextView headingTxt,txt_positive_like,txt_negative_like,txt_comment,txtDate,txtCategory,detailsTxt;
   private  ImageView backImgMain,fvImg,backBtn;
    private String content_id = "";
    private ProgressBar progressShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_view);
        con = this;
        headingTxt = (TextView)findViewById(R.id.headingTxt);
        txt_positive_like = (TextView)findViewById(R.id.txt_positive_like);
        txt_negative_like = (TextView)findViewById(R.id.txt_negative_like);
        txt_comment = (TextView)findViewById(R.id.txt_comment);
        txtDate = (TextView)findViewById(R.id.dateTv);
        txtCategory = (TextView)findViewById(R.id.catagoryTv);
        detailsTxt = (TextView)findViewById(R.id.detailsText);
        backImgMain = (ImageView)findViewById(R.id.mainBackground);
        fvImg = (ImageView)findViewById(R.id.favrtBtn);
        backBtn = (ImageView)findViewById(R.id.backBtn);
        progressShow = (ProgressBar)findViewById(R.id.progressShow);

        content_id = getIntent().getExtras().getString("content_id");

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // call url for detals
        if(!content_id.isEmpty()){
            Log.e("id details:",content_id);
            requestGetNeslist(AllURL.getDetails(content_id,""));
        }

    }


    private void requestGetNeslist(final String url) {
        if (!NetInfo.isOnline(con)) {
            AlertMessage.showMessage(con, getString(R.string.app_name), "No Internet!");
            return;
        }
        Log.e("URL : ", url);

        progressShow.setVisibility(View.VISIBLE);
        Executors.newSingleThreadScheduledExecutor().submit(new Runnable() {
            String response = "";

            @Override
            public void run() {

                try {
                    response = AAPBDHttpClient.get(url).body();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        progressShow.setVisibility(View.GONE);

                        try {
                            Log.e("details response:", ">>" + new String(response));
                            if (!TextUtils.isEmpty(new String(response))) {
                                Gson g = new Gson();
                                DetailsModel allDetail=g.fromJson(new String(response),DetailsModel.class);

                                headingTxt.setText(allDetail.getNews().getTitle());
                                detailsTxt.setText(allDetail.getNews().getDetails());
                                Glide.with(con).load(allDetail.getNews().getImage()).placeholder(R.drawable.fullscreen).into(backImgMain);

                                if(allDetail.getIs_liked().equalsIgnoreCase("true") && !allDetail.getLike_count().isEmpty()){
                                    txt_positive_like.setText(allDetail.getLike_count());
                                }
                                if(allDetail.getIs_disliked().equalsIgnoreCase("true") && !allDetail.getDislike_count().isEmpty()){
                                    txt_positive_like.setText(allDetail.getDislike_count());
                                }
                                if(!allDetail.getComments_count().equalsIgnoreCase("0"))
                                   txt_comment.setText(allDetail.getComments_count());
                               txtDate.setText(allDetail.getNews().getDatetime()+"  | ");
                                txtCategory.setText(allDetail.getNews().getCategory_name());
                            }

                        } catch (final Exception e) {
                            e.printStackTrace();
                            progressShow.setVisibility(View.GONE);
                        }


                    }
                });
            }
        });

//
    }
}
