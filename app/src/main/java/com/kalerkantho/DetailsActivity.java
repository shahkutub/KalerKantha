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
import com.aapbd.utils.storage.PersistData;
import com.aapbd.utils.storage.PersistentUser;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.kalerkantho.Model.DetailsModel;
import com.kalerkantho.Model.FvrtModel;
import com.kalerkantho.MyDb.MyDBHandler;
import com.kalerkantho.Utils.AlertMessage;
import com.kalerkantho.Utils.AllURL;
import com.kalerkantho.Utils.AppConstant;
import com.kalerkantho.Utils.NetInfo;
import com.kalerkantho.holder.AllCommonResponse;

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
    private ImageView positive_like, dislikeBtn;
    private AllCommonResponse allCommonResponse;
    Typeface face_reg,face_bold;

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

        positive_like = (ImageView) findViewById(R.id.positive_like);
        dislikeBtn = (ImageView) findViewById(R.id.dislikeBtn);

        content_id = getIntent().getExtras().getString("content_id");
        isFvrtString = getIntent().getExtras().getString("is_favrt");

         face_reg = Typeface.createFromAsset(con.getAssets(), "fonts/SolaimanLipi_reg.ttf");
         face_bold = Typeface.createFromAsset(con.getAssets(), "fonts/SolaimanLipi_Bold.ttf");


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
                        setAllData();
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


        positive_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dislike="0",like="0";

                if(allDetail.getIs_disliked().equalsIgnoreCase("TRUE"))
                {
                    dislike="1";
                }else
                {
                    dislike="0";
                }

                if(allDetail.getIs_liked().equalsIgnoreCase("TRUE"))
                {
                    like="0";
                }else
                {
                    like="1";
                }


                setLikeDislike(AllURL.getLikeDislike(PersistentUser.getUserID(con), allDetail.getNews().getId(), like, dislike));


            }
        });


        dislikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dislike="0",like="0";

                if(allDetail.getIs_disliked().equalsIgnoreCase("TRUE"))
                {
                    dislike="0";
                }else
                {
                    dislike="1";
                }

                if(allDetail.getIs_liked().equalsIgnoreCase("TRUE"))
                {
                    like="1";
                }else
                {
                    like="0";
                }




                setDislikeLike(AllURL.getLikeDislike(PersistentUser.getUserID(con), allDetail.getNews().getId(), like, dislike));


            }
        });


    }


    private void setAllData() {
        headingTxt.setText(allDetail.getNews().getTitle());
        detailsTxt.setText(Html.fromHtml(allDetail.getNews().getDetails()));
        Glide.with(con).load(allDetail.getNews().getImage()).placeholder(R.drawable.fullscreen).into(backImgMain);


        if (!(TextUtils.isEmpty(allDetail.getLike_count()))) {
            txt_positive_like.setText("(" + allDetail.getLike_count() + ")");
        } else {
            txt_positive_like.setText("(" + 0 + ")");
        }
        if (!(TextUtils.isEmpty(allDetail.getIs_disliked()))) {
            txt_negative_like.setText("(" + allDetail.getDislike_count() + ")");
        } else {
            txt_negative_like.setText("(" + 0 + ")");
        }

        if (!(TextUtils.isEmpty(allDetail.getComments_count())))
            txt_comment.setText("(" + allDetail.getComments_count() + ")");
        else {
            txt_comment.setText("(" + 0 + ")");
        }


        if (allDetail.getIs_liked().equalsIgnoreCase("FALSE")) {

            positive_like.setImageResource(R.drawable.negative_like);

        } else {

            positive_like.setImageResource(R.drawable.positive_like);
        }


        if (allDetail.getIs_disliked().equalsIgnoreCase("FALSE")) {
            dislikeBtn.setImageResource(R.drawable.negative_like);
        } else {

            dislikeBtn.setImageResource(R.drawable.positive_like);
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

                                setAllData();
                            }

                        } catch (final Exception e) {
                            e.printStackTrace();
                            progressShow.setVisibility(View.GONE);
                        }


                    }
                });
            }
        });
    }


    private void setLikeDislike(final String url) {
        if (!NetInfo.isOnline(con)) {
            AlertMessage.showMessage(con, getString(R.string.app_name), "No Internet!");
            return;
        }
        Log.e("URL L_D: ", url);

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
                            Log.e("details response:", ">>" + new String(response));
                            if (!TextUtils.isEmpty(new String(response))) {
                                Gson g = new Gson();
                                allCommonResponse = g.fromJson(new String(response), AllCommonResponse.class);


                                if ((allCommonResponse.getMsg().equalsIgnoreCase("Successful")) && (allDetail.getIs_liked().equalsIgnoreCase("FALSE"))) {

                                    int current_likecount = Integer.parseInt(allDetail.getLike_count());
                                    current_likecount = current_likecount + 1;
                                    allDetail.setLike_count("" + current_likecount);
                                    allDetail.setIs_liked("TRUE");
                                    setAllData();
                                }else {

                                    int current_likecount = Integer.parseInt(allDetail.getLike_count());
                                    current_likecount = current_likecount - 1;
                                    allDetail.setLike_count("" + current_likecount);
                                    allDetail.setIs_liked("FALSE");
                                    setAllData();

                                }


                         /*       if ((allCommonResponse.getMsg().equalsIgnoreCase("Successful")) && (allDetail.getIs_disliked().equalsIgnoreCase("FALSE"))&& (clickType.equalsIgnoreCase("dislike"))) {

                                    int current_dislikCount = Integer.parseInt(allDetail.getDislike_count());
                                    current_dislikCount = current_dislikCount + 1;
                                    allDetail.setIs_disliked("" + current_dislikCount);
                                    allDetail.setIs_disliked("TRUE");
                                    setAllData();
                                }else {

                                    int current_dislikCount = Integer.parseInt(allDetail.getDislike_count());
                                    current_dislikCount = current_dislikCount - 1;
                                    allDetail.setIs_disliked("" + current_dislikCount);
                                    allDetail.setIs_disliked("FALSE");
                                    setAllData();

                                }*/





                            }

                        } catch (final Exception e) {
                            e.printStackTrace();
                            progressShow.setVisibility(View.GONE);
                        }


                    }
                });
            }
        });
    }




    private void setDislikeLike(final String url) {
        if (!NetInfo.isOnline(con)) {
            AlertMessage.showMessage(con, getString(R.string.app_name), "No Internet!");
            return;
        }
        Log.e("URL L_D: ", url);

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
                            Log.e("details response:", ">>" + new String(response));
                            if (!TextUtils.isEmpty(new String(response))) {
                                Gson g = new Gson();
                                allCommonResponse = g.fromJson(new String(response), AllCommonResponse.class);




                         if ((allCommonResponse.getMsg().equalsIgnoreCase("Successful")) && (allDetail.getIs_disliked().equalsIgnoreCase("FALSE"))) {

                                    int current_dislikCount = Integer.parseInt(allDetail.getDislike_count());
                                    current_dislikCount = current_dislikCount + 1;
                                    allDetail.setDislike_count("" + current_dislikCount);
                                    allDetail.setIs_disliked("TRUE");
                                    setAllData();

                                }else {

                                    int current_dislikCount = Integer.parseInt(allDetail.getDislike_count());
                                    current_dislikCount = current_dislikCount - 1;
                                    allDetail.setDislike_count("" + current_dislikCount);
                                    allDetail.setIs_disliked("FALSE");
                                    setAllData();

                                }


                            }

                        } catch (final Exception e) {
                            e.printStackTrace();
                            progressShow.setVisibility(View.GONE);
                        }


                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
