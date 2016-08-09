package com.example.shohel.khaler_kontho.Utils;


import android.graphics.Bitmap;


import com.example.shohel.khaler_kontho.Model.AllNewsInfo;
import com.example.shohel.khaler_kontho.Model.AllselectedNewsInfo;
import com.example.shohel.khaler_kontho.Model.BasaiNewsInfo;
import com.example.shohel.khaler_kontho.Model.LatestNewsInfo;
import com.example.shohel.khaler_kontho.Model.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shohel on 2/15/2016.
 */
public class AppConstant {
    public static final String[] FACEBOOK_PERMISSION = {"email", "user_about_me", "read_stream", "user_photos", "public_profile" };
    public static String COLOR_MAIN = "#A551D0";

    public static final String COLOR_DEFAULT_MAIN = "#A551D0";
    public static final String COLOR_DEFAULT_SECONDARY = "#FFFFFF";

    public static final String COLOR_SETTINGS = "#4C436E";
    public static final String COLOR_SHARE = "#0054A5";
    public static final String BTN_COLOR_ORIGIN = "#B166D6";


    // news item data
    public static ArrayList<NewsItem> topnewslist;
    public static ArrayList<NewsItem> allnewsinfo;
    public static ArrayList<BasaiNewsInfo> basainewslist;
    public static ArrayList<AllselectedNewsInfo> selectednews;

    // lv position item save

    public static int topnews_size=0;
    public static int seperator_latestnews_size=0;
    public static int latestnews_size=0;
    public static int seperator_bibid_size=0;
    public static int seperator_specialnews_size=0;
    public static int specialnews_size=0;
    public static int seperator_oternews_size=0;







}
