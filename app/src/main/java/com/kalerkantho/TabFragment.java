package com.kalerkantho;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kalerkantho.Fragment.AllnewsFragment;
import com.kalerkantho.Fragment.NirbaChitoCategoryFragment;
import com.kalerkantho.Fragment.PrintVFragment;
import com.kalerkantho.Fragment.SubjectLikeFragment;
import com.kalerkantho.Utils.AppConstant;

public class TabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
   public static int int_items = 9;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
             View x =  inflater.inflate(R.layout.tab_layout,null);
             tabLayout = (TabLayout) x.findViewById(R.id.tabs);
             viewPager = (ViewPager) x.findViewById(R.id.viewpager);


            viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                Log.e("position",""+position);

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

        private String tabTitles[] = new String[] { "হোম", "শীর্ষ সংবাদ", "সর্বশেষ সংবাদ", "সর্বাধিক দর্শন", "নির্বাচিত সংবাদ", "প্রিন্ট ভার্সন", "নির্বাচিত ক্যাটাগরি","বিষয় পছন্দ করুন","সকল সংবাদ"};

        public MyAdapter(FragmentManager fm) {
            super(fm);

        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
           // position = AppConstant.POSITION;

            switch (position){

              case 0 :

                  return new HomeFragment();

              case 1 :

                  return new LatestNewsFragment();

              case 2 :

                  return new TopNewsFragment();

              case 3 :

                  return new MostviewNewsFragment();

              case 4 :

                  return new MostviewNewsFragment();

              case 5 :

                  return new PrintVFragment();

              case 6 :

                  return new NirbaChitoCategoryFragment();

              case 7 :

                  return new SubjectLikeFragment();

                case 8 :

                    return new AllnewsFragment();

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