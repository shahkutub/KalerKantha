package com.kalerkantho.Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aapbd.utils.storage.PersistData;
import com.google.gson.Gson;
import com.kalerkantho.Adapter.AllNewsRecyAdapter;
import com.kalerkantho.Model.Category;
import com.kalerkantho.R;
import com.kalerkantho.Utils.AppConstant;
import com.kalerkantho.Utils.DividerItemDecoration;
import com.kalerkantho.holder.AllCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ratan on 7/29/2015.
 */
public class PrintVFragment extends Fragment {
    private Context con;
    private RecyclerView printvirsionList;
    AllNewsRecyAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    List<Category> printList= new ArrayList<Category>();
    AllCategory allCategory;
    Drawable dividerDrawable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.printvirsion,null);
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

        printvirsionList = (RecyclerView) getView().findViewById(R.id.printvirsionList);

        mLayoutManager = new LinearLayoutManager(con);
        printvirsionList.setLayoutManager(mLayoutManager);

        if (printList!=null)
            printList.clear();

        dividerDrawable = ContextCompat.getDrawable(con, R.drawable.lineee);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(dividerDrawable);
        printvirsionList.addItemDecoration(dividerItemDecoration);

        for (int i=0;i<allCategory.getCategory_list().size();i++){
            if (allCategory.getCategory_list().get(i).getM_type().equalsIgnoreCase("print")){
                printList.add(allCategory.getCategory_list().get(i));
            }
        }
        mAdapter = new AllNewsRecyAdapter(con, printList);
        printvirsionList.setAdapter(mAdapter);

    }
}
