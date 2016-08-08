package com.example.shohel.khaler_kontho.Model;

import java.util.ArrayList;

/**
 * Created by Shohel on 8/2/2016.
 */
public class AllnewsResponse {

    private ArrayList<NewsItem> top_news=new ArrayList<>();
    private ArrayList<NewsItem> latestnews=new ArrayList<>();
    private ArrayList<AllselectedNewsInfo> selectednews=new ArrayList<>();
    private ArrayList<BasaiNewsInfo> basainews=new ArrayList<>();
    private ArrayList<NewsItem> specialnews=new ArrayList<>();

    FeatureNewsInfo featurenews=new FeatureNewsInfo();

    public ArrayList<NewsItem> getTop_news() {
        return top_news;
    }

    public void setTop_news(ArrayList<NewsItem> top_news) {
        this.top_news = top_news;
    }

    public ArrayList<NewsItem> getLatestnews() {
        return latestnews;
    }

    public void setLatestnews(ArrayList<NewsItem> latestnews) {
        this.latestnews = latestnews;
    }

    public ArrayList<AllselectedNewsInfo> getSelectednews() {
        return selectednews;
    }

    public void setSelectednews(ArrayList<AllselectedNewsInfo> selectednews) {
        this.selectednews = selectednews;
    }

    public ArrayList<BasaiNewsInfo> getBasainews() {
        return basainews;
    }

    public void setBasainews(ArrayList<BasaiNewsInfo> basainews) {
        this.basainews = basainews;
    }

    public ArrayList<NewsItem> getSpecialnews() {
        return specialnews;
    }

    public void setSpecialnews(ArrayList<NewsItem> specialnews) {
        this.specialnews = specialnews;
    }

    public FeatureNewsInfo getFeaturenews() {
        return featurenews;
    }

    public void setFeaturenews(FeatureNewsInfo featurenews) {
        this.featurenews = featurenews;
    }
}
