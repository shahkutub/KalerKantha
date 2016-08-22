package com.kalerkantho.Gcm;


import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.kalerkantho.HomeFragment;
import com.kalerkantho.R;

public class GcmIntentService extends IntentService {
	Context context;
	public static final int NOTIFICATION_ID = 1;
	private NotificationManager mNotificationManager;
	NotificationCompat.Builder builder;
	public static final String TAG = "GCM Demo";

	public GcmIntentService() {
		super("GcmIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		final Bundle extras = intent.getExtras();
		final String msg = intent.getStringExtra("message");
		final GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		final String messageType = gcm.getMessageType(intent);

		if (!extras.isEmpty()) {

			if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
				// sendNotification("Send error: " + extras.toString());
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
				sendNotification("Deleted messages on server: " + extras.toString());
				// If it's a regular GCM message, do some work.
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
				// This loop represents the service doing some work.
				for (int i = 0; i < 5; i++) {
					Log.i(GcmIntentService.TAG, "Working... " + (i + 1)
							+ "/5 @ " + SystemClock.elapsedRealtime());
					try {
						Thread.sleep(500);
					} catch (final InterruptedException e) {
					}
				}
				Log.i(GcmIntentService.TAG, "Completed work @ " + SystemClock.elapsedRealtime());

				 sendNotification(msg);
				Log.i(GcmIntentService.TAG, "Received: " + extras.toString());
			}
		}
		WakefulBroadcastReceiver.completeWakefulIntent(intent);
	}

	private void sendNotification(String msg) {
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		final Intent myintent = new Intent(this, HomeFragment.class);
		myintent.putExtra("message", msg);
		final PendingIntent contentIntent = PendingIntent.getActivity(this, 0, myintent, PendingIntent.FLAG_UPDATE_CURRENT);

		final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this).setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("GCM Notification")
				.setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
				.setContentText(msg);

		mBuilder.setContentIntent(contentIntent);
		mNotificationManager.notify(GcmIntentService.NOTIFICATION_ID,
				mBuilder.build());
	}

}