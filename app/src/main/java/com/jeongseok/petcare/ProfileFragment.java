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
        //db테스트
        //TextView textView = v.findViewById(R.id.profileTextView);
        initLoadDB();
        //textView.setText(dogDiseaseList.get(1).result1);
        return v;

    }
    private void initLoadDB() {

        DataAdapter mDbHelper = new DataAdapter(this.getContext());
        mDbHelper.createDatabase();
        mDbHelper.open();
        dogDiseaseList = mDbHelper.getTableData();

        mDbHelper.close();
    }

}
