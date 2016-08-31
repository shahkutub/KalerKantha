package com.kalerkantho;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aapbd.utils.network.AAPBDHttpClient;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.kalerkantho.Model.DetailsModel;
import com.kalerkantho.Model.FvrtModel;
import com.kalerkantho.MyDb.MyDBHandler;
import com.kalerkantho.Utils.AlertMessage;
import com.kalerkantho.Utils.AllURL;
import com.kalerkantho.Utils.NetInfo;

import java.util.concurrent.Executors;

/**
 * Created by AppBd on 8/29/2016.
 */
public class DetailsActivity extends AppCompatActivity {
    private Context con;
    private TextView headingTxt, txt_positive_like, txt_negative_like, txt_comment, txtDate, txtCategory, detailsTxt;
    private ImageView backImgMain, fvImg, backBtn;
    private String content_id = "", isFvrtString = "";
    private ProgressBar progressShow;
    private MyDBHandler db;
    private FvrtModel fm = new FvrtModel();
    private DetailsModel allDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_view);
        con = this;
        headingTxt = (TextView) findViewById(R.id.headingTxt);
        txt_positive_like = (TextView) findViewById(R.id.txt_positive_like);
        txt_negative_like = (TextView) findViewById(R.id.txt_negative_like);
        txt_comment = (TextView) findViewById(R.id.txt_comment);
        txtDate = (TextView) findViewById(R.id.dateTv);
        txtCategory = (TextView) findViewById(R.id.catagoryTv);
        detailsTxt = (TextView) findViewById(R.id.detailsText);
        backImgMain = (ImageView) findViewById(R.id.mainBackground);
        fvImg = (ImageView) findViewById(R.id.favrtBtn);
        backBtn = (ImageView) findViewById(R.id.backBtn);
        progressShow = (ProgressBar) findViewById(R.id.progressShow);

        content_id = getIntent().getExtras().getString("content_id");
        isFvrtString = getIntent().getExtras().getString("is_favrt");
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        db = new MyDBHandler(con);

        // call url for detals
        try {
            if (!content_id.isEmpty()) {
                if (isFvrtString.equalsIgnoreCase("1")) {
                    Log.e("fff", "favrt");

                    final Typeface face_reg = Typeface.createFromAsset(con.getAssets(), "fonts/SolaimanLipi_reg.ttf");
                    final Typeface face_bold = Typeface.createFromAsset(con.getAssets(), "fonts/SolaimanLipi_Bold.ttf");
                    String response = "";

                    for (FvrtModel fm : db.getAllFvrtModels()) {
                        if (content_id.equalsIgnoreCase(fm.getFvrtId())) {
                            response = fm.getFvrtObject();
                            break;
                        }
                    }


                    Gson g = new Gson();
                    allDetail = g.fromJson(new String(response), DetailsModel.class);

                    if (allDetail != null) {
                        headingTxt.setText(allDetail.getNews().getTitle());
                        detailsTxt.setText(Html.fromHtml(allDetail.getNews().getDetails()));
                        Glide.with(con).load(allDetail.getNews().getImage()).placeholder(R.drawable.fullscreen).into(backImgMain);

                        if (allDetail.getIs_liked().equalsIgnoreCase("true") && !allDetail.getLike_count().isEmpty()) {
                            txt_positive_like.setText("("+allDetail.getLike_count()+")");
                        }
                        else{
                            txt_positive_like.setText("("+0+")");
                        }
                        if (allDetail.getIs_disliked().equalsIgnoreCase("true") && !allDetail.getDislike_count().isEmpty()) {
                            txt_negative_like.setText("("+allDetail.getDislike_count()+")");
                        }
                        else{
                            txt_negative_like.setText("("+0+")");
                        }
                        if (!allDetail.getComments_count().equalsIgnoreCase("0"))
                            txt_comment.setText("("+allDetail.getComments_count()+")");
                        else{
                            txt_comment.setText("("+0+")");
                        }
                        txtDate.setText(allDetail.getNews().getBanglaDateString() + "  | ");
                        txtCategory.setText(allDetail.getNews().getCategory_name());

                        headingTxt.setTypeface(face_bold);
                        detailsTxt.setTypeface(face_reg);
                        txt_positive_like.setTypeface(face_reg);
                        txt_negative_like.setTypeface(face_reg);
                        txt_comment.setTypeface(face_reg);
                        txtDate.setTypeface(face_reg);
                        txtCategory.setTypeface(face_reg);
                    }

                } else {
                    Log.e("id details:", content_id);
                    requestGetNeslist(AllURL.getDetails(content_id, ""));
                }
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        fvImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isFvrt = false;
                for (FvrtModel fm : db.getAllFvrtModels()) {
                    if (content_id.equalsIgnoreCase(fm.getFvrtId())) {
                        Log.e("get compared:", "true");
                        isFvrt = true;
                        break;
                    }
                }


                if (!isFvrt) { // not added before

                    //isFvrt = true;
                    //   AppConstant.mAdapter.notifyDataSetChanged();
                    fm.setFvrtId(content_id);
                    Gson gson = new Gson();
                    String favObject = gson.toJson(allDetail);
                    fm.setFvrtId(content_id);
                    fm.setFvrtObject(favObject);
                    db.addFavrtEntry(fm);
                    Toast.makeText(con, "Data Added Successfully", Toast.LENGTH_SHORT);

                } else {
                    // isFvrt = false;

                    db.removeSingleFavENtry(content_id);
                }
                //isFvrt = false;
                Log.e("All Frvt Size:", ">>" + db.getAllFvrtModels().size());
            }
        });

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
                            final Typeface face_reg = Typeface.createFromAsset(con.getAssets(), "fonts/SolaimanLipi_reg.ttf");
                            final Typeface face_bold = Typeface.createFromAsset(con.getAssets(), "fonts/SolaimanLipi_Bold.ttf");
                            Log.e("details response:", ">>" + new String(response));
                            if (!TextUtils.isEmpty(new String(response))) {
                                Gson g = new Gson();
                                allDetail = g.fromJson(new String(response), DetailsModel.class);

                                headingTxt.setText(allDetail.getNews().getTitle());
                                detailsTxt.setText(Html.fromHtml(allDetail.getNews().getDetails()));
                                //new OpenTextUrl(con).setTextViewHTMLChat(detailsTxt, allDetail.getNews().getDetails());
                                Glide.with(con).load(allDetail.getNews().getImage()).placeholder(R.drawable.fullscreen).into(backImgMain);

                                if (allDetail.getIs_liked().equalsIgnoreCase("true") && !allDetail.getLike_count().isEmpty()) {
                                    txt_positive_like.setText("("+allDetail.getLike_count()+")");
                                }
                                else{
                                    txt_positive_like.setText("("+0+")");
                                }
                                if (allDetail.getIs_disliked().equalsIgnoreCase("true") && !allDetail.getDislike_count().isEmpty()) {
                                    txt_negative_like.setText("("+allDetail.getDislike_count()+")");
                                }
                                else{
                                    txt_negative_like.setText("("+0+")");
                                }
                                if (!allDetail.getComments_count().equalsIgnoreCase("0"))
                                    txt_comment.setText("("+allDetail.getComments_count()+")");
                                else{
                                    txt_comment.setText("("+0+")");
                                }
                                txtDate.setText(allDetail.getNews().getBanglaDateString() + "  | ");
                                txtCategory.setText(allDetail.getNews().getCategory_name());

                                headingTxt.setTypeface(face_bold);
                                detailsTxt.setTypeface(face_reg);
                                txt_positive_like.setTypeface(face_reg);
                                txt_negative_like.setTypeface(face_reg);
                                txt_comment.setTypeface(face_reg);
                                txtDate.setTypeface(face_reg);
                                txtCategory.setTypeface(face_reg);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
