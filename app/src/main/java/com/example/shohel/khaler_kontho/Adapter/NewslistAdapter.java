package com.example.shohel.khaler_kontho.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.shohel.khaler_kontho.Utils.AppConstant;
import com.example.shohel.khaler_kontho.holder.AllCommonNewsItem;
import com.kalerkantho.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.widget.HListView;
public class NewslistAdapter extends ArrayAdapter<AllCommonNewsItem> {

    Context context;
    int layoutResourceId;
    List<AllCommonNewsItem> newslist = new ArrayList<AllCommonNewsItem>();
    AllCommonNewsItem newsitem;
    HorizontalAdapter horizontaladaapter;
    HorizontalOtherNewsAdapter othernewsadapter;

    public NewslistAdapter(Context context,List<AllCommonNewsItem> newslist) {
        super(context, R.layout.row_first_item, newslist);
        this.context = context;
        this.newslist = newslist;
    }

    @Override
    public int getViewTypeCount() {

        return 4;
    }

    @Override
    public int getCount() {
        return newslist.size();


    }

    @Override
    public int getItemViewType(int position) {

        if(newslist.get(position).getType().equalsIgnoreCase("fullscreen"))
        {
            position=0;
        }else if(newslist.get(position).getType().equalsIgnoreCase("defaultscreen"))
        {
            position=1;
        }else if(newslist.get(position).getType().equalsIgnoreCase("titleshow"))
        {
            position=2;
        }else if(newslist.get(position).getType().equalsIgnoreCase("horizontal"))
        {
            position=3;
        }



        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        LayoutInflater inflater;
        newsitem = newslist.get(position);
        int type = getItemViewType(position);


        if (row == null) {
            inflater=  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (type == 0) {
              //  inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(R.layout.row_first_item, parent, false);
                holder = new ViewHolder();
                /*

                holder.imgIcon = (ImageView) row.findViewById(R.id.img_commonnews);
                holder.txtTitle = (TextView) row.findViewById(R.id.tvTitleComonnews);
                holder.tv_dattime = (TextView) row.findViewById(R.id.tvDatetime);
                holder.catagoryTite     = (TextView) row.findViewById(R.id.cat_type);*/




            } else if (type == 1) {



                //else if (position == AppConstant.topnews_size)
               // inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(R.layout.row_common_news_item, parent, false);

                holder = new ViewHolder();
               // holder.section_header=(TextView) row.findViewById(R.id.idSectionTitle);
                //holder.section_header.setText(context.getString(R.string.latest_news));

            }else if (type == 2) {



                //else if (position == AppConstant.latestnews_size)
              //  inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(R.layout.headertilte, parent, false);

                holder = new ViewHolder();
                //holder.dayHListView = (HListView)row.findViewById(R.id.dayHListView);
                //holder.lvHeader = (TextView) row.findViewById(R.id.lvHeader);
               // horizontaladaapter=new HorizontalAdapter(context,AppConstant.basainewslist);
                //holder.lvHeader.setText(context.getString(R.string.bibid));
                //holder.dayHListView.setAdapter(horizontaladaapter);



            }
            else if(type==3){
                //inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(R.layout.footer_horizontallist_item, parent, false);

               holder = new ViewHolder();
               // holder.imgIcon = (ImageView) row.findViewById(R.id.img_commonnews);
               // holder.txtTitle = (TextView) row.findViewById(R.id.tvTitleComonnews);
               // holder.tv_dattime = (TextView) row.findViewById(R.id.tvDatetime);

               // holder.txtTitle.setText(newsitem.getTitle());
                //holder.tv_dattime.setText(newsitem.getDatetime());
               // Picasso.with(getContext()).load(newsitem.getFeatured_image()).placeholder(R.drawable.ic_launcher).into(holder.imgIcon);
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
        TextView catagoryTite;
        TextView tv_dattime;
        TextView section_header;
        HListView dayHListView;
    }
}