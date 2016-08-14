package com.example.shohel.khaler_kontho.holder;

import com.example.shohel.khaler_kontho.Model.All_Cat_News_Obj;
import com.example.shohel.khaler_kontho.Model.CommonNewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wlaptop on 8/14/2016.
 */
public class AllNewsObj {
    private int code;
    private int status;
    private String msg;
    private List<CommonNewsItem> top_news = new ArrayList<CommonNewsItem>();
    private List<CommonNewsItem> blueslide = new ArrayList<CommonNewsItem>();
    private List<CommonNewsItem> redslider = new ArrayList<CommonNewsItem>();
    private List<All_Cat_News_Obj> all_cat_news = new ArrayList<All_Cat_News_Obj>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<CommonNewsItem> getTop_news() {
        return top_news;
    }

    public void setTop_news(List<CommonNewsItem> top_news) {
        this.top_news = top_news;
    }

    public List<CommonNewsItem> getBlueslide() {
        return blueslide;
    }

    public void setBlueslide(List<CommonNewsItem> blueslide) {
        this.blueslide = blueslide;
    }

    public List<CommonNewsItem> getRedslider() {
        return redslider;
    }

    public void setRedslider(List<CommonNewsItem> redslider) {
        this.redslider = redslider;
    }

    public List<All_Cat_News_Obj> getAll_cat_news() {
        return all_cat_news;
    }

    public void setAll_cat_news(List<All_Cat_News_Obj> all_cat_news) {
        this.all_cat_news = all_cat_news;
    }
}
