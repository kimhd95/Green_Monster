package com.example.q.Tab1andTab2_01;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeGood();

        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ede4e4"));
        setSupportActionBar(toolbar);

        viewPager = (ViewPager)findViewById(R.id.vp);
        linearLayout = (LinearLayout)findViewById(R.id.ll);

        //Button btn_first = (Button)findViewById(R.id.btn_first);
        //Button btn_second = (Button)findViewById(R.id.btn_second);
        //Button btn_third = (Button)findViewById(R.id.btn_third);
        ImageButton imageButton1 = (ImageButton)findViewById(R.id.image_btn1);
        ImageButton imageButton2 = (ImageButton)findViewById(R.id.image_btn2);
        ImageButton imageButton3 = (ImageButton)findViewById(R.id.image_btn3);

        viewPager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);

        //btn_first.setOnClickListener(movePageListener);
        //btn_first.setTag(0);
        //btn_second.setOnClickListener(movePageListener);
        //btn_second.setTag(1);
        //btn_third.setOnClickListener(movePageListener);
        //btn_third.setTag(2);

        imageButton1.setOnClickListener(movePageListener);
        imageButton1.setTag(0);
        imageButton2.setOnClickListener(movePageListener);
        imageButton2.setTag(1);
        imageButton3.setOnClickListener(movePageListener);
        imageButton3.setTag(2);


        //btn_first.setSelected(true);
        imageButton1.setSelected(true);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                int i=0;
                while(i<3)
                {
                    if(position==i)
                    {
                        linearLayout.findViewWithTag(i).setSelected(true);
                    }else{
                        linearLayout.findViewWithTag(i).setSelected(false);
                    }
                    i++;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    View.OnClickListener movePageListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int tag = (int) v.getTag();
            int i=0;
            while(i<3)
            {
                if(tag==i)
                {
                    linearLayout.findViewWithTag(i).setSelected(true);
                }else{
                    linearLayout.findViewWithTag(i).setSelected(false);
                }
                i++;
            }

            viewPager.setCurrentItem(tag);
        }
    };

    private class pagerAdapter extends FragmentStatePagerAdapter
    {
        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new FirstFragment();
                case 1:
                    return new SecondFragment();
                case 2:
                    return new ThirdFragment();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 3;
        }
    }

    private void initializeGood() 	{
        InitializationRunnable init = new InitializationRunnable();
        new Thread(init).start();
    }

    /**
     * 초기화 작업 처리
     */
    class InitializationRunnable implements Runnable {
        public void run() {
            // 여기서부터 초기화 작업 처리
            // do_something
        }
    }


}
