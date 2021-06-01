package com.jeongseok.petcare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.jeongseok.petcare.localdb.AppDataBase;
import com.jeongseok.petcare.localdb.Profile;
import com.jeongseok.petcare.localdbPet.DataAdapter;
import com.jeongseok.petcare.localdbPet.dogDisease;

import java.text.SimpleDateFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    private View v;
    private ImageView edit_btn;

    private CircleImageView imgView;
    private TextView name;
    private TextView gender;
    private TextView breed;
    private TextView birthday;
    private ToggleButton toggle;

    private AppDataBase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_profile, container, false);
        edit_btn=(ImageView)v.findViewById(R.id.edit_btn);

        imgView = v.findViewById(R.id.profile_img_view_f);
        name = v.findViewById(R.id.fp_name);
        gender = v.findViewById(R.id.fp_gender);
        breed = v.findViewById(R.id.fp_breed);
        birthday = v.findViewById(R.id.fp_birthday);
        toggle = v.findViewById(R.id.toggle);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                NotificationReceiver.flag = b;
            }
        });

        db = Room.databaseBuilder(getContext(), AppDataBase.class, "petcare-db" )
                .allowMainThreadQueries()
                .build();

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(getContext(), EditActivity.class);
                startActivity(next_intent);
            }
        });

        Profile p = db.profileDao().getAll().get(0);
        SimpleDateFormat output = new SimpleDateFormat("yyMMdd");



        name.setText(p.getName());
        gender.setText(p.getGender() ? "Female" : "Male");
        breed.setText(p.getBreed());
        birthday.setText(output.format(p.getBirthDay()));

        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
        Profile p = db.profileDao().getAll().get(0);
        SimpleDateFormat output = new SimpleDateFormat("yyMMdd");

        if(p.getImage() != null)
            imgView.setImageBitmap(p.getImage());

        name.setText(p.getName());
        gender.setText(p.getGender() ? "Female" : "Male");
        breed.setText(p.getBreed());
        birthday.setText(output.format(p.getBirthDay()));
    }
}
