package com.kalerkantho.Dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aapbd.utils.storage.PersistData;
import com.google.gson.Gson;
import com.kalerkantho.Model.LoginResponse;
import com.kalerkantho.R;
import com.kalerkantho.Utils.AllURL;
import com.kalerkantho.Utils.AppConstant;
import com.kalerkantho.Utils.BusyDialog;
import com.kalerkantho.Utils.NetInfo;
import com.kalerkantho.Utils.PersistentUser;
import com.kalerkantho.Utils.Registration;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by hp on 8/30/2016.
 */
public class HelpDialogFragment extends DialogFragment {

    private Context con;
    private View view;
    private WebView helpWeb;
    private ImageView dissmissHelpBtn;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.help_dialog, container, true);
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
        dissmissHelpBtn = (ImageView) view.findViewById(R.id.dissmissHelpBtn);
        helpWeb = (WebView) view.findViewById(R.id.helpWeb);
        dissmissHelpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        getWebView(AllURL.getHelp());
    }

    public void getWebView(String url) {
        final CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        CookieSyncManager.createInstance(con);

        helpWeb.clearCache(true);
        helpWeb.clearHistory();

        helpWeb.getSettings().setJavaScriptEnabled(true);
        helpWeb.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        helpWeb.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        helpWeb.getSettings().setUserAgentString("silly_that_i_have_to_do_this");
        helpWeb.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        helpWeb.getSettings().setLoadWithOverviewMode(true);
        helpWeb.getSettings().setUseWideViewPort(true);
        helpWeb.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        helpWeb.setScrollbarFadingEnabled(true);

        helpWeb.loadUrl(url);

        helpWeb.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                //busy = new BusyDialog(con, true, false);
                //busy.show();

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

               // busy.dismis();

            }
        });

    }
}
