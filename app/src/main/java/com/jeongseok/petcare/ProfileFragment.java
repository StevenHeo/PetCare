package com.jeongseok.petcare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.jeongseok.petcare.localdb.AppDataBase;
import com.jeongseok.petcare.localdb.Profile;
import com.jeongseok.petcare.localdbPet.DataAdapter;
import com.jeongseok.petcare.localdbPet.dogDisease;

import java.text.SimpleDateFormat;
import java.util.List;

public class ProfileFragment extends Fragment {
    private View v;
    public List<dogDisease> dogDiseaseList;
    private ImageView edit_btn;

    private TextView name;
    private TextView gender;
    private TextView breed;
    private TextView birthday;

    private AppDataBase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        edit_btn=(ImageView)v.findViewById(R.id.edit_btn);

        name = v.findViewById(R.id.fp_name);
        gender = v.findViewById(R.id.fp_gender);
        breed = v.findViewById(R.id.fp_breed);
        birthday = v.findViewById(R.id.fp_birthday);

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
        Log.d("profile", p.getBirthDay().toString());

        name.setText(p.getName());
        gender.setText(p.getGender() ? "Female" : "Male");
        breed.setText(p.getBreed());
        birthday.setText(output.format(p.getBirthDay()));
    }
}
