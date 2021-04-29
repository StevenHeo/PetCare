package com.jeongseok.petcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button start_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_btn = (Button)findViewById(R.id.init_button);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(next_intent);

            }
        });

        /*
        AppDataBase db = Room.databaseBuilder(this, AppDataBase.class, "PetCare-DB").build();
        db.petCareDao().getAll();
        */
    }

}