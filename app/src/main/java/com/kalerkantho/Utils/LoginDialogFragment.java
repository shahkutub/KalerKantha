package com.kalerkantho.Utils;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aapbd.utils.storage.PersistData;
import com.google.gson.Gson;
import com.kalerkantho.Dialog.Registration;
import com.kalerkantho.Model.LoginResponse;
import com.kalerkantho.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by hp on 8/30/2016.
 */
public class LoginDialogFragment extends DialogFragment {

    private Context con;
    private View view;
private String email,password;
    private TextView tvEnterLogin,tvRegistrationLog;
    private EditText etEmailLogin, etPaswordLogin;
    private LoginResponse logInResponse;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_dialog, container, true);
        con = getActivity();
        initUi();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void initUi() {
        final Typeface face_normal = Typeface.createFromAsset(con.getAssets(), "fonts/SolaimanLipi_reg.ttf");

        ImageView imgCrossLogin= (ImageView) view.findViewById(R.id.imgCrossLogin);
        TextView tvLogin = (TextView) view.findViewById(R.id.tvLogin);
        tvEnterLogin = (TextView) view.findViewById(R.id.tvEnterLogin);
        tvRegistrationLog = (TextView) view.findViewById(R.id.tvRegistrationLog);

        etEmailLogin = (EditText) view.findViewById(R.id.etEmailLogin);
        etPaswordLogin = (EditText) view.findViewById(R.id.etPaswordLogin);
        tvLogin.setTypeface(face_normal);
        tvEnterLogin.setTypeface(face_normal);
        etEmailLogin.setTypeface(face_normal);
        etPaswordLogin.setTypeface(face_normal);
        tvRegistrationLog.setTypeface(face_normal);



        imgCrossLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        tvRegistrationLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registration registration= new Registration();
                registration.setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar);
                registration.show(getActivity().getFragmentManager(), "");
                getDialog().dismiss();
            }
        });

        tvEnterLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // --------Check user id and password-----------
                if (TextUtils.isEmpty(etEmailLogin.getText().toString())) {
                    AlertMessage.showMessage(con, getString(R.string.app_name), getResources().getString(R.string.provide_email));
                } else if (TextUtils.isEmpty(etPaswordLogin.getText().toString())) {
                    AlertMessage.showMessage(con, getString(R.string.app_name), getResources().getString(R.string.provide_password));
                } else {

                    //--------- Set data of same type of API--------------
                    email = etEmailLogin.getText().toString();
                    password = etPaswordLogin.getText().toString();

					/*
						-------------------API-2. Normal User Login call-------------------
					 */
                    if (PersistentUser.isLogged(con)) {
                        PersistentUser.logOut(con);
                    }
                    callNormalUserLoginAPI(AllURL.loginURL());
                }
            }
        });
    }

    protected void callNormalUserLoginAPI(final String url) {
        /**
         * --------------- Check Internet------------
         */
        if (!NetInfo.isOnline(con)) {
            AlertMessage.showMessage(con, getString(R.string.app_name), "No Internet!");
            return;
        }

        /**
         * ------Show Busy Dialog------------------
         */
        final BusyDialog busyNow = new BusyDialog(con, true, false);
        busyNow.show();
        /**
         * ---------Create object of  RequestParams to send value with URL---------------
         */
        final RequestParams param = new RequestParams();

        try {
            param.put("email", email);
            param.put("password", password);
            param.put("device_type", "android");
            param.put("push_id", PersistData.getStringData(con, AppConstant.GCMID));
            param.put("registrationtype", "normal");
        } catch (final Exception e1) {
            e1.printStackTrace();
        }
        /**
         * ---------Create object of  AsyncHttpClient class to heat server ---------------
         */
        final AsyncHttpClient client = new AsyncHttpClient();
        Log.e("Login URL ", ">>" + url);
        client.post(url, param, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] response) {
                //-----------Off Busy Dialog ----------------
                if (busyNow != null) {
                    busyNow.dismis();
                }
                //-----------------Print Response--------------------
                Log.e("LogInresposne ", ">>" + new String(response));

                //------------Data persist using Gson------------------
                Gson g = new Gson();
                logInResponse = g.fromJson(new String(response), LoginResponse.class);

                Log.e("Loginstatus", "=" + logInResponse.getStatus());

                if (logInResponse.getStatus().equalsIgnoreCase("1")) {
                    Toast.makeText(con, logInResponse.getMsg(), Toast.LENGTH_LONG).show();
                    PersistentUser.setLogin(con);
                    PersistData.setStringData(con, AppConstant.id,
                            logInResponse.getUserdetails().getId());
                    PersistData.setStringData(con, AppConstant.email,
                            logInResponse.getUserdetails().getEmail());
//
                    PersistData.setStringData(con, AppConstant.token,
                            logInResponse.getToken());

                    Log.e("token", "=" + PersistData.getStringData(con, AppConstant.token));

                    getDialog().dismiss();
                } else {
					AlertMessage.showMessage(con, getResources().getString(R.string.app_name), logInResponse.getMsg() + "");
                    return;
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)

//				Log.e("LoginerrorResponse", new String(errorResponse));

                if (busyNow != null) {
                    busyNow.dismis();
                }
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried

            }
        });

    }
}
