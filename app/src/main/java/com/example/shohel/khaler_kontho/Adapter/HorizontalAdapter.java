package com.example.shohel.khaler_kontho.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shohel.khaler_kontho.Model.BasaiNewsInfo;
import com.example.shohel.khaler_kontho.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shohel on 8/3/2016.
 *
 */


public class HorizontalAdapter extends ArrayAdapter<BasaiNewsInfo> {

    Context context;
    int layoutResourceId;
    ArrayList<BasaiNewsInfo> newslist;
    BasaiNewsInfo newsitem;


    public HorizontalAdapter(Context context, ArrayList<BasaiNewsInfo> newslist) {
        super(context, R.layout.row_others_news, newslist);
        this.context = context;
        this.newslist = newslist;
        //  this.layoutResourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = null;
        LayoutInflater inflater;
        if (row == null) {
            inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.row_others_news, parent, false);
            holder = new ViewHolder();
            holder.imgIcon = (ImageView) row.findViewById(R.id.others_news_icon);
            holder.txtTitle = (TextView) row.findViewById(R.id.otersnews_title);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        newsitem=newslist.get(position);
        Picasso.with(getContext()).load(newsitem.getFeatured_image()).placeholder(R.drawable.ic_launcher).into(holder.imgIcon);
        holder.txtTitle.setText(newsitem.getTitle());

        return row;
    }

    static class ViewHolder {
        ImageView imgIcon;
        TextView txtTitle;

    }
}