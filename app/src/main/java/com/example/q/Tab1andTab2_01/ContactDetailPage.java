package com.example.q.Tab1andTab2_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ContactDetailPage extends AppCompatActivity {
    Intent intent;
    private TextView H1, H2;
    private TextView T1, T2, T3, T4, T5;
    private String name, phone, dur, call, rec, snd, abs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail_page);

        H1 = (TextView)findViewById(R.id.name_TV);
        H2 = (TextView)findViewById(R.id.phone_TV);
        T1 = (TextView)findViewById(R.id.duration_TV);
        T2 = (TextView)findViewById(R.id.callTimes_TV);
        T3 = (TextView)findViewById(R.id.receiveTimes_TV);
        T4 = (TextView)findViewById(R.id.sendTimes_TV);
        T5 = (TextView)findViewById(R.id.absencsTimes_TV);

        intent = getIntent();
        name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone").replaceAll("-", "");
        dur = intent.getStringExtra("duration");
        call = intent.getStringExtra("call");
        rec = intent.getStringExtra("receive");
        snd = intent.getStringExtra("send");
        abs = intent.getStringExtra("absence");

        H1.setText(name);
        H2.setText("0"+phone.subSequence(0, 2) + "-" + phone.subSequence(2, 6) + "-" + phone.subSequence(6, 10));
        T1.setText(dur);
        T2.setText(call);
        T3.setText(rec);
        T4.setText(snd);
        T5.setText(abs);
    }




}