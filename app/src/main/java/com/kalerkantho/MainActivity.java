package com.kalerkantho;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aapbd.utils.storage.PersistData;
import com.google.gson.Gson;
import com.kalerkantho.Adapter.Menu2RecyAdapter;
import com.kalerkantho.Adapter.Menu3RecyAdapter;
import com.kalerkantho.Adapter.MenuRecyAdapter;
import com.kalerkantho.Fragment.MotamotFragment;
import com.kalerkantho.Fragment.PhotoFragment;
import com.kalerkantho.Fragment.SettingFragment;
import com.kalerkantho.Model.Category;
import com.kalerkantho.MyDb.MyDBHandler;
import com.kalerkantho.Utils.AppConstant;
import com.kalerkantho.Utils.DividerItemDecoration;
import com.kalerkantho.holder.AllCategory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    RecyclerView listViewMenu,shokolshogbadList,nirbachitoList;
    private MenuRecyAdapter mAdapter;
    private Menu2RecyAdapter mAdapter2;
    private Menu3RecyAdapter mAdapter3;
    Drawable dividerDrawable;
    List<Category> printList= new ArrayList<Category>();
    List<Category> onlineList= new ArrayList<Category>();
    private MyDBHandler db;
    private LinearLayoutManager mLayoutManager,mmLayoutManager,mmmLayoutManager;

    private ImageView showPrintListView,shokolShonbadListView,nirbacitoMenuView;
    AllCategory allCategory;

    private RelativeLayout printBtn,shokolBtn,nirbachitoBtn;
    private TextView homeMenu,shirshoMenu,shorboMenu,shorbaMenu,printVersion;
    private  TextView nirbachitoSongbad,shokolShogbad,nirbachitoCategory;
    private TextView favorite,photogalery,setting,motamot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new MyDBHandler(getApplicationContext());
        /**
         *Setup the DrawerLayout and NavigationView
         */

        final Typeface face_bold = Typeface.createFromAsset(getApplication().getAssets(), "fonts/SolaimanLipi_Bold.ttf");

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        mDrawerLayout.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);
        listViewMenu = (RecyclerView) findViewById(R.id.listViewMenu);
        shokolshogbadList= (RecyclerView) findViewById(R.id.shokolshogbadList);
        nirbachitoList = (RecyclerView) findViewById(R.id.nirbachitoList);

        showPrintListView = (ImageView) findViewById(R.id.showPrintListView);
        shokolShonbadListView = (ImageView) findViewById(R.id.shokolShonbadListView);
        nirbacitoMenuView = (ImageView) findViewById(R.id.nirbacitoMenuView);

        homeMenu = (TextView) findViewById(R.id.homeMenu);
        shirshoMenu = (TextView) findViewById(R.id.shirshoMenu);
        shorboMenu = (TextView) findViewById(R.id.shorboMenu);
        shorbaMenu = (TextView) findViewById(R.id.shorbaMenu);
        printVersion = (TextView) findViewById(R.id.printVersion);
        printBtn = (RelativeLayout) findViewById(R.id.printBtn);
        shokolBtn = (RelativeLayout) findViewById(R.id.shokolBtn);
        nirbachitoBtn = (RelativeLayout) findViewById(R.id.nirbachitoBtn);

        nirbachitoSongbad = (TextView) findViewById(R.id.nirbachitoSongbad);
        shokolShogbad = (TextView) findViewById(R.id.shokolShogbad);
        nirbachitoCategory = (TextView) findViewById(R.id.nirbachitoCategory);
        favorite = (TextView) findViewById(R.id.favorite);
        photogalery = (TextView) findViewById(R.id.photogalery);
        setting = (TextView) findViewById(R.id.setting);
        motamot = (TextView) findViewById(R.id.motamot);

        homeMenu.setTypeface(face_bold);
        shirshoMenu.setTypeface(face_bold);
        shorboMenu.setTypeface(face_bold);
        shorbaMenu.setTypeface(face_bold);
        printVersion.setTypeface(face_bold);
        nirbachitoSongbad.setTypeface(face_bold);
        shokolShogbad.setTypeface(face_bold);
        nirbachitoCategory.setTypeface(face_bold);
        favorite.setTypeface(face_bold);
        photogalery.setTypeface(face_bold);
        setting.setTypeface(face_bold);
        motamot.setTypeface(face_bold);

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        TabFragment fragment= new TabFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("pos", 0);
        fragment.setArguments(bundle);

        mFragmentTransaction.replace(R.id.containerView, fragment).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mmLayoutManager = new LinearLayoutManager(getApplicationContext());
        mmmLayoutManager = new LinearLayoutManager(getApplicationContext());

        listViewMenu.setLayoutManager(mLayoutManager);
        shokolshogbadList.setLayoutManager(mmLayoutManager);
        nirbachitoList.setLayoutManager(mmmLayoutManager);

        dividerDrawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.lineee);


        Gson g = new Gson();
        allCategory = g.fromJson(PersistData.getStringData(getApplicationContext(), AppConstant.CATEGORY_RESPONSE),AllCategory.class);


        homeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TabFragment fragment= new TabFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("pos", 0);
                fragment.setArguments(bundle);
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.containerView, fragment).commit();
                mDrawerLayout.closeDrawers();

            }
        });

        shirshoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TabFragment fragment= new TabFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("pos", 1);
                fragment.setArguments(bundle);
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.containerView, fragment).commit();
                mDrawerLayout.closeDrawers();


            }
        });

        shorboMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabFragment fragment= new TabFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("pos", 2);
                fragment.setArguments(bundle);
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.containerView, fragment).commit();
                mDrawerLayout.closeDrawers();
            }
        });

        shorbaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TabFragment fragment= new TabFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("pos", 3);
                fragment.setArguments(bundle);
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.containerView, fragment).commit();
                mDrawerLayout.closeDrawers();
            }
        });

        printBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



               if (listViewMenu.getVisibility() == View.GONE){
                   listViewMenu.setVisibility(View.VISIBLE);


                   printList.clear();

                   for (int i=0;i<allCategory.getCategory_list().size();i++){
                       if (allCategory.getCategory_list().get(i).getM_type().equalsIgnoreCase("print")){

                           printList.add(allCategory.getCategory_list().get(i));
                       }
                   }

                   if(printList.size()>0){

                       showPrintListView.setImageResource(R.drawable.back_show);
                   }

                   mAdapter = new MenuRecyAdapter(getApplicationContext(), printList,null);
                   listViewMenu.setAdapter(mAdapter);
                   RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(dividerDrawable);
                   listViewMenu.addItemDecoration(dividerItemDecoration);

               }else{
                   showPrintListView.setImageResource(R.drawable.back_gone);
                   listViewMenu.setVisibility(View.GONE);
               }


            }
        });

        nirbachitoSongbad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TabFragment fragment= new TabFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("pos", 4);
                fragment.setArguments(bundle);
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.containerView, fragment).commit();
                mDrawerLayout.closeDrawers();

            }
        });


        shokolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shokolshogbadList.getVisibility() == View.GONE){

                    shokolshogbadList.setVisibility(View.VISIBLE);
                    onlineList.clear();

                    for (int i=0;i<allCategory.getCategory_list().size();i++){

                        if (allCategory.getCategory_list().get(i).getM_type().equalsIgnoreCase("online")){

                            onlineList.add(allCategory.getCategory_list().get(i));
                        }
                    }

                    if(onlineList.size()>0){

                        shokolShonbadListView.setImageResource(R.drawable.back_show);
                    }

                    mAdapter2 = new Menu2RecyAdapter(getApplicationContext(), onlineList);
                    shokolshogbadList.setAdapter(mAdapter2);
                    RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(dividerDrawable);
                    shokolshogbadList.addItemDecoration(dividerItemDecoration);

                }else{
                    shokolShonbadListView.setImageResource(R.drawable.back_gone);
                    shokolshogbadList.setVisibility(View.GONE);
                }
            }
        });


        nirbachitoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nirbachitoList.getVisibility() == View.GONE){


                    if(db.getCatList().size()>0){
                        nirbacitoMenuView.setImageResource(R.drawable.back_show);
                    }

                    nirbachitoList.setVisibility(View.VISIBLE);
                    mAdapter3 = new Menu3RecyAdapter(getApplicationContext(),null);
                    nirbachitoList.setAdapter(mAdapter3);
                    mAdapter3.notifyDataSetChanged();
                    RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(dividerDrawable);
                    nirbachitoList.addItemDecoration(dividerItemDecoration);



                }else{
                    nirbacitoMenuView.setImageResource(R.drawable.back_gone);
                    nirbachitoList.setVisibility(View.GONE);
                }
            }
        });

        photogalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PhotoFragment fragment= new PhotoFragment();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.containerView, fragment).commit();
                mDrawerLayout.closeDrawers();


            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SettingFragment fragment= new SettingFragment();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.containerView, fragment).commit();
                mDrawerLayout.closeDrawers();

            }
        });


        motamot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MotamotFragment fragment= new MotamotFragment();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.containerView, fragment).commit();
                mDrawerLayout.closeDrawers();

            }
        });






        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                xfragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();

               /* if (menuItem.getItemId() == R.id.nav_item_home) {
                    AppConstant.POSITION=0;


                    mDrawerLayout.closeDrawers();
                    return true;
                }else if(menuItem.getItemId() == R.id.nav_item_latest){

                    AppConstant.POSITION=1;

                    mDrawerLayout.closeDrawers();
                    return true;

                }else if(menuItem.getItemId() == R.id.nav_item_last){

                    AppConstant.POSITION=2;

                    mDrawerLayout.closeDrawers();
                    return true;

                }else if(menuItem.getItemId() == R.id.nav_item_most){

                    AppConstant.POSITION=3;

                    mDrawerLayout.closeDrawers();
                    return true;

                }else if(menuItem.getItemId() == R.id.nav_item_self){

                    AppConstant.POSITION=4;

                    mDrawerLayout.closeDrawers();
                    return true;

                }else if(menuItem.getItemId() == R.id.nav_item_print){

                    AppConstant.POSITION=5;

                    mDrawerLayout.closeDrawers();
                    return true;

                }else if(menuItem.getItemId() == R.id.nav_item_final){

                    AppConstant.POSITION=6;

                    mDrawerLayout.closeDrawers();
                    return true;
                } else if(menuItem.getItemId() == R.id.nav_item_select){
                    AppConstant.POSITION=7;
                    mDrawerLayout.closeDrawers();
                    return true;
                }*/

                return true;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */


         android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
         mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
         mDrawerLayout.setDrawerListener(mDrawerToggle);
         mDrawerToggle.syncState();


        // set here nav icon if want to change
      //  toolbar.setNavigationIcon(R.id.nav_icon);



    }

}