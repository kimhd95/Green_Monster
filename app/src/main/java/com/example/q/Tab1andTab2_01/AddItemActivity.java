package com.example.q.Tab1andTab2_01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {
    private Intent intent;
    private EditText year_EditText, month_EditText, day_EditText;
    private EditText hour_EditText, min_EditText, desc_EditText, sum_EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        year_EditText = (EditText)findViewById(R.id.year__EDITTEXT);
        month_EditText = (EditText)findViewById(R.id.month__EDITTEXT);
        day_EditText = (EditText)findViewById(R.id.day__EDITTEXT);
        hour_EditText = (EditText)findViewById(R.id.hour__EDITTEXT);
        min_EditText = (EditText)findViewById(R.id.minute__EDITTEXT);
        desc_EditText = (EditText)findViewById(R.id.desc__EDITTEXT);
        sum_EditText = (EditText)findViewById(R.id.sum__EDITTEXT);
        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();

                if (year_EditText.getText().toString().getBytes().length <= 0 || month_EditText.getText().toString().getBytes().length <= 0 ||
                        day_EditText.getText().toString().getBytes().length <= 0 || hour_EditText.getText().toString().getBytes().length <= 0 ||
                        min_EditText.getText().toString().getBytes().length <= 0 || desc_EditText.getText().toString().getBytes().length <= 0 ||
                        sum_EditText.getText().toString().getBytes().length <= 0)
                    finish();

                intent.putExtra("Year", year_EditText.getText().toString());
                intent.putExtra("Month", month_EditText.getText().toString());
                intent.putExtra("Day", day_EditText.getText().toString());
                intent.putExtra("Hour", hour_EditText.getText().toString());
                intent.putExtra("Minute", min_EditText.getText().toString());
                intent.putExtra("Desc", desc_EditText.getText().toString());
                intent.putExtra("Sum", sum_EditText.getText().toString());
                setResult(1234, intent);
                finish();
            }
        });


    }
}
