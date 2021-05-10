package com.jeongseok.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.jeongseok.petcare.localdb.AppDataBase;
import com.jeongseok.petcare.localdb.HealthData;
import com.jeongseok.petcare.localdb.Profile;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private Button next_btn;
    private ImageView back_image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);
        back_image = (ImageView)findViewById(R.id.backbtn_image);

        final AppDataBase db = Room.databaseBuilder(this, AppDataBase.class, "petcare-db" )
                .allowMainThreadQueries()
                .build();

        Toast.makeText(getApplicationContext(), "확인1: " + db.profileDao().getAll().toString() + " 확인2: " + db.healthDataDao().getAll().toString(), Toast.LENGTH_SHORT).show();
        backButton(back_image);


        next_btn = (Button)findViewById(R.id.profile_button);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> list =Arrays.asList(0, 0, 0);

//                db.profileDao().insert(new Profile("image", "name", new Date(1),false,"breed" ));
                db.healthDataDao().update(new HealthData(new Date(1),list,"abc"));

                Toast.makeText(getApplicationContext(), "확인1: " + db.profileDao().getAll().get(0).getBirthDay() + " 확인2: " +db.healthDataDao().getAll().get(0).getCheckList(), Toast.LENGTH_SHORT).show();
//                Intent next_intent = new Intent(ProfileActivity.this, HomeActivity.class);
//                startActivity(next_intent);
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
