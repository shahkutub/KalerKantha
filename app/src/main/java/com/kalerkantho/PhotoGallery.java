package com.kalerkantho;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.kalerkantho.Adapter.CustomPagerAdapter;
import com.kalerkantho.Utils.AppConstant;

public class PhotoGallery extends Activity {

    Context con;

    private ProgressBar progressShow;

    int imagePos=0;

    private ViewPager mPager;
    CustomPagerAdapter mCustomPagerAdapter ;
    private ImageView backwaredBtn,forwardBtn;
    int gallerySize=0;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.photodialog);
        con=this;

        backwaredBtn=(ImageView)findViewById(R.id.backwardButton);
        forwardBtn=(ImageView)findViewById(R.id.forwardButton);
        gallerySize= AppConstant.PHOTOLIST.size();


        mPager = (ViewPager) findViewById(R.id.pager);
        mCustomPagerAdapter = new CustomPagerAdapter(con);
        mPager.setAdapter(mCustomPagerAdapter);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                imagePos=position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        backwaredBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(imagePos==0)
                {

                }else {
                    mPager.setCurrentItem(imagePos-1);
                    imagePos=imagePos-1;
                }

            }
        });

        forwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((gallerySize-1)==imagePos)
                {

                }else {
                    mPager.setCurrentItem(imagePos+1);
                    imagePos=imagePos+1;
                }

            }
        });


    }

    public void closeWindow(View v)
    {
        PhotoGallery.this.finish();
    }
}