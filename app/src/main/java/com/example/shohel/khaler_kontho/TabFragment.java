package com.example.shohel.khaler_kontho;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shohel.khaler_kontho.Utils.AppConstant;
import com.kalerkantho.R;

/**
 * Created by Ratan on 7/27/2015.
 */
public class TabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
   public static int int_items = 7;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
             View x =  inflater.inflate(R.layout.tab_layout,null);
             tabLayout = (TabLayout) x.findViewById(R.id.tabs);
             viewPager = (ViewPager) x.findViewById(R.id.viewpager);
            // final Typeface face_bold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/SolaimanLipi_Bold.ttf");

        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });




        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                    tabLayout.setupWithViewPager(viewPager);
                   }
        });

        return x;

    }




    class MyAdapter extends FragmentPagerAdapter {

        private String tabTitles[] = new String[] { "হোম", "শীর্ষ সংবাদ", "সর্বশেষ সংবাদ", "সর্বাধিক দর্শন", "নিজ সংবাদ", "প্রিন্ট ভার্সন", "নির্বাচিত ক্যাটাগরি"};

        public MyAdapter(FragmentManager fm) {
            super(fm);

        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
            position = AppConstant.POSITION;

            switch (position){

              case 0 : return new HomeFragment();
              case 1 : return new LatestNewsFragment();
              case 2 : return new TopNewsFragment();
              case 3 : return new MostviewNewsFragment();
              case 4 : return new MostviewNewsFragment();
              case 5 : return new MostviewNewsFragment();
              case 6 : return new MostviewNewsFragment();
              case 7 : return new MostviewNewsFragment();

          }
        return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        @Override
        public CharSequence getPageTitle(int position) {
                return tabTitles[position];
        }
    }

}
