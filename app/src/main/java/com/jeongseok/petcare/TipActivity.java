package com.jeongseok.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class TipActivity extends FragmentActivity {

    Button tipCheck_button;
    CalendarFragment calendarFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_detail);
        tipCheck_button =(Button)findViewById(R.id.tipCheck_btn);
        calendarFragment = new CalendarFragment();
        tipCheck_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(TipActivity.this, HomeActivity.class);
                startActivity(next_intent);
            }
        });


    }



}
