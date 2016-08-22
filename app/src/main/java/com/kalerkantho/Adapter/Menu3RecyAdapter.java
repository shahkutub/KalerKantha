package com.kalerkantho.Adapter;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kalerkantho.R;

import java.util.ArrayList;
import java.util.List;

public class Menu3RecyAdapter extends RecyclerView.Adapter<Menu3RecyAdapter.MyViewHolder> {
    Context context;
   // List<CommonNewsItem> hlist = new ArrayList<CommonNewsItem>();
   List<String> heading4= new ArrayList<String>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView menu;

        public MyViewHolder(View view) {
            super(view);
            menu = (TextView) view.findViewById(R.id.headlist);
        }
    }
   /* public MenuRecyAdapter(Context context, List<CommonNewsItem> hlist) {
        this.context = context;
        this.hlist = hlist;
    }*/

    public Menu3RecyAdapter(Context context, List<String> heading4) {
        this.context = context;
        this.heading4 = heading4;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Typeface face_reg = Typeface.createFromAsset(context.getAssets(), "fonts/SolaimanLipi_reg.ttf");

        if (!TextUtils.isEmpty(heading4.get(position))){
            holder.menu.setText(heading4.get(position));
        }else{
            holder.menu.setText("");
        }

        holder.menu.setTypeface(face_reg);
    }

    @Override
    public int getItemCount() {
        return heading4.size();
    }
}
