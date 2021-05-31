package com.jeongseok.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.jeongseok.petcare.localdb.AppDataBase;
import com.jeongseok.petcare.localdb.Profile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditActivity extends AppCompatActivity {

    private Button editButton;
    private ImageView backButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        editButton = (Button)findViewById(R.id.editSave_btn);
        backButton = (ImageView)findViewById(R.id.edit_backbtn_image);

        EditText nameEditText = findViewById(R.id.name1);
        EditText birthdayEditText = findViewById(R.id.birthday1);
        EditText genderEditText = findViewById(R.id.gender1);
        EditText breedEditText = findViewById(R.id.breed1);
        TextView errorTextView = findViewById(R.id.error1);

        final AppDataBase db = Room.databaseBuilder(this, AppDataBase.class, "petcare-db" )
                .allowMainThreadQueries()
                .build();

        int id = db.profileDao().getAll().get(0).getId();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Empty Check
                if(nameEditText.getText().toString().isEmpty()){
                    errorTextView.setText("이름을 입력해주세요");
                    return;
                }else if(birthdayEditText.getText().toString().isEmpty()){
                    errorTextView.setText("생년월일을 입력해주세요");
                    return;
                }else if(genderEditText.getText().toString().isEmpty()){
                    errorTextView.setText("성별을 입력해주세요");
                    return;
                }else if(breedEditText.getText().toString().isEmpty()){
                    errorTextView.setText("품종을 입력해주세요");
                    return;
                }

                String name = nameEditText.getText().toString();
                String breed = breedEditText.getText().toString();

                //Date Check
                SimpleDateFormat sd = new SimpleDateFormat("yyMMdd", Locale.KOREA);
                Date date;
                try {
                    date = sd.parse(birthdayEditText.getText().toString());
                } catch (ParseException e) {
                    errorTextView.setText("생일 입력 값을 확인해주세요");
                    return;
                }

                //Gender Check
                boolean gender;
                if(genderEditText.getText().toString().matches("M") || genderEditText.getText().toString().matches("m")){
                    gender = false;
                } else if(genderEditText.getText().toString().matches("F") || genderEditText.getText().toString().matches("f")){
                    gender = true;
                }else{
                    errorTextView.setText("성별 입력 값을 확인해주세요");
                    return;
                }
                Log.d("edit", date.toString());
                Profile editProfile = new Profile("image URL", name, date,gender, breed);
                editProfile.setId(id);
                db.profileDao().update(editProfile);

                onBackPressed();
            }
        });
    }
}
