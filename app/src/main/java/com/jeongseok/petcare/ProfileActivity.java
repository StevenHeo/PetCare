package com.jeongseok.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private Button next_btn;
    private ImageView back_image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);
        back_image = (ImageView)findViewById(R.id.backbtn_image);

        backButton(back_image);

        next_btn = (Button)findViewById(R.id.profile_button);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(next_intent);
            }
        });
    }
    private void backButton(ImageView back_image){
        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
