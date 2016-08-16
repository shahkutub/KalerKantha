package com.example.shohel.khaler_kontho.Adapter;

import android.app.Activity;
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
import com.kalerkantho.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HorizontalAdapter extends ArrayAdapter<CommonNewsItem> {

    Context context;
    List<CommonNewsItem> hlist = new ArrayList<CommonNewsItem>();

    public HorizontalAdapter(Context context, List<CommonNewsItem> hlist) {
        super(context, R.layout.row_others_news, hlist);
        this.context = context;
        this.hlist = hlist;

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

            final Typeface face_reg = Typeface.createFromAsset(context.getAssets(), "fonts/SolaimanLipi_reg.ttf");

            if (!TextUtils.isEmpty(hlist.get(position).getImage())){
                Picasso.with(getContext()).load(hlist.get(position).getImage()).placeholder(R.drawable.ic_launcher).into(holder.imgIcon);
            }else{
                Picasso.with(getContext()).load(R.drawable.ic_launcher).placeholder(R.drawable.ic_launcher).into(holder.imgIcon);
            }

            if (!TextUtils.isEmpty(hlist.get(position).getTitle())){
                holder.txtTitle.setText(hlist.get(position).getTitle());
            }else{
                holder.txtTitle.setText("");
            }

            holder.txtTitle.setTypeface(face_reg);

            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }


        return row;
    }

    static class ViewHolder {
        ImageView imgIcon;
        TextView txtTitle;

    }
}