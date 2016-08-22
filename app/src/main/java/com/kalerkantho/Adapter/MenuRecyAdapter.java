package com.kalerkantho.Adapter;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kalerkantho.Model.Category;
import com.kalerkantho.Model.OnItemClickListener;
import com.kalerkantho.R;

import java.util.ArrayList;
import java.util.List;

public class MenuRecyAdapter extends RecyclerView.Adapter<MenuRecyAdapter.MyViewHolder> {
    Context context;
    List<Category> printList= new ArrayList<Category>();
    private final OnItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView menu;

        public MyViewHolder(View view) {
            super(view);
            menu = (TextView) view.findViewById(R.id.headlist);
        }

        public void bind(final Category item, final OnItemClickListener listener) {

            final Typeface face_reg = Typeface.createFromAsset(context.getAssets(), "fonts/SolaimanLipi_reg.ttf");

            if (!TextUtils.isEmpty(item.getName())){
              menu.setText(item.getName());
            }else{
                menu.setText("");
            }

           menu.setTypeface(face_reg);

            menu.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    //listener.onItemClick(item);
                    Toast.makeText(context,"Click",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public MenuRecyAdapter(Context context, List<Category> printList,OnItemClickListener listener) {
        this.context = context;
        this.printList = printList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.bind(printList.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return printList.size();
    }

}
