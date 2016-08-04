package com.example.shohel.khaler_kontho;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.shohel.khaler_kontho.Adapter.HorizontalAdapter;
import com.example.shohel.khaler_kontho.Adapter.NewslistAdapter;
import com.example.shohel.khaler_kontho.Model.AllNewsInfo;
import com.example.shohel.khaler_kontho.Model.AllnewsResponse;
import com.example.shohel.khaler_kontho.Model.LatestNewsInfo;
import com.example.shohel.khaler_kontho.Model.NewsItem;
import com.example.shohel.khaler_kontho.Utils.AAPBDHttpClient;
import com.example.shohel.khaler_kontho.Utils.AlertMessage;
import com.example.shohel.khaler_kontho.Utils.AppConstant;
import com.example.shohel.khaler_kontho.Utils.BusyDialog;
import com.example.shohel.khaler_kontho.Utils.NetInfo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.Executors;

import it.sephiroth.android.library.widget.HListView;

public class MainActivity extends Activity {

    private ListView lvNewsList;
    Context con;
    final String URL = "http://www.kalerkantho.com/api/homenews";
    NewslistAdapter adapter;
    private HListView dayHListView;
    HorizontalAdapter horizontaladaapter;
    View footerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con = this;
        initUI();
    }

    public void initUI() {
        lvNewsList = (ListView) findViewById(R.id.lvNewsList);
        dayHListView = (HListView) findViewById(R.id.dayHListView);

        footerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer_horizontallist_item, null, false);
        dayHListView = (HListView) footerView.findViewById(R.id.dayHListView);
        // ListView.addFooterView(footerView);

        requestGetNeslist(URL);


    }

    private void requestGetNeslist(final String url) {
        // TODO Auto-generated method stub
        if (!NetInfo.isOnline(con)) {
            AlertMessage.showMessage(con, getString(R.string.app_name), "No Internet!");
            return;
        }
        Log.e("URL : ", url);
        final BusyDialog busyNow = new BusyDialog(con, true, false);
        busyNow.show();
        Executors.newSingleThreadScheduledExecutor().submit(new Runnable() {
            String response = "";

            @Override
            public void run() {


/*

                Map<String,String> param =new HashMap();
                param.put("api_token",PersistData.getStringData(getActivity(), AppConstant.TOKEN));

*/


                try {
                    response = AAPBDHttpClient.get(url).body();
                } catch (Exception e) {
                    // TODO: handle exception
                    Log.e("MYAPP", "exception", e);
                    if (busyNow != null) {
                        busyNow.dismis();
                    }
                }

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        if (busyNow != null) {
                            busyNow.dismis();
                        }
                        try {
                            Log.e("Response", ">>" + new String(response));
                            if (!TextUtils.isEmpty(new String(response))) {
                                Gson g = new Gson();
                                AllnewsResponse newslistresponsee = g.fromJson(new String(response), AllnewsResponse.class);

                                Log.e("Top news size>> ", "" + newslistresponsee.getTop_news().size());
                                Log.e("Latest news size>> ", "" + newslistresponsee.getLatestnews().size());
                                Log.e("Basai news size>> ", "" + newslistresponsee.getBasainews().size());


                                ArrayList<NewsItem> topnewslist = new ArrayList<>();
                                ArrayList<NewsItem> latestnewslist = new ArrayList<>();
                                ArrayList<NewsItem> allnewslist = new ArrayList<>();

                                topnewslist = newslistresponsee.getTop_news();
                                latestnewslist = newslistresponsee.getLatestnews();


                                // add two arraylist into single arraylist (allnewslist)
                                allnewslist.addAll(topnewslist);
                                // increase one position of list for set seperator in adapter
                                allnewslist.add(new NewsItem());
                                allnewslist.addAll(latestnewslist);

                                //
                                AppConstant.allnewsinfo = allnewslist;

                                Log.e("All newslist >>",""+allnewslist.size());

                                // after getting server data and set listview
                                if (newslistresponsee.getTop_news().size() > 0) {
                                    adapter = new NewslistAdapter(con, allnewslist);
                                    lvNewsList.setAdapter(adapter);
                                    horizontaladaapter = new HorizontalAdapter(con, newslistresponsee.getBasainews());
                                    dayHListView.setAdapter(horizontaladaapter);
                                    lvNewsList.addFooterView(footerView);
                                    AppConstant.topnewslist = newslistresponsee.getTop_news();
                                    Log.e("Appconstant size>> ", "" + AppConstant.topnewslist.size());
                                }

                            }


                        } catch (final Exception e) {

                            e.printStackTrace();
                        }


                    }
                });
            }
        });


    }
}
