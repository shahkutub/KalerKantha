package com.example.shohel.khaler_kontho.Utils;

import android.annotation.SuppressLint;

import java.util.Vector;

@SuppressLint("DefaultLocale")
public class AllURL {

	private static String getcommonURLWithParamAndAction(String action,
														 Vector<KeyValue> params) {

		if (params == null || params.size() == 0) {
			return BaseURL.HTTP + action;
		} else {
			String pString = "";

			for (final KeyValue obj : params) {

				pString += obj.getKey().trim() + "=" + obj.getValue().trim()
						+ "&";
			}

			if (pString.endsWith("&")) {
				pString = pString.subSequence(0, pString.length() - 1)
						.toString();
			}

			return BaseURL.HTTP + action + "?" + UrlUtils.encode(pString);
		}
	}
	public static String postRegisterUrl() {
		final Vector<KeyValue> temp = new Vector();
		return getcommonURLWithParamAndAction("register", temp);

	}
	public static String postLoginUrl() {
		final Vector<KeyValue> temp = new Vector();
		return getcommonURLWithParamAndAction("login", temp);

	}

	public static String forgetPosswordUrl() {
		final Vector<KeyValue> temp = new Vector();

		return getcommonURLWithParamAndAction("reminder", temp);

	}

	public static String updateProfileUrl(String api_token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("api_token",api_token));
		return getcommonURLWithParamAndAction("profile", temp);

	}

	public static String sendFeedbackUrl(String api_token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("api_token",api_token));
		return getcommonURLWithParamAndAction("sendFeedback", temp);

	}

	public static String getworkbydateUrl(String api_token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("api_token",api_token));
		return getcommonURLWithParamAndAction("worksByDate", temp);
	}

	public static String sendmsgUrl(String api_token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("api_token",api_token));
		return getcommonURLWithParamAndAction("send-message", temp);
	}

	public static String getUsrListUrl(String token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("token",token));
		return getcommonURLWithParamAndAction("user-list", temp);
	}
	public static String addFriendUrl(String token,String UserId,String matched_id,String  passed) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("token",token));
		temp.add(new KeyValue("UserId",UserId));
		temp.add(new KeyValue("matched_id",matched_id));
		temp.add(new KeyValue("passed",passed));
		return getcommonURLWithParamAndAction("add-friend", temp);

	}
	public static String updatePass(String api_token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("api_token",api_token));
		return getcommonURLWithParamAndAction("update-password", temp);

	}
	public static String getChatListUrl(String token,String partner_id) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("token",token));
		temp.add(new KeyValue("partner_id",partner_id));
		return getcommonURLWithParamAndAction("chat-list", temp);

	}
	public static String sentMessagetUrl(String token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("token",token));;
		return getcommonURLWithParamAndAction("send-msg", temp);

	}

	public static String getFriendUrl(String token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("token",token));;
		return getcommonURLWithParamAndAction("friend-list", temp);

	}

	public static String getSettingsUrl(String api_token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("api_token",api_token));
		return getcommonURLWithParamAndAction("categories", temp);

	}

	public static String getNotificationUrl(String api_token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("api_token",api_token));
		return getcommonURLWithParamAndAction("notifications", temp);

	}
	public static String getConversationUrl(String api_token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("api_token",api_token));
		return getcommonURLWithParamAndAction("conversation", temp);

	}


	public static String getupdatecategorisUrl(String api_token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("api_token",api_token));
		return getcommonURLWithParamAndAction("categories", temp);

	}

	public static String UpdateSettingsUrl() {
		final Vector<KeyValue> temp = new Vector();
		return getcommonURLWithParamAndAction("settings", temp);

	}
	public static String getDeleteAccountUrl() {
		final Vector<KeyValue> temp = new Vector();
		return getcommonURLWithParamAndAction("delete-account", temp);

	}

	public static String getInterestListUrl() {
		final Vector<KeyValue> temp = new Vector();
		return getcommonURLWithParamAndAction("interested-list", temp);

	}
	public static String getEditProfileUrl(String  token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("token",token));
		return getcommonURLWithParamAndAction("edit-profile", temp);

	}

	public static String getMessageList(String  token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("token",token));
		return getcommonURLWithParamAndAction("msg-list", temp);

	}

}