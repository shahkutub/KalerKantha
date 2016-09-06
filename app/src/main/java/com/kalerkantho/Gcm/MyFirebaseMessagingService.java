package com.kalerkantho.Gcm;

import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.kalerkantho.DetailsActivity;
import com.kalerkantho.MainActivity;
import com.kalerkantho.R;

import java.util.Map;

/**
 * Created by wlaptop on 9/3/2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";

    PowerManager pm;
    PowerManager.WakeLock wl;
    KeyguardManager km;
    private Context con;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.

        con=getApplicationContext();
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.

        //Log.e(TAG, "data: " + remoteMessage.getData());

//
//        for(Map m:remoteMessage.getData())
//        {
//
//        }

        //Toast.makeText(getApplicationContext(),remoteMessage.getNotification().getTitle()+">>"+remoteMessage.getData(),Toast.LENGTH_SHORT).show();

        //Log.e(TAG, "From: " + remoteMessage.getFrom());
        //Log.e(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

       // CustomNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody(),"Test");

        //sendNotification( remoteMessage.getNotification().getBody());

       // sendNotification(remoteMessage.getData().get("title"),remoteMessage.getData().get("body"));



        //Toast.makeText(con,remoteMessage.getData().get("body"),Toast.LENGTH_SHORT).show();

//        Intent intent = new Intent(con, DetailsActivity.class);
//        // Send data to NotificationView Class
//        intent.putExtra("content_id", "");
//        intent.putExtra("is_favrt", "0");
        sendNotification(remoteMessage.getData().get("body"));
        updateMyActivity(con,remoteMessage.getData().get("body"));


       // CustomNotification(remoteMessage.getData().get("title"),remoteMessage.getData().get("body"),"Test");
    }


    private void sendNotification(String messageTitle,String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0 /* request code */, intent,PendingIntent.FLAG_UPDATE_CURRENT);

        long[] pattern = {500,500,500,500,500};

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setVibrate(pattern)
                .setLights(Color.BLUE,1,1)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
    // Put the GCM message into a notification and post it.
    public void sendNotification(String msg) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.testicon)
                .setContentTitle("FCM Message")
                .setContentText(msg)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());

    }

    static void updateMyActivity(Context context, String message) {
       // PersistData.setBooleanData(context, AppManager.PENDINGSTATUS,true);
        Intent intent = new Intent("unique_name");
        //put whatever data you want to send, if a
        // ny
        intent.putExtra("message", message);
        //send broadcast
        context.sendBroadcast(intent);
    }




    public void CustomNotification(final String title,final String body, String message) {
        // Using RemoteViews to bind custom layouts into Notification


        RemoteViews remoteViews = new RemoteViews(getPackageName(),
                R.layout.breaking_news);

        // Set Notification Title
        String strtitle = title;
        // Set Notification Text
        String strtext = body;

        // Open NotificationView Class on Notification Click
        Intent intent = new Intent(this, DetailsActivity.class);
        // Send data to NotificationView Class
        intent.putExtra("content_id", "");
        intent.putExtra("is_favrt", "0");
        // Open NotificationView.java Activity
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                // Set Icon
                .setSmallIcon(R.drawable.ic_launcher)
                // Set Ticker Message
                .setTicker(title)
                // Dismiss Notification
                .setAutoCancel(true)
                // Set PendingIntent into Notification
                .setContentIntent(pIntent)
                // Set RemoteViews into Notification
                .setContent(remoteViews);

        // Locate and set the Image into customnotificationtext.xml ImageViews
       // remoteViews.setImageViewResource(R.id.imagenotileft,R.drawable.ic_launcher);
       // remoteViews.setImageViewResource(R.id.imagenotiright,R.drawable.androidhappy);

        // Locate and set the Text into customnotificationtext.xml TextViews
        remoteViews.setTextViewText(R.id.title,title);
        remoteViews.setTextViewText(R.id.body,body);

        // Create Notification Manager
        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Build Notification with Notification Manager
        notificationmanager.notify(0, builder.build());

    }


}
