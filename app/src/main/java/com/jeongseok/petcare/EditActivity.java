package com.jeongseok.petcare;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    private Uri uri;
    private Bitmap bitmap = null;
    private Button editButton;
    private ImageView backButton;
    private ImageView buttonImgView;
    private CircleImageView profileImgView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        editButton = (Button)findViewById(R.id.editSave_btn);
        backButton = (ImageView)findViewById(R.id.edit_backbtn_image);
        buttonImgView = (ImageView)findViewById(R.id.add_profile_e);
        profileImgView = (CircleImageView)findViewById(R.id.profile_img_view_e);

        EditText nameEditText = findViewById(R.id.name1);
        EditText birthdayEditText = findViewById(R.id.birthday1);
        EditText genderEditText = findViewById(R.id.gender1);
        EditText breedEditText = findViewById(R.id.breed1);
        TextView errorTextView = findViewById(R.id.error1);

        final AppDataBase db = Room.databaseBuilder(this, AppDataBase.class, "petcare-db" )
                .allowMainThreadQueries()
                .build();

        int id = db.profileDao().getAll().get(0).getId();

        buttonImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

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

                Profile editProfile = new Profile(bitmap, name, date,gender, breed);
                editProfile.setId(id);
                db.profileDao().update(editProfile);

                onBackPressed();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE && data != null && data.getData() != null){
            uri = data.getData();
            try {
                bitmap =  MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(bitmap != null){
                profileImgView.setImageBitmap(bitmap);
            }
        }
    }
}
