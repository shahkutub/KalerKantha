package com.example.shohel.khaler_kontho;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kalerkantho.R;

/**
 * Created by Ratan on 7/29/2015.
 */
public class MostviewNewsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mostview_news_screen,null);
    }
}
