package com.kalerkantho.Adapter;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kalerkantho.Model.OnItemClickListenerNews;
import com.kalerkantho.R;
import com.kalerkantho.holder.AllCommonNewsItem;

import java.util.ArrayList;
import java.util.List;

public class PhotoRecyAdapter extends RecyclerView.Adapter<PhotoRecyAdapter.MyViewHolder> {
    Context context;
    private final OnItemClickListenerNews listener;
    private List<AllCommonNewsItem> photoList = new ArrayList<AllCommonNewsItem>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;

        public MyViewHolder(View view) {
            super(view);

            photo = (ImageView) view.findViewById(R.id.photo);

        }

        public void bind(final AllCommonNewsItem item, final OnItemClickListenerNews listener) {

            if (!(TextUtils.isEmpty(item.getNews_obj().getImage()))) {
                Glide.with(context).load(item.getNews_obj().getImage()).placeholder(R.drawable.defaulticon).into(photo);
            } else {
                Glide.with(context).load(R.drawable.defaulticon).placeholder(R.drawable.defaulticon).into(photo);
            }
        }

    }
    public PhotoRecyAdapter(Context context, List<AllCommonNewsItem> photoList, OnItemClickListenerNews listener) {
        this.context = context;
        this.listener = listener;
        this.photoList = photoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

         holder.bind(photoList.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }
}
