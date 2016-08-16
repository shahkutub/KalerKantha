package com.example.shohel.khaler_kontho.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.shohel.khaler_kontho.Model.CommonNewsItem;
import com.example.shohel.khaler_kontho.holder.AllCommonNewsItem;
import com.kalerkantho.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.widget.HListView;
public class NewslistAdapter extends ArrayAdapter<AllCommonNewsItem> {

    Context context;
    List<AllCommonNewsItem> newslist = new ArrayList<AllCommonNewsItem>();

    AllCommonNewsItem newsitem;
    HorizontalAdapter horizontaladaapter;


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

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (type == 0) {

                if (row == null) {
                    row = inflater.inflate(R.layout.row_first_item, parent, false);
                    holder = new ViewHolder();

                    final Typeface face_reg = Typeface.createFromAsset(context.getAssets(), "fonts/SolaimanLipi_reg.ttf");
                    final Typeface face_bold = Typeface.createFromAsset(context.getAssets(), "fonts/SolaimanLipi_Bold.ttf");

                    holder.imgIcon = (ImageView) row.findViewById(R.id.img_commonnews);
                    holder.txtTitle = (TextView) row.findViewById(R.id.tvTitleComonnews);
                    holder.tv_dattime = (TextView) row.findViewById(R.id.tvDatetime);
                    holder.catagoryTite = (TextView) row.findViewById(R.id.cat_type);

                    if (!(TextUtils.isEmpty(newsitem.getNews_obj().getImage()))) {
                        Picasso.with(getContext()).load(newsitem.getNews_obj().getImage()).placeholder(R.drawable.ic_launcher).into(holder.imgIcon);
                    } else {
                        Picasso.with(getContext()).load(R.drawable.ic_launcher).placeholder(R.drawable.ic_launcher).into(holder.imgIcon);
                    }

                    if (!TextUtils.isEmpty(newsitem.getNews_obj().getTitle())) {
                        holder.txtTitle.setText(newsitem.getNews_obj().getTitle());

                    } else {
                        holder.txtTitle.setText("");
                    }


                    if (!TextUtils.isEmpty(newsitem.getNews_obj().getBanglaDateString())){

                        holder.tv_dattime.setText(newsitem.getNews_obj().getBanglaDateString());

                    }else{
                        holder.tv_dattime.setText("");

                    }
                    holder.txtTitle.setTypeface(face_bold);
                    holder.tv_dattime.setTypeface(face_reg);
                    holder.catagoryTite.setTypeface(face_reg);

                    row.setTag(holder);
                }else {
                    holder = (ViewHolder) row.getTag();
                }

            } else if (type == 1) {

                    if (row == null) {
                        row = inflater.inflate(R.layout.row_common_news_item, parent, false);
                        holder = new ViewHolder();


                        final Typeface face_reg = Typeface.createFromAsset(context.getAssets(), "fonts/SolaimanLipi_reg.ttf");
                        final Typeface face_bold = Typeface.createFromAsset(context.getAssets(), "fonts/SolaimanLipi_Bold.ttf");

                        holder.imgIcon = (ImageView) row.findViewById(R.id.img_commonnews);
                        holder.txtTitle = (TextView) row.findViewById(R.id.tvTitleComonnews);
                        holder.tv_dattime = (TextView) row.findViewById(R.id.tvDatetime);


                        if (!(TextUtils.isEmpty(newsitem.getNews_obj().getImage()))) {
                            Picasso.with(getContext()).load(newsitem.getNews_obj().getImage()).placeholder(R.drawable.ic_launcher).into(holder.imgIcon);
                        } else {
                            Picasso.with(getContext()).load(R.drawable.ic_launcher).placeholder(R.drawable.ic_launcher).into(holder.imgIcon);
                        }

                        if (!TextUtils.isEmpty(newsitem.getNews_obj().getTitle())) {
                            holder.txtTitle.setText(newsitem.getNews_obj().getTitle());

                        } else {
                            holder.txtTitle.setText("");
                        }

                        if (!TextUtils.isEmpty(newsitem.getNews_obj().getBanglaDateString())){
                            holder.tv_dattime.setText(newsitem.getNews_obj().getBanglaDateString());
                        }else{
                            holder.tv_dattime.setText("");
                        }

                        holder.txtTitle.setTypeface(face_bold);
                        holder.tv_dattime.setTypeface(face_reg);




                        row.setTag(holder);
                    }else {
                        holder = (ViewHolder) row.getTag();
                    }
            }else if (type == 2) {

                if (row == null) {

                    row = inflater.inflate(R.layout.headertilte, parent, false);
                     holder = new ViewHolder();

                    final Typeface face_reg = Typeface.createFromAsset(context.getAssets(), "fonts/SolaimanLipi_reg.ttf");
                    final Typeface face_bold = Typeface.createFromAsset(context.getAssets(), "fonts/SolaimanLipi_Bold.ttf");

                     holder.lvHeader = (TextView) row.findViewById(R.id.headerTitle);
                    if(!TextUtils.isEmpty(newsitem.getCategory_title())){
                        holder.lvHeader.setText(newsitem.getCategory_title());
                    }else{
                        holder.lvHeader.setText("");
                    }

                    holder.lvHeader.setTypeface(face_bold);

                    row.setTag(holder);

                }else {
                    holder = (ViewHolder) row.getTag();
                }




            }
            else if(type==3) {

                if (row == null) {
                    row = inflater.inflate(R.layout.footer_horizontallist_item, parent, false);

                    holder = new ViewHolder();

                    AllCommonNewsItem newsitem1=newslist.get(position);
                 //   hList.addAll(newsitem.getList_news_obj());
                    holder.dayHListView = (HListView)row.findViewById(R.id.dayHListView);
                    horizontaladaapter=new HorizontalAdapter(context,newsitem1.getList_news_obj());
                    holder.dayHListView.setAdapter(horizontaladaapter);
                    horizontaladaapter.notifyDataSetChanged();
                    row.setTag(holder);

                }else {
                    holder = (ViewHolder) row.getTag();
                }
            }

        return row;
    }

    static class ViewHolder {
        ImageView imgIcon;
        TextView txtTitle;
        TextView lvHeader;
        TextView catagoryTite;
        TextView tv_dattime;
        HListView dayHListView;
    }
}