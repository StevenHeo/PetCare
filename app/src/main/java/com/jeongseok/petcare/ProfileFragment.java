package com.jeongseok.petcare;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.List;

public class ProfileFragment extends Fragment {
    private View v;
    public List<dogDisease> dogDiseaseList;
    private ImageView edit_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        edit_btn=(ImageView)v.findViewById(R.id.edit_btn);
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(getContext(), EditActivity.class);
                startActivity(next_intent);
            }
        });

        final AppDataBase db = Room.databaseBuilder(this, AppDataBase.class, "petcare-db" )
                .allowMainThreadQueries()
                .build();

        Profile p = db.profileDao().getAll().get(0);

        TextView name = v.findViewById(R.id.fp_name);
        TextView gender = v.findViewById(R.id.fp_gender);
        TextView breed = v.findViewById(R.id.fp_breed);
        TextView birthday = v.findViewById(R.id.fp_birthday);

        name.setText(p.getName());
        gender.setText(p.getGender() ? "MALE" : "FEMALE");
        breed.setText(p.getBreed());
        birthday.setText(p.getBirthDay().toString());

        return v;
    }

}
