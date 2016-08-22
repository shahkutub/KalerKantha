package com.kalerkantho;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aapbd.utils.storage.PersistData;
import com.google.gson.Gson;
import com.kalerkantho.Adapter.Menu2RecyAdapter;
import com.kalerkantho.Adapter.Menu3RecyAdapter;
import com.kalerkantho.Adapter.MenuRecyAdapter;
import com.kalerkantho.Model.Category;
import com.kalerkantho.Utils.AppConstant;
import com.kalerkantho.Utils.DividerItemDecoration;
import com.kalerkantho.holder.AllCategory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
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
    List<String> heading4= new ArrayList<String>();
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

        /**
         *Setup the DrawerLayout and NavigationView
         */

        final Typeface face_bold = Typeface.createFromAsset(getApplication().getAssets(), "fonts/SolaimanLipi_Bold.ttf");

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
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
        mFragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
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



        heading4.add("Submenu of item 1");
        heading4.add("Submenu of item 2");
        heading4.add("Submenu of item 3");
        heading4.add("Submenu of item 4");
        heading4.add("Submenu of item 5");
        heading4.add("Submenu of item 6");
        heading4.add("Submenu of item 7");
        heading4.add("Submenu of item 8");
        heading4.add("Submenu of item 9");
        heading4.add("Submenu of item 10");
        heading4.add("Submenu of item 11");
        heading4.add("Submenu of item 12");
        heading4.add("Submenu of item 13");
        heading4.add("Submenu of item 14");
        heading4.add("Submenu of item 15");

        Gson g = new Gson();
        allCategory = g.fromJson(PersistData.getStringData(getApplicationContext(), AppConstant.CATEGORY_RESPONSE),AllCategory.class);


        printBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (listViewMenu.getVisibility() == View.GONE){
                   listViewMenu.setVisibility(View.VISIBLE);
                   showPrintListView.setImageResource(R.drawable.back_show);

                   printList.clear();

                   for (int i=0;i<allCategory.getCategory_list().size();i++){
                       if (allCategory.getCategory_list().get(i).getM_type().equalsIgnoreCase("print")){

                           printList.add(allCategory.getCategory_list().get(i));
                       }
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
        shokolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shokolshogbadList.getVisibility() == View.GONE){
                    shokolShonbadListView.setImageResource(R.drawable.back_show);
                    shokolshogbadList.setVisibility(View.VISIBLE);
                    onlineList.clear();

                    for (int i=0;i<allCategory.getCategory_list().size();i++){

                        if (allCategory.getCategory_list().get(i).getM_type().equalsIgnoreCase("online")){

                            onlineList.add(allCategory.getCategory_list().get(i));
                        }
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
                    nirbacitoMenuView.setImageResource(R.drawable.back_show);
                    nirbachitoList.setVisibility(View.VISIBLE);
                    mAdapter3 = new Menu3RecyAdapter(getApplicationContext(), heading4);
                    nirbachitoList.setAdapter(mAdapter3);

                    RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(dividerDrawable);
                    nirbachitoList.addItemDecoration(dividerItemDecoration);

                }else{
                    nirbacitoMenuView.setImageResource(R.drawable.back_gone);
                    nirbachitoList.setVisibility(View.GONE);
                }
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
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

        // set here nav icon if want to change
      //  toolbar.setNavigationIcon(R.id.nav_icon);


    }


 /*   @Override
    public void onBackPressed() {
        if (isNavDrawerOpen()) {
            closeNavDrawer();
        } else {
            super.onBackPressed();
        }
    }

    protected boolean isNavDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    protected void closeNavDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }*/
}