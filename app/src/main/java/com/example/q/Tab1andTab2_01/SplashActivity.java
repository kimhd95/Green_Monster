package com.example.q.Tab1andTab2_01;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;

public class SplashActivity extends Activity {
    ImageView img;
    AnimationDrawable ani;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        initialize();
    }

    private void initialize() {
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                finish();    	// 액티비티 종료
            }
        };

        handler.sendEmptyMessageDelayed(0, 5000);	// ms, 3초후 종료시킴
        img=(ImageView)findViewById(R.id.loading_img);
        ani=(AnimationDrawable)img.getDrawable();
        new Thread(new Runnable() {
            @Override

            public void run() {

//                Handler handler; // = new Handler(Looper.getMainLooper());


                Handler handler = new Handler(Looper.getMainLooper());


                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        img.setImageResource(R.drawable.loading_gradient);
                        ani=(AnimationDrawable)img.getDrawable();
                        ani.start();
                    }
                }, 0);




                /*
                handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (ani.isRunning()) ani.stop();
                        img.setImageResource(R.drawable.pink_attack);
                        ani=(AnimationDrawable)img.getDrawable();
                        ani.start();
                    }
                }, 0);

                */

                /*
                handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (ani.isRunning()) ani.stop();
                        img.setImageResource(R.drawable.pink_attack);
                        ani=(AnimationDrawable)img.getDrawable();
                        ani.start();
                    }
                }, 0);
                */

            }

        }).start();



    }

}
