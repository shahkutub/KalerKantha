package com.kalerkantho.Adapter;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kalerkantho.Model.Category;
import com.kalerkantho.R;

import java.util.ArrayList;
import java.util.List;

public class Menu2RecyAdapter extends RecyclerView.Adapter<Menu2RecyAdapter.MyViewHolder> {
    Context context;
   List<Category> onlineList= new ArrayList<Category>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView menu;

        public MyViewHolder(View view) {
            super(view);
            menu = (TextView) view.findViewById(R.id.headlist);
        }
    }
    public Menu2RecyAdapter(Context context, List<Category> onlineList) {
        this.context = context;
        this.onlineList = onlineList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Typeface face_reg = Typeface.createFromAsset(context.getAssets(), "fonts/SolaimanLipi_reg.ttf");

        if (!TextUtils.isEmpty(onlineList.get(position).getName())){
            holder.menu.setText(onlineList.get(position).getName());
        }else{
            holder.menu.setText("");
        }

        holder.menu.setTypeface(face_reg);
    }

    @Override
    public int getItemCount() {
        return onlineList.size();
    }
}
