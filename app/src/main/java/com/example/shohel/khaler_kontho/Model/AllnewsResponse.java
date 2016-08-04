package com.example.shohel.khaler_kontho.Model;

import java.util.ArrayList;

/**
 * Created by Shohel on 8/2/2016.
 */
public class AllnewsResponse {

    private ArrayList<NewsItem> top_news=new ArrayList<>();
    private ArrayList<NewsItem> latestnews=new ArrayList<>();
    private ArrayList<BasaiNewsInfo> basainews=new ArrayList<>();

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

    public ArrayList<BasaiNewsInfo> getBasainews() {
        return basainews;
    }

    public void setBasainews(ArrayList<BasaiNewsInfo> basainews) {
        this.basainews = basainews;
    }
}
