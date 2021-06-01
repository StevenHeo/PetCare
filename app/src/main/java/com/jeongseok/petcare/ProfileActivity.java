package com.jeongseok.petcare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.jeongseok.petcare.localdb.AppDataBase;
import com.jeongseok.petcare.localdb.Profile;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private Button next_btn;
    private ImageView back_image;
    private Uri uri;
    private Bitmap bitmap = null;
    private static final int PICK_IMAGE = 100;
    private CircleImageView imgView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_profile);
        imgView = findViewById(R.id.profile_img_view_a);
        final EditText nameEditText = findViewById(R.id.name_editText1);
        final EditText birthdayEditText = findViewById(R.id.birthday_editText);
        final EditText genderEditText = findViewById(R.id.gender_editText);
        final EditText breedEditText = findViewById(R.id.breed_editText);
        final TextView errorTextView = findViewById(R.id.errorTextView);
        final ImageView buttonImgView = (ImageView)findViewById(R.id.add_profile_a);

        back_image = (ImageView)findViewById(R.id.backbtn_image);


        final AppDataBase db = Room.databaseBuilder(this, AppDataBase.class, "petcare-db" )
                .allowMainThreadQueries()
                .build();

        backButton(back_image);

        buttonImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        next_btn = (Button)findViewById(R.id.profile_button);
        next_btn.setOnClickListener(new View.OnClickListener() {
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

                db.profileDao().insert(new Profile(bitmap, name, date,gender, breed));

                Intent next_intent = new Intent(ProfileActivity.this, HomeActivity.class);
                finishAffinity();
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
                imgView.setImageBitmap(bitmap);
            }


        }
    }

}
