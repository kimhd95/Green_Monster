package com.example.q.Tab1andTab2_01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashDemoActivity extends Activity {

    @Override

    public void onCreate(Bundle savedInstanceState) {

        // 스플래시 화면 띄우기

        startActivity(new Intent(this, SplashActivity.class));



        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        // 여기부터 시간이 걸리는 작업 처리

        initializeGood();	// 좋은(??) 초기화

    }



    /** 좋은(??) 초기화

     * - 스플래시 화면이 표시되는 동안에 어떤 일(예를 들면 초기화  작업)을 시키려면 작업을 메인쓰레드가 아닌 다른 쓰레드에서 처리하도록 해야됩니다.

     * - 메인 쓰레드는 스플래시 화면을 표시하는 일을 해야 되니까요.. 메인 쓰레드 혼자서 작업 2개를 동시에 할 수는 없습니다.

     */

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