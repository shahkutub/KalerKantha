package com.example.shohel.khaler_kontho;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.shohel.khaler_kontho.Adapter.HorizontalAdapter;
import com.example.shohel.khaler_kontho.Adapter.NewslistAdapter;
import com.example.shohel.khaler_kontho.Model.AllNewsInfo;
import com.example.shohel.khaler_kontho.Model.AllnewsResponse;
import com.example.shohel.khaler_kontho.Model.LatestNewsInfo;
import com.example.shohel.khaler_kontho.Model.NewsItem;
import com.example.shohel.khaler_kontho.Model.SpecialNewsInfo;
import com.example.shohel.khaler_kontho.Utils.AAPBDHttpClient;
import com.example.shohel.khaler_kontho.Utils.AlertMessage;
import com.example.shohel.khaler_kontho.Utils.AppConstant;
import com.example.shohel.khaler_kontho.Utils.BusyDialog;
import com.example.shohel.khaler_kontho.Utils.NetInfo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.Executors;

import it.sephiroth.android.library.widget.HListView;

public class HomeFragment extends Fragment {

    private ListView lvNewsList;
    Context con;
    final String URL = "http://www.kalerkantho.com/api/homenews";
    NewslistAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_screen,null);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        con = getActivity();
        initUI();
    }

    public void initUI() {
        lvNewsList = (ListView)getView().findViewById(R.id.lvNewsList);
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

                getActivity().runOnUiThread(new Runnable() {

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



                                ArrayList<NewsItem> topnewslist = new ArrayList<>();
                                ArrayList<NewsItem> latestnewslist = new ArrayList<>();
                                ArrayList<NewsItem> specialnewslist = new ArrayList<>();
                                ArrayList<NewsItem> allnewslist = new ArrayList<>();

                                topnewslist = newslistresponsee.getTop_news();
                                latestnewslist = newslistresponsee.getLatestnews();
                                specialnewslist= newslistresponsee.getSpecialnews();


                                Log.e("Top news size>> ", "" + topnewslist.size());
                                Log.e("Latest news size>> ", "" + latestnewslist.size());
                                Log.e("Basai news size>> ", "" + newslistresponsee.getBasainews().size());
                                Log.e("special news size>> ", "" + specialnewslist.size());


                                // add two arraylist into single arraylist (allnewslist)
                                allnewslist.addAll(topnewslist);
                                // increase one position of list for set seperator in adapter (latest news seperator
                                allnewslist.add(new NewsItem());
                                allnewslist.addAll(latestnewslist);
                                // bibid news seperator
                                allnewslist.add(new NewsItem());
                                //special news seperator
                               // allnewslist.add(new NewsItem());
                                allnewslist.addAll(specialnewslist);
                                //others news seperator
                                allnewslist.add(new NewsItem());

                                //
                                AppConstant.basainewslist=newslistresponsee.getBasainews();
                                AppConstant.selectednews=newslistresponsee.getSelectednews();
                                AppConstant.allnewsinfo = allnewslist;

                                Log.e("All newslist >>",""+allnewslist.size());

                                // after getting server data and set listview
                                if (newslistresponsee.getTop_news().size() > 0) {
                                    adapter = new NewslistAdapter(con, allnewslist);
                                    lvNewsList.setAdapter(adapter);
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
