package com.example.shohel.khaler_kontho.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.shohel.khaler_kontho.Model.LatestNewsInfo;
import com.example.shohel.khaler_kontho.Model.NewsItem;
import com.example.shohel.khaler_kontho.R;
import com.example.shohel.khaler_kontho.Utils.AppConstant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import it.sephiroth.android.library.widget.HListView;
public class NewslistAdapter extends ArrayAdapter<NewsItem> {

    Context context;
    int layoutResourceId;
    ArrayList<NewsItem> newslist;
    NewsItem newsitem;
    LatestNewsInfo latestnews;
    HorizontalAdapter horizontaladaapter;
    HorizontalOtherNewsAdapter othernewsadapter;

    public NewslistAdapter(Context context, ArrayList<NewsItem> newslist) {
        super(context, R.layout.row_common_news_item, newslist);
        this.context = context;
        this.newslist = newslist;
    }

    @Override
    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        LayoutInflater inflater;

        newsitem = newslist.get(position);
        //   Log.e("topnewslist appcons ",""+AppConstant.topnewslist.size());

        if (row == null) {
            if (position == 0) {
                inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(R.layout.row_first_item, parent, false);

                holder = new ViewHolder();
                holder.imgIcon = (ImageView) row.findViewById(R.id.img_commonnews);
                holder.txtTitle = (TextView) row.findViewById(R.id.tvTitleComonnews);
                holder.tv_dattime = (TextView) row.findViewById(R.id.tvDatetime);

                holder.txtTitle.setText(newsitem.getTitle());
                holder.tv_dattime.setText(newsitem.getDatetime());
                Picasso.with(getContext()).load(newsitem.getFeatured_image()).placeholder(R.drawable.ic_launcher).into(holder.imgIcon);

            } else if (position == AppConstant.topnews_size) {
                inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(R.layout.section_latest_news, parent, false);

                holder = new ViewHolder();
                holder.section_header=(TextView) row.findViewById(R.id.idSectionTitle);
                holder.section_header.setText(context.getString(R.string.latest_news));

            }else if (position == AppConstant.latestnews_size) {
                inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(R.layout.footer_horizontallist_item, parent, false);

                holder = new ViewHolder();
                holder.dayHListView = (HListView)row.findViewById(R.id.dayHListView);
                holder.lvHeader = (TextView) row.findViewById(R.id.lvHeader);
                horizontaladaapter=new HorizontalAdapter(context,AppConstant.basainewslist);
                holder.lvHeader.setText(context.getString(R.string.bibid));
                holder.dayHListView.setAdapter(horizontaladaapter);

            }else if (position == AppConstant.seperator_bibid_size) {
                inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(R.layout.section_latest_news, parent, false);

                holder = new ViewHolder();
                holder.section_header=(TextView) row.findViewById(R.id.idSectionTitle);
                holder.section_header.setText(context.getString(R.string.special_news));
            }
            else if (position == AppConstant.specialnews_size) {
                inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(R.layout.footer_horizontallist_item, parent, false);

                holder = new ViewHolder();
                holder.lvHeader = (TextView) row.findViewById(R.id.lvHeader);
                holder.dayHListView = (HListView)row.findViewById(R.id.dayHListView);
                othernewsadapter=new HorizontalOtherNewsAdapter(context,AppConstant.selectednews);
                holder.lvHeader.setText(context.getString(R.string.others_news));
                holder.dayHListView.setAdapter(horizontaladaapter);

            }
            else {
                inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(R.layout.row_common_news_item, parent, false);

                holder = new ViewHolder();
                holder.imgIcon = (ImageView) row.findViewById(R.id.img_commonnews);
                holder.txtTitle = (TextView) row.findViewById(R.id.tvTitleComonnews);
                holder.tv_dattime = (TextView) row.findViewById(R.id.tvDatetime);

                holder.txtTitle.setText(newsitem.getTitle());
                holder.tv_dattime.setText(newsitem.getDatetime());
                Picasso.with(getContext()).load(newsitem.getFeatured_image()).placeholder(R.drawable.ic_launcher).into(holder.imgIcon);
            }

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        //  Log.e("adapter top news >",""+newslist.size()) ;


        return row;
    }

    static class ViewHolder {
        ImageView imgIcon;
        TextView txtTitle;
        TextView lvHeader;
        TextView tv_dattime;
        TextView section_header;
        HListView dayHListView;
    }
}