package com.example.shohel.khaler_kontho;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.shohel.khaler_kontho.Adapter.NewslistAdapter;
import com.example.shohel.khaler_kontho.Model.All_Cat_News_Obj;
import com.example.shohel.khaler_kontho.Model.CommonNewsItem;

import com.example.shohel.khaler_kontho.Utils.AAPBDHttpClient;
import com.example.shohel.khaler_kontho.Utils.AlertMessage;
import com.example.shohel.khaler_kontho.Utils.AppConstant;
import com.example.shohel.khaler_kontho.Utils.BusyDialog;
import com.example.shohel.khaler_kontho.Utils.NetInfo;
import com.example.shohel.khaler_kontho.holder.AllCommonNewsItem;
import com.example.shohel.khaler_kontho.holder.AllNewsObj;
import com.google.gson.Gson;
import com.kalerkantho.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;


public class HomeFragment extends Fragment {

    private ListView lvNewsList;
    Context con;
    final String URL = "http://www.kalerkantho.com/api/homenews";
    NewslistAdapter adapter;
    private List<AllCommonNewsItem> allCommonNewsItem=new ArrayList<AllCommonNewsItem>();

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
                               // AllnewsResponse newslistresponsee = g.fromJson(new String(response), AllnewsResponse.class);

                              //  Log.e("getData()", ">>" + getActivity().getResources().getString(R.string.a));

                                //String jsonString=getActivity().getResources().getString(R.string.a).toString();
                                String jsonString=loadAssetTextAsString(getContext(),"abc.txt");


                                AllNewsObj allObj=g.fromJson(jsonString,AllNewsObj.class);


                              //  allCommonNewsItem  =new ArrayList<>();
                                allCommonNewsItem.clear();

                                int i=0;
                                // for top news and below list
                                for(CommonNewsItem topNews:allObj.getTop_news())
                                {
                                    if(i==0)
                                    {
                                        AllCommonNewsItem singleObj=new AllCommonNewsItem();
                                        singleObj.setType("fullscreen");
                                        singleObj.setNews_obj(topNews);
                                        allCommonNewsItem.add(singleObj);


                                    }else
                                    {
                                        AllCommonNewsItem singleObj=new AllCommonNewsItem();
                                        singleObj.setType("defaultscreen");
                                        singleObj.setNews_obj(topNews);
                                        allCommonNewsItem.add(singleObj);
                                    }
                                    i++;
                                }

                                // for bibid row
                                AllCommonNewsItem titleObj=new AllCommonNewsItem();
                                titleObj.setType("titleshow");
                                titleObj.setCategory_title(getActivity().getResources().getString(R.string.bibid));
                                allCommonNewsItem.add(titleObj);

                                // for bibid row
                                AllCommonNewsItem hListObj=new AllCommonNewsItem();
                                hListObj.setType("horizontal");
                                hListObj.setList_news_obj(allObj.getBlueslide());
                                allCommonNewsItem.add(hListObj);

                                // All category object need to add

                                for(All_Cat_News_Obj allCat:allObj.getAll_cat_news())
                                {
                                    // for bibid row
                                    AllCommonNewsItem a1=new AllCommonNewsItem();
                                    a1.setType("titleshow");
                                    a1.setCategory_title(allCat.getCategory_name());
                                    a1.setCategory_id(allCat.getCategory_id());
                                    allCommonNewsItem.add(a1);

                                    for(CommonNewsItem cn:allCat.getNews())
                                    {
                                        AllCommonNewsItem a2=new AllCommonNewsItem();
                                        a2.setType("defaultscreen");
                                        a2.setNews_obj(cn);
                                        allCommonNewsItem.add(a2);
                                    }
                                }

                                // for bhinow nes
                                AllCommonNewsItem b3Obj=new AllCommonNewsItem();
                                b3Obj.setType("titleshow");
                                b3Obj.setCategory_title(getActivity().getResources().getString(R.string.others_news));
                                allCommonNewsItem.add(b3Obj);

                                // for bibid row
                                AllCommonNewsItem redObj=new AllCommonNewsItem();
                                redObj.setType("horizontal");
                                redObj.setList_news_obj(allObj.getRedslider());
                                allCommonNewsItem.add(redObj);


                                Log.e("allCommonNewsItem.size", ""+ allCommonNewsItem.size());

                                // after getting server data and set listview
                                if (allCommonNewsItem.size() > 0) {
                                    adapter = new NewslistAdapter(con, allCommonNewsItem);
                                    lvNewsList.setAdapter(adapter);
                                   lvNewsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                       @Override
                                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                           //Toast.makeText(con,""+position,Toast.LENGTH_LONG).show();
                                           //Log.e("Item position: >>",""+position);
                                       }
                                   });
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

    public String getData()
    {
        try {
            InputStream is = getActivity().getAssets().open("abc.txt");

            // We guarantee that the available method returns the total
            // size of the asset...  of course, this does mean that a single
            // asset can't be more than 2 gigs.
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            String text = new String(buffer);

            // Finally stick the string into the text view.
            // Replace with whatever you need to have the text into.

          return  text;

        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);

        }
    }

    private String loadAssetTextAsString(Context context, String name) {
        BufferedReader in = null;
        try {
            StringBuilder buf = new StringBuilder();
            InputStream is = context.getAssets().open(name);
            in = new BufferedReader(new InputStreamReader(is));

            String str;
            boolean isFirst = true;
            while ( (str = in.readLine()) != null ) {
                if (isFirst)
                    isFirst = false;
                else
                    buf.append('\n');
                buf.append(str);
            }
            return buf.toString();
        } catch (IOException e) {
            Log.e("fff", "Error opening asset " + name);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e("fff", "Error closing asset " + name);
                }
            }
        }

        return null;
    }
}
