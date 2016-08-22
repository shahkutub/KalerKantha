package com.kalerkantho.Gcm;

import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.kalerkantho.HomeFragment;
import com.kalerkantho.R;

import org.json.JSONObject;

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {

	PowerManager pm;
	WakeLock wl;
	KeyguardManager km;
	KeyguardLock kl;
	private Context con;

	String orderId = "";
	String orderType = "";
	String vibrate = "";
	String action = "";
	String sound = "";
	String receiver_id = "";
	String message = "";
	String sender_id = "";
	String chat_id = "";

	private NotificationManager mNotificationManager;
	NotificationCompat.Builder builder;

	@Override
	public void onReceive(Context context, Intent intent) {
		con = context;
		final String smg = intent.getStringExtra("msg");
		Log.e("Receiver", "Broadcast received: " + smg);
		JSONObject jsonObject;

		try {
			jsonObject = new JSONObject(smg);
			orderId = jsonObject.optString("order_id");
			orderType = jsonObject.optString("order_type");
			vibrate = jsonObject.optString("vibrate");
			action = jsonObject.optString("action");
			sound = jsonObject.optString("sound");
			receiver_id = jsonObject.optString("receiver_id");
			message = jsonObject.optString("message");
			sender_id = jsonObject.optString("sender_id");
			chat_id = jsonObject.optString("chat_id");

		} catch (final Exception e) {
			e.printStackTrace();
		}

		if (orderType.equalsIgnoreCase("")) {



				sendNotification("You've got a new message", "chat");


		} else if (orderType.equalsIgnoreCase("normal")) {



				sendNotification("Please proceed your payment", "order");


		}

	}

	static void updateMyActivity(Context context, String message) {
		final Intent intent = new Intent("unique_name");
		intent.putExtra("message", message);
		context.sendBroadcast(intent);
	}

	private void sendNotification(String msg, String act) {

		mNotificationManager = (NotificationManager) con.getSystemService(Context.NOTIFICATION_SERVICE);
		Intent intent = null;

		if (act.equalsIgnoreCase("chat")) {



		} else if (act.equalsIgnoreCase("order")) {



		} else if (act.equalsIgnoreCase("parcel")) {


		}else{

		}

		final PendingIntent contentIntent = PendingIntent.getActivity(con, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(con);
		final Resources res = con.getResources();
		final int icon = R.drawable.ic_launcher;
		mBuilder.setSmallIcon(icon);
		mBuilder.setContentTitle(res.getString(R.string.app_name));
		mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(msg));
		mBuilder.setContentText(msg);
		mBuilder.setContentIntent(contentIntent);
		mBuilder.setAutoCancel(true);

		if (sound.equalsIgnoreCase("1")) {
			mBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
		}
		if (vibrate.equalsIgnoreCase("1")) {
			mBuilder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
		}

		mNotificationManager.notify(0, mBuilder.build());

	}

	private void restartApplication(Context context) {

		final Intent mStartActivity = new Intent(context, HomeFragment.class);
		mStartActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		final int mPendingIntentId = 123456;
		final PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
		final AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
		System.exit(0);
	}

	private void msgActivity(Context context) {
		final Intent mStartActivity = new Intent(context, HomeFragment.class);
		mStartActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		final int mPendingIntentId = 123456;
		final PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
		final AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1, mPendingIntent);
	}

}
