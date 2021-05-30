package com.jeongseok.petcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jeongseok.petcare.localdb.AppDataBase;

public class MainActivity extends AppCompatActivity {
    private Button start_btn;
    private int check=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AppDataBase db = Room.databaseBuilder(this, AppDataBase.class, "petcare-db" )
                .allowMainThreadQueries()
                .build();

        start_btn = (Button)findViewById(R.id.init_button);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.profileDao().getAll().size() <= 0&&check==0) {
                    check++;
                    Intent next_intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(next_intent);
                } else if(check==0) {
                    check++;
                    Intent next_intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(next_intent);
                }
            }
        });
    }
}