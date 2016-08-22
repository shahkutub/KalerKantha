package com.kalerkantho.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aapbd.utils.storage.PersistData;
import com.google.gson.Gson;
import com.kalerkantho.Adapter.AllNewsRecyAdapter;
import com.kalerkantho.Adapter.RecyclerAdapter;
import com.kalerkantho.R;
import com.kalerkantho.Utils.AppConstant;
import com.kalerkantho.holder.AllCategory;

/**
 * Created by Ratan on 7/29/2015.
 */
public class AllnewsFragment extends Fragment {
    private Context con;
    private RecyclerView allNewsList;
    AllNewsRecyAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    AllCategory allCategory;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.allnewsfragment,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        con = getActivity();
        Gson g = new Gson();
        allCategory = g.fromJson(PersistData.getStringData(con, AppConstant.CATEGORY_RESPONSE),AllCategory.class);
        intiU();
    }

    private void intiU() {

        allNewsList = (RecyclerView) getView().findViewById(R.id.allNewsList);
        mLayoutManager = new LinearLayoutManager(con);
        allNewsList.setLayoutManager(mLayoutManager);

        mAdapter = new AllNewsRecyAdapter(con, allCategory.getCategory_list());
        allNewsList.setAdapter(mAdapter);

    }
}
