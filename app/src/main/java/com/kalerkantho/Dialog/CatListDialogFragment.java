package com.kalerkantho.Dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kalerkantho.R;
import com.kalerkantho.Utils.AllURL;

public class CatListDialogFragment extends DialogFragment {

    private Context con;
    private View view;
    private ImageView dissmissCatListBtn;
    private TextView catHeadText;
    private ProgressBar progressCat;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.catlist_dialog, container, true);
        con = getActivity();
        initUi();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void initUi() {

        final Typeface face_bold = Typeface.createFromAsset(con.getAssets(), "fonts/SolaimanLipi_Bold.ttf");
        dissmissCatListBtn = (ImageView) view.findViewById(R.id.dissmissCatListBtn);

        progressCat = (ProgressBar) view.findViewById(R.id.progressCat);
        catHeadText = (TextView) view.findViewById(R.id.catHeadText);

        catHeadText.setText(getString(R.string.tram1));
        catHeadText.setTypeface(face_bold);

        dissmissCatListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

    }

}
