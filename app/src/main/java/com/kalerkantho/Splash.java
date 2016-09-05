package com.kalerkantho;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.aapbd.utils.geolocation.GPSTracker;
import com.aapbd.utils.network.AAPBDHttpClient;
import com.aapbd.utils.storage.PersistData;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.gson.Gson;
import com.kalerkantho.Gcm.FirebaseIDService;
import com.kalerkantho.Utils.AllURL;
import com.kalerkantho.Utils.AppConstant;
import com.kalerkantho.Utils.NetInfo;
import com.kalerkantho.holder.AllCategory;

import java.util.concurrent.Executors;

public class Splash extends Activity {

    Context con;
    Handler handler = new Handler();
    private final int SPLASH_DISPLAY_LENGTH = 1500;
    //private ProgressBar progressShow;




    GPSTracker gps;
    //String SENDER_ID = "257395124016";
    //GoogleCloudMessaging gcm;
    String regId="",msg="",response_menu="";
    static final String TAG = "GCM_CHECK";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;


    private long millis;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash);
        con=this;

        if(TextUtils.isEmpty(PersistData.getStringData(con,AppConstant.GCMID)))
        {
            PersistData.setStringData(con,AppConstant.GCMID,"1234567890");
        }

       Intent intent=new Intent(con,FirebaseIDService.class);
        startService(intent);


        millis = System.currentTimeMillis();
        //millisOday = TimeUnit.DAYS.toMillis(1);


        //progressShow = (ProgressBar) findViewById(R.id.progressSplash);


//        if (checkPlayServices()) {
//            gcm = GoogleCloudMessaging.getInstance(con);
//            Log.e("gcmId", ":" + PersistData.getStringData(con, AppConstant.GCMID));
//            if (PersistData.getStringData(con, AppConstant.GCMID).length() == 0) {
//                new RegisterBackground().execute();
//            }
//        }


        if (!NetInfo.isOnline(con)) {
            //AlertMessage.showMessage(con, getString(R.string.app_name), "No Internet!");
            //return;

            handler.postDelayed(new Runnable(){
                @Override
                public void run() {
                    Intent mainIntent = new Intent(con,MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }, SPLASH_DISPLAY_LENGTH);



        }else{

            if((PersistData.getLongData(con, AppConstant.SystemTime)+(24*60*60*1000))>millis)
            {
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        Intent mainIntent = new Intent(con,MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                }, SPLASH_DISPLAY_LENGTH);

            }else
            {

                PersistData.setLongData(con,AppConstant.SystemTime,millis);

                getMenuInfo(AllURL.getMenuList());
            }

        }
    }

//    class RegisterBackground extends AsyncTask<String, String, String> {
//        @Override
//        protected String doInBackground(String... arg0) {
//
//            try {
//                if (gcm == null) {
//                    gcm = GoogleCloudMessaging.getInstance(con);
//                }
//                regId = gcm.register(SENDER_ID);
//                PersistData.setStringData(con, AppConstant.GCMID, regId);
//                msg = "Dvice registered, registration ID=" + regId;
//                Log.e("Google Registration ID", "---------" + msg);
//
//            } catch (final IOException ex) {
//                msg = "Error :" + ex.getMessage();
//            }
//            return msg;
//        }
//
//        @Override
//        protected void onPostExecute(String msg) {
//
//        }
//    }

    private boolean checkPlayServices() {
        final int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, Splash.PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(Splash.TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    private void getMenuInfo(final String url) {


        if (!NetInfo.isOnline(con)) {
            // AlertMessage.showMessage(con, getString(R.string.app_name), "No Internet!");
            //return;
            Handler handler= new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent mainIntent = new Intent(con,MainActivity.class);
                    startActivity(mainIntent);
                    finish();

                }
            },100);


        }

     //  progressShow.setVisibility(View.VISIBLE);
        Executors.newSingleThreadScheduledExecutor().submit(new Runnable() {


            @Override
            public void run() {

                try {
                    response_menu = AAPBDHttpClient.get(url).body();
                } catch (Exception e) {
                    e.printStackTrace();
                }

               runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                       // progressShow.setVisibility(View.GONE);

                        try {
                            Log.e("Response", ">>" + new String(response_menu));
                            if (!TextUtils.isEmpty(new String(response_menu))) {

                                Gson g = new Gson();
                                AllCategory allCategory=g.fromJson(new String(response_menu),AllCategory.class);
                                PersistData.setLongData(con, AppConstant.SystemTime, millis);

                                if(allCategory.getStatus()==1){
                                    PersistData.setStringData(getApplicationContext(),AppConstant.CATEGORY_RESPONSE,response_menu);
                                    Intent mainIntent = new Intent(con,MainActivity.class);
                                    startActivity(mainIntent);
                                    finish();

                                }
                            }

                        } catch (final Exception e) {
                            e.printStackTrace();
                            //progressShow.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });


    }
}